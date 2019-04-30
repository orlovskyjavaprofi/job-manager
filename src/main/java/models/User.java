package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class User
{
	@NotNull
	@Size(min=5, max=40)
	private String userFirstName;
	@NotNull
	@Size(min=5, max=40)
	private String userLastName;
	private String userEmail;
	private String userCity;
	private String userStreetName;
	private String userCountryName;
	private LocalDate userBirthDate;
	private UserEmploymentState currentEmploymentState;
	private UserSexState currentUserSexState;
	private Integer userStreetNumber;
	private Boolean userAccountState;
	private LocalDate userRegistrationDate;
	private String userNickName;
	private List<UserSexState> typesOfUserSex = new ArrayList<UserSexState>();
	private List<UserEmploymentState> typesOfUserEmploymentState = new ArrayList<UserEmploymentState>();
	
	public User()
	{
		setUpDefaultValues();
	}

	private void setUpDefaultValues()
	{
		String textplacer = "";
		this.setUserFirstName(textplacer);
		this.setUserLastName(textplacer);
		this.setUserEmail(textplacer);
		this.setUserStreetName(textplacer);
		this.setUserStreetNumber(0);
		this.setUserCity(textplacer);
		this.setUserCountryName(textplacer);
		this.setCurrentEmploymentState(UserEmploymentState.UNEMPLOYED);
		this.setCurrentUserSexState(UserSexState.UNDEFINED);
		this.setUserAccountState(false);
		this.setUserNickName("");
		this.setUserBirthDate("25.03.1978");
		formaRegDate();
		// To-do implement a Reference to List or of some sort collection of job
		// applications where belong to this user
	}

	public User(String firstNameOfUser, String lastNameOfUser)
	{
		this.setUserFirstName(firstNameOfUser);
		this.setUserLastName(lastNameOfUser);
		this.setUserAccountState(false);
		formaRegDate();
	}

	private void formaRegDate()
	{
		this.setUserRegistrationDate(LocalDate.now());
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
		this.userBirthDate = LocalDate.parse(userBirthDate, formatter);
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

	public Boolean getUserAccountState()
	{
		return userAccountState;
	}

	public void setUserAccountState(Boolean userAccountState)
	{
		this.userAccountState = userAccountState;
	}

	public LocalDate getUserRegistrationDate()
	{
		return userRegistrationDate;
	}

	public void setUserRegistrationDate(LocalDate userRegistrationDate)
	{
		this.userRegistrationDate = userRegistrationDate;
	}

	public UserSexState getCurrentUserSexState()
	{
		return currentUserSexState;
	}

	public void setCurrentUserSexState(UserSexState currentUserSexState)
	{
		this.currentUserSexState = currentUserSexState;
	}

	public String getUserNickName()
	{
		return userNickName;
	}

	public void setUserNickName(String userNickName)
	{
		this.userNickName = userNickName;
	}

	public List<UserSexState> getTypesOfUserSex()
	{
		return typesOfUserSex;
	}

	public void setTypesOfUserSex(List<UserSexState> typesOfUserSex)
	{
		this.typesOfUserSex = typesOfUserSex;
	}

	public List<UserEmploymentState> getTypesOfUserEmploymentState()
	{
		return typesOfUserEmploymentState;
	}

	public void setTypesOfUserEmploymentState(List<UserEmploymentState> typesOfUserEmploymentState)
	{
		this.typesOfUserEmploymentState = typesOfUserEmploymentState;
	}

	@Override
	public String toString()
	{
		return "User: "+ userNickName + "\nuserFirstName=" + userFirstName + "\nuserLastName=" + userLastName 
				+ "\nuserEmail=" + userEmail
				+ "\nuserCity=" + userCity + "\nuserStreetName=" + userStreetName + "\nuserCountryName="
				+ userCountryName + "\nuserBirthDate=" + userBirthDate +"\nuserSex=" + currentUserSexState
				+ "\ncurrentEmploymentState=" + currentEmploymentState 
				+ "\nuserStreetNumber=" + userStreetNumber + "\nuserAccountState="
				+ userAccountState + "\nuserRegistrationDate=" + userRegistrationDate ;
	}

}
