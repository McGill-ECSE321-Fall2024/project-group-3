/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.GameOn.model;
import jakarta.persistence.*;
import java.util.*;
import java.sql.Date;

// line 82 "GameOn.ump"

@Entity
public class Wishlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Wishlist Attributes
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY)
  private int id;

  //Wishlist Associations

    @OneToMany //Wishlist --> WishList Link
  private List<WishlistLink> wishlistLink;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Wishlist()
  {
    wishlistLink = new ArrayList<WishlistLink>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public WishlistLink getWishlistLink(int index)
  {
    WishlistLink aWishlistLink = wishlistLink.get(index);
    return aWishlistLink;
  }

  public List<WishlistLink> getWishlistLink()
  {
    List<WishlistLink> newWishlistLink = Collections.unmodifiableList(wishlistLink);
    return newWishlistLink;
  }

  public int numberOfWishlistLink()
  {
    int number = wishlistLink.size();
    return number;
  }

  public boolean hasWishlistLink()
  {
    boolean has = wishlistLink.size() > 0;
    return has;
  }

  public int indexOfWishlistLink(WishlistLink aWishlistLink)
  {
    int index = wishlistLink.indexOf(aWishlistLink);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWishlistLink()
  {
    return 0;
  }

  public boolean addOrMoveWishlistLinkAt(WishlistLink aWishlistLink, int index)
  {
    boolean wasAdded = false;
    if(wishlistLink.contains(aWishlistLink))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWishlistLink()) { index = numberOfWishlistLink() - 1; }
      wishlistLink.remove(aWishlistLink);
      wishlistLink.add(index, aWishlistLink);
      wasAdded = true;
    }
    return wasAdded;
  }

  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]";
  }
}