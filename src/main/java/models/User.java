package models;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import frontend.security.config.roles.SecurityRoles;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User implements UserDetails
{

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "User first name input must be 2 or more signs!")
	@Size(min=2, max=40)
	private String userFirstName;
	
	@NotEmpty(message = "User last name input must be 2 or more signs!")
	@Size(min=2, max=40)
	private String userLastName;
	
	@NotEmpty(message = "Invalid input for User email!")
	@Email
	private String userEmail;
	
	@NotEmpty(message = "User city input must be 3 or more signs!")
	@Size(min=3, max=40)
	private String userCity;
	
	@NotEmpty(message = "User street name input must be 3 or more signs!")
	@Size(min=3, max=40)
	private String userStreetName;
	
	@NotNull(message = "User street number input must be 1 or more signs!")
	@NumberFormat
	private Integer userStreetNumber;
	
	@NotEmpty(message = "User street country input must be 2 or more signs!")
	@Size(min=2, max=40)
	private String userCountryName;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "User date input is wrong dd mm yyy like for examle 12.01.2019") 
	@Past
	private LocalDate userBirthDate;
	
	@NotEmpty(message = "User nickname input must be 5 or more signs!")
	@Size(min=5, max=40)
	private String userNickName;
	
	@NotNull(message = "User sex must be selected!")
	private UserSexState currentUserSexState;
	
	@NotNull(message = "User EmploymentState must be selected!")
	private UserEmploymentState currentEmploymentState;
	
	@NotEmpty(message = "User password must be 8 or more signs!")
	@Size(min=8, max=40)
	private String userPassword;
	
	private Boolean userAccountState;
	private LocalDate userRegistrationDate;
	private Boolean userLoginState;
	
	private List<UserSexState> typesOfUserSex = new ArrayList<UserSexState>();
	private List<UserEmploymentState> typesOfUserEmploymentState = new ArrayList<UserEmploymentState>();
	private SortedSet<UserApplication> userApplicationsSet = new TreeSet<UserApplication>();
	private String role;
	
	
	
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
		this.setUserPassword("tuxtuxtux*");
		this.setUserLoginState(false);
		formaRegDate();
		this.setRole(SecurityRoles.USER.toString());
		this.role = this.getRole();
		// To-do implement a Reference to List or of some sort collection of job
		// applications where belong to this user
	}

	public User(String firstNameOfUser, String lastNameOfUser)
	{
		this.setUserFirstName(firstNameOfUser);
		this.setUserLastName(lastNameOfUser);
		this.setUserAccountState(false);
		this.setUserLoginState(false);

		formaRegDate();
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
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

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public Boolean getUserLoginState()
	{
		return userLoginState;
	}

	public void setUserLoginState(Boolean userLoginState)
	{
		this.userLoginState = userLoginState;
	}

	@Override
	public String toString()
	{
		return "\nUser: "+ userNickName + "\nuserFirstName=" + userFirstName + "\nuserLastName=" + userLastName 
				+ "\nuserEmail=" + userEmail
				+ "\nuserCity=" + userCity + "\nuserStreetName=" + userStreetName + "\nuserCountryName="
				+ userCountryName + "\nuserBirthDate=" + userBirthDate +"\nuserSex=" + currentUserSexState
				+ "\ncurrentEmploymentState=" + currentEmploymentState 
				+ "\nuserStreetNumber=" + userStreetNumber + "\nuserAccountState="
				+ userAccountState + "\nuserRegistrationDate=" + userRegistrationDate +"\nUser pass: "+ userPassword+
				"\nUser logged status: "+userLoginState;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		List<GrantedAuthority> listOfAthorities = new ArrayList<GrantedAuthority>();
		listOfAthorities.add(new SimpleGrantedAuthority(SecurityRoles.USER.toString()));
		
		return listOfAthorities;
	}

	@Override
	public String getPassword()
	{
		return getPassword();
	}

	@Override
	public String getUsername()
	{
		return getUserNickName();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return getUserAccountState();
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return getUserAccountState();
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return getUserAccountState();
	}

	@Override
	public boolean isEnabled()
	{
		return getUserAccountState();
	}

	public boolean insertNewCompanyApplication(UserApplication inputUserCompanyApplication )
	{
		boolean result = false;
		
		if (inputUserCompanyApplication != null) {
			getUserApplicationsSet().add(inputUserCompanyApplication);
			result = true;
		}
		
		return result;
	}

	public SortedSet<UserApplication> getUserApplicationsSet()
	{
		return userApplicationsSet;
	}

	public void setUserApplicationsSet(SortedSet<UserApplication> userApplicationsSet)
	{
		this.userApplicationsSet = userApplicationsSet;
	}

	public boolean deleteJobApplicationByCompanyName(String companyName)
	{
		boolean deletionResult = false;

		for (UserApplication jobApplication : this.getUserApplicationsSet())
		{
		   deletionResult = findByCompanyNameJobApplicationAndDeleteIt(companyName, deletionResult, jobApplication);
		   if (deletionResult == true) {  break;}
		}
		
		return deletionResult;
	}

	private boolean findByCompanyNameJobApplicationAndDeleteIt(String companyName, boolean deletionResult,
			UserApplication jobApplication)
	{
		if(jobApplication.getCompanyName().equals(companyName)) {
			   this.getUserApplicationsSet().remove(jobApplication);
			   deletionResult = true;
			  
		   }
		return deletionResult;
	}


}
