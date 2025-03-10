package ca.mcgill.ecse321.GameOn.dto;
import ca.mcgill.ecse321.GameOn.model.Game;
import ca.mcgill.ecse321.GameOn.model.Review;

import java.util.List;

public class GameResponseDTO {
    private String picture;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private String category;
    private List<Review> reviews;

    protected GameResponseDTO() {
    }

    public GameResponseDTO(Game game) {
        this.picture = game.getPicture();
        this.name = game.getName();
        this.description = game.getDescription();
        this.price = game.getPrice();
        this.quantity = game.getQuantity();
        this.category = game.getCategory().getName();
        this.reviews = game.getReviews();
    }

    public String getCategory() {
        return category;
    }

    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public List<Review> getReviews(){return reviews;}
}
