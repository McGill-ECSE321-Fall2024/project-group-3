/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.GameOn.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;


// line 70 "GameOn.ump"
@JsonIgnoreProperties({"games"}) 
@Entity
public class Category
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  // Category can either be available or unavailable
  public enum CategoryStatus {AVAILABLE, UNAVAILABLE}
  private CategoryStatus status;

  //Category Attributes
  @Id
  private String name;

  //Category Associations
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Game> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Category(String aName)
  {
    name = aName;
    games = new ArrayList<Game>();
    status = CategoryStatus.AVAILABLE;
  }

  protected Category(){

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(CategoryStatus aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public CategoryStatus getStatus()
  {
    return status;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }
  
  /* Code from template association_AddManyToOne */
  public Game addGame(String aPicture, String aName, String aDescription, int aPrice, int aQuantity)
  {
    return new Game(aPicture, aName, aDescription, aPrice, aQuantity, this);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Category existingCategory = aGame.getCategory();
    boolean isNewCategory = existingCategory != null && !this.equals(existingCategory);
    if (isNewCategory)
    {
      aGame.setCategory(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a category
    if (!this.equals(aGame.getCategory()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }

}