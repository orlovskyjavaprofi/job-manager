package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class User
{
	private String userFirstName;
    private String userLastName;
    private LocalDate userBirthDate;
    
	public User()
	{	
	  this.setUserFirstName("undefined");
	}
	

	public User(String firstNameOfUser, String lastNameOfUser)
	{
		this.setUserFirstName(firstNameOfUser);
		this.setUserLastName(lastNameOfUser);
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


	public String getUserBirthDate()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
		return userBirthDate.format(formatter);
	}


	public void setUserBirthDate(String userBirthDate)
	{		
		userBirthDate = userBirthDate.replaceAll("[.]", "-");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.userBirthDate = LocalDate.parse(userBirthDate,formatter);
	}



}
