package models;

public class User
{
	private String userFirstName;
    private String userLastName;
    
	public User()
	{	
	  this.setUserFirstName("undefined");
	}
	
	public User(String firstName)
	{	
		this.userFirstName = firstName;
	}
	
	public String getUserFirstName()
	{
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName)
	{
		this.userFirstName = userFirstName;
	}

	public String getUserLastName()
	{
		return userLastName;
	}

	public void setUserLastName(String userLastName)
	{
		this.userLastName = userLastName;
	}



}
