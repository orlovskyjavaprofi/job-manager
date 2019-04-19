package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class User
{
	private String userFirstName;
    private String userLastName;
    private String userEmail; 
    private String userCity;
    private String userStreetName;
    private String userCountryName;
    private LocalDate userBirthDate;
    private UserEmploymentState currentEmploymentState;
    private Integer userStreetNumber;
    
	public User()
	{	
	  String textplacer = "undefined";
	  this.setUserFirstName(textplacer);
	  this.setUserLastName(textplacer);
	  this.setUserEmail(textplacer);
	  this.setUserStreetName(textplacer);
	  this.setUserStreetNumber(0);
	  this.setUserCity(textplacer);
	  this.setUserCountryName(textplacer);
	  this.setCurrentEmploymentState(UserEmploymentState.UNEMPLOYED);
	}

	public User(String firstNameOfUser, String lastNameOfUser)
	{
		this.setUserFirstName(firstNameOfUser);
		this.setUserLastName(lastNameOfUser);
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
	
	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserCity()
	{
		return userCity;
	}

	public void setUserCity(String userCity)
	{
		this.userCity = userCity;
	}

	public String getUserStreetName()
	{
		return userStreetName;
	}

	public void setUserStreetName(String userStreetName)
	{
		this.userStreetName = userStreetName;
	}

	public Integer getUserStreetNumber()
	{
		return userStreetNumber;
	}

	public void setUserStreetNumber(Integer userStreetNumber)
	{
		this.userStreetNumber = userStreetNumber;
	}

	public String getUserCountryName()
	{
		return userCountryName;
	}

	public void setUserCountryName(String userCountryName)
	{
		this.userCountryName = userCountryName;
	}

	public UserEmploymentState getCurrentEmploymentState()
	{
		return currentEmploymentState;
	}

	public void setCurrentEmploymentState(UserEmploymentState currentEmploymentState)
	{
		this.currentEmploymentState = currentEmploymentState;
	}


}
