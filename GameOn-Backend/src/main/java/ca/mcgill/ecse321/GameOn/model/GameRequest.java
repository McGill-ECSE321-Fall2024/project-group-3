/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 40 "GameOn.ump"
public class GameRequest
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum RequestType { Create, Archive }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameRequest Attributes
  private RequestType requestType;

  //GameRequest Associations
  private Employee requestCreator;
  private Game resquestedGame;
  private Manager requestApprover;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameRequest(RequestType aRequestType, Employee aRequestCreator, Game aResquestedGame, Manager aRequestApprover)
  {
    requestType = aRequestType;
    boolean didAddRequestCreator = setRequestCreator(aRequestCreator);
    if (!didAddRequestCreator)
    {
      throw new RuntimeException("Unable to create gameRequest due to requestCreator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setResquestedGame(aResquestedGame))
    {
      throw new RuntimeException("Unable to create GameRequest due to aResquestedGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddRequestApprover = setRequestApprover(aRequestApprover);
    if (!didAddRequestApprover)
    {
      throw new RuntimeException("Unable to create gameRequest due to requestApprover. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRequestType(RequestType aRequestType)
  {
    boolean wasSet = false;
    requestType = aRequestType;
    wasSet = true;
    return wasSet;
  }

  public RequestType getRequestType()
  {
    return requestType;
  }
  /* Code from template association_GetOne */
  public Employee getRequestCreator()
  {
    return requestCreator;
  }
  /* Code from template association_GetOne */
  public Game getResquestedGame()
  {
    return resquestedGame;
  }
  /* Code from template association_GetOne */
  public Manager getRequestApprover()
  {
    return requestApprover;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRequestCreator(Employee aRequestCreator)
  {
    boolean wasSet = false;
    if (aRequestCreator == null)
    {
      return wasSet;
    }

    Employee existingRequestCreator = requestCreator;
    requestCreator = aRequestCreator;
    if (existingRequestCreator != null && !existingRequestCreator.equals(aRequestCreator))
    {
      existingRequestCreator.removeGameRequest(this);
    }
    requestCreator.addGameRequest(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setResquestedGame(Game aNewResquestedGame)
  {
    boolean wasSet = false;
    if (aNewResquestedGame != null)
    {
      resquestedGame = aNewResquestedGame;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRequestApprover(Manager aRequestApprover)
  {
    boolean wasSet = false;
    if (aRequestApprover == null)
    {
      return wasSet;
    }

    Manager existingRequestApprover = requestApprover;
    requestApprover = aRequestApprover;
    if (existingRequestApprover != null && !existingRequestApprover.equals(aRequestApprover))
    {
      existingRequestApprover.removeGameRequest(this);
    }
    requestApprover.addGameRequest(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Employee placeholderRequestCreator = requestCreator;
    this.requestCreator = null;
    if(placeholderRequestCreator != null)
    {
      placeholderRequestCreator.removeGameRequest(this);
    }
    resquestedGame = null;
    Manager placeholderRequestApprover = requestApprover;
    this.requestApprover = null;
    if(placeholderRequestApprover != null)
    {
      placeholderRequestApprover.removeGameRequest(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "requestType" + "=" + (getRequestType() != null ? !getRequestType().equals(this)  ? getRequestType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "requestCreator = "+(getRequestCreator()!=null?Integer.toHexString(System.identityHashCode(getRequestCreator())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "resquestedGame = "+(getResquestedGame()!=null?Integer.toHexString(System.identityHashCode(getResquestedGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "requestApprover = "+(getRequestApprover()!=null?Integer.toHexString(System.identityHashCode(getRequestApprover())):"null");
  }
}