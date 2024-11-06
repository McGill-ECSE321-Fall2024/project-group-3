package ca.mcgill.ecse321.GameOn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import ca.mcgill.ecse321.GameOn.repository.CategoryRepository;
import ca.mcgill.ecse321.GameOn.repository.GameRepository;
import ca.mcgill.ecse321.GameOn.repository.GameRequestRepository;
import ca.mcgill.ecse321.GameOn.model.Category;
import ca.mcgill.ecse321.GameOn.model.Employee;
import ca.mcgill.ecse321.GameOn.model.Game;
import ca.mcgill.ecse321.GameOn.model.GameRequest;
import ca.mcgill.ecse321.GameOn.model.Manager;
import ca.mcgill.ecse321.GameOn.model.RequestType;
import ca.mcgill.ecse321.GameOn.model.Game.GameStatus;

@Service
public class GameController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameRequestRepository gameRequestRepository;

    /**
     * Method to create new category
     * @param aName
     * @throws IllegalArgumentException if name is invalid
     */
    @Transactional
    public Category createCategory(String aName){
        if (aName == null || aName.trim().length() == 0) {
            throw new IllegalArgumentException("Name is invalid");
        }
        Category category = new Category(aName);
        category.setName(aName);
        categoryRepository.save(category);
        return category;
    }

    /**
     * Method to delete category
     * @param aName
     * @throws IllegalArgumentException if name is invalid
     */
    @Transactional
    public void deleteCategory(String aName){
        if (aName == null || aName.trim().length() == 0) {
            throw new IllegalArgumentException("Name is invalid");
        }

        Category category = categoryRepository.findCategoryByName(aName);

        if (category == null) {
            throw new IllegalArgumentException("Category does not exist");
        }

        //The associated game to the category should be deleted first
        for (Game game : category.getGames()) {
            gameRepository.delete(game);
        }

        categoryRepository.delete(category);
    }

    /**
     * Method to get all categories
     * @param List<Category>
     * @throws IllegalArgumentException if name is invalid
     */
    @Transactional
    public Iterable<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    /**
     * Method add a game.
     * When a game is created, the game status is set to unavaible. 
     * The logic is that the manager should approve the game request before the game is available.
     * 
     * @param aName
     * @param aCategory
     * @param aDescription
     * @param aPrice
     * @param aQuantity
     * @param aPicture
     * @return Game
     * @throws IllegalArgumentException if parameters are invalid or game already exists.
     */
    @Transactional
    public Game createGame(String aPicture, String aName, String aDescription, int aPrice, int aQuantity, Category aCategory){
        if (aName == null || aName.trim().length() == 0) {
            throw new IllegalArgumentException("Name is invalid");
        }
        if (aDescription == null || aDescription.trim().length() == 0) {
            throw new IllegalArgumentException("Description is invalid");
        }
        if (aPrice <= 0) {
            throw new IllegalArgumentException("Price is invalid");
        }
        if (aQuantity <= 0) {
            throw new IllegalArgumentException("Quantity is invalid");
        }
        if (aCategory == null) {
            throw new IllegalArgumentException("Category is invalid");
        }

        if (gameRepository.findGameByName(aName) != null) {
            throw new IllegalArgumentException("Game already exists");
        }

        Game game = new Game(aPicture, aName, aDescription, aPrice, aQuantity, aCategory);
        gameRepository.save(game);

        return game;
    }

    /**
     * Method to delete a game.
     * 
     * @param aName
     */
    @Transactional
    public void deleteGame(String aName){
        if (aName == null || aName.trim().length() == 0) {
            throw new IllegalArgumentException("Name is invalid");
        }

        Game game = gameRepository.findGameByName(aName);

        if (game == null) {
            throw new IllegalArgumentException("Game does not exist");
        }
        gameRepository.delete(game);
    }

    /**
     * Method to get all games.
     * 
     * @return List<Game>
     */
    @Transactional
    public Iterable<Game> getAllGames(){
        return gameRepository.findAll();
    }

    /**
     * Method to get a specific game.
     * 
     * @param aGameName
     * @return Game
     */
    @Transactional
    public Game findGameByName(String aGameName){
        if (aGameName == null || aGameName.trim().length() == 0) {
            throw new IllegalArgumentException("Name is invalid");
        }

        Game game = gameRepository.findGameByName(aGameName);

        if (game == null) {
            throw new IllegalArgumentException("Game does not exist");
        }

        return game;
    }

    /**
     * Method to get all games in a category.
     * 
     * @param aCategoryName
     * @return List<Game>
     */
    @Transactional
    public Iterable<Game> getGamesInCategory(String aCategoryName){
        if (aCategoryName == null || aCategoryName.trim().length() == 0) {
            throw new IllegalArgumentException("Name is invalid");
        }

        Category category = categoryRepository.findCategoryByName(aCategoryName);

        if (category == null) {
            throw new IllegalArgumentException("Category does not exist");
        }

        return category.getGames();
    }


    /**
     * Method create a game request for a game.
     * A game request is created by an employee and is approved by a manager.
     * A game request can either be a request to create a game or a request to archive a game.
     * 
     * @param aRequestCreator
     * @param aRequestedGame
     * @param aRequestType
     */
    @Transactional
    public GameRequest createGameRequest(Employee aRequestCreator, Game aRequestedGame, RequestType aRequestType){
        if (aRequestCreator == null) {
            throw new IllegalArgumentException("Request creator is invalid");
        }
        if (aRequestedGame == null) {
            throw new IllegalArgumentException("Requested game is invalid");
        }
        if (aRequestType == null) {
            throw new IllegalArgumentException("Request type is invalid");
        }

        GameRequest gameRequest = new GameRequest(aRequestType, aRequestCreator, aRequestedGame);
        gameRequestRepository.save(gameRequest);

        return gameRequest;
    }

    /**
     * Method to approve a game request.
     * This changes the game status to available.
     * 
     * @param aGameRequest
     * @param aManager
     * @throws IllegalArgumentException if game or manager is invalid
     */
    public void approveGameRequest(GameRequest aGameRequest, Manager aManager){
        if (aGameRequest == null) {
            throw new IllegalArgumentException("Game Request is invalid");
        }
        if (aManager == null) {
            throw new IllegalArgumentException("Manager is invalid");
        }

        GameRequest gameRequest = gameRequestRepository.findGameRequestById(aGameRequest.getId());

        if (gameRequest == null) {
            throw new IllegalArgumentException("Game request does not exist");
        }

        if (gameRequest.getRequestType() == RequestType.Create) {
            if (gameRequest.getResquestedGame().getGameStatus().equals(GameStatus.Available)) {
                throw new IllegalArgumentException("Game is already available");
            } else {
                gameRequest.getResquestedGame().setAvailable();
            }
        } else if (gameRequest.getRequestType() == RequestType.Archive) {
            gameRequest.getResquestedGame().setUnavailable();
        }

        gameRepository.save(gameRequest.getResquestedGame());
    }

    /**
     * Method to get the quantity of a game.
     * 
     * @param aGame
     * @return int quantity
     */
    @Transactional
    public int getGameQuantity(Game aGame){
        if (aGame == null) {
            throw new IllegalArgumentException("Game is invalid");
        }

        Game game = gameRepository.findGameByName(aGame.getName());

        if (game == null) {
            throw new IllegalArgumentException("Game does not exist");
        }
        return game.getQuantity();
    }

    /**
     * Method to update the quantity of a game.
     * 
     * @param aGame
     * @param aQuantity
     */
    @Transactional
    public void updateGameQuantity(Game aGame, int aQuantity){
        if (aGame == null) {
            throw new IllegalArgumentException("Game is invalid");
        }

        Game game = gameRepository.findGameByName(aGame.getName());

        if (game == null) {
            throw new IllegalArgumentException("Game does not exist");
        }

        game.setQuantity(aQuantity);
        gameRepository.save(game);
    }

    /**
     * Method to get the price of a game.
     * 
     * @param aGame
     * @return int price
     */
    @Transactional
    public int getGamePrice(Game aGame){
        if (aGame == null) {
            throw new IllegalArgumentException("Game is invalid");
        }

        Game game = gameRepository.findGameByName(aGame.getName());

        if (game == null) {
            throw new IllegalArgumentException("Game does not exist");
        }
        return game.getPrice();
    }

    /**
     * Method to update the price of a game.
     * 
     * @param aGame
     * @param aPrice
     * @throws IllegalArgumentException if game is invalid
     * @throws IllegalArgumentException if game does not exist
     */
    @Transactional
    public void updateGamePrice(Game aGame, int aPrice){
        if (aGame == null) {
            throw new IllegalArgumentException("Game is invalid");
        }

        Game game = gameRepository.findGameByName(aGame.getName());

        if (game == null) {
            throw new IllegalArgumentException("Game does not exist");
        }

        game.setPrice(aPrice);
        gameRepository.save(game);
    }

}
