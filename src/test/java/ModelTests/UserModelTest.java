package ModelTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.User;
import models.UserEmploymentState;

class UserModelTest
{
    private User userObj;
	
	@BeforeEach
	void setup() {
		userObj = new User();
	}
   
	@Test
	void checkIfUserObjExist(){
		
		assertNotNull(userObj,"User Object was not created!");
	}

	@Test
	void checkIfuserNameSetUp() {
		String firstNameOfUser = "John";
		userObj.setUserFirstName(firstNameOfUser);
		
		assertEquals(firstNameOfUser,userObj.getUserFirstName(), "Firstname of user was not correctly set up!");
	}
	
	@Test
	void checkIfuserLastnameSetUp() {
		String lastNameOfUser = "Doe";
		userObj.setUserLastName(lastNameOfUser);
		
		assertEquals(lastNameOfUser,userObj.getUserLastName(), "Lastname of user was not correctly set up!");
	}
	
	@Test
	void checkIfUserFirstAndLastNameSetUp() {
		String firstNameOfUser = "John";
		String lastNameOfUser = "Doe";
		userObj.setUserFirstName(firstNameOfUser);
		userObj.setUserLastName(lastNameOfUser);
		assertEquals(firstNameOfUser+" "+lastNameOfUser,userObj.getUserFirstName()+" "+userObj.getUserLastName(), "Firstname and Lastname of user was not correctly set up!");
	}
	
	@Test
	void checkIfAUserCanBeCreatedWithFirstAndLastName() {
		String firstNameOfUser = "John";
		String lastNameOfUser = "Doe";
		User newUser = new User(firstNameOfUser,lastNameOfUser );
		assertEquals(firstNameOfUser+" "+lastNameOfUser,newUser.getUserFirstName()+" "+newUser.getUserLastName(), "Firstname and Lastname of user was not correctly set up!");
	}
	
	@Test
	void checkIfAUserBirthdayCanBetSetUp() {
		String userBirthDay = "25.03.1978";		
		userObj.setUserBirthDate(userBirthDay);
		assertEquals(userBirthDay,userObj.getUserBirthDate());
	}
	
	@Test
	void checkIfuserEmailSetUp() {
		String userEmail = "johndoe@redhat.com";
		userObj.setUserEmail(userEmail);
		
		assertEquals(userEmail,userObj.getUserEmail(), "User email was not set up!");
	}
	
	@Test
	void checkIfuserCitySetUp() {
		String userCity = "Berlin";
		userObj.setUserCity(userCity);
		
		assertEquals(userCity,userObj.getUserCity(), "User city was not set up!");
	}
	
	@Test
	void checkIfuserStreetnameSetUp() {
		String userStreetName = "Alexanderplatz";
		userObj.setUserStreetName(userStreetName);
		
		assertEquals(userStreetName,userObj.getUserStreetName(), "User street name was not set up!");
	}
	
	@Test
	void checkIfuserStreetnumberSetUp() {
		Integer userStreetNumber = 3456;
		userObj.setUserStreetNumber(userStreetNumber);
		
		assertEquals(userStreetNumber,userObj.getUserStreetNumber(), "User street number was not set up!");
	}
	
	@Test
	void checkIfuserCountrySetUp() {
		String userCountryName = "Germany";
		userObj.setUserCountryName(userCountryName);
		
		assertEquals(userCountryName,userObj.getUserCountryName(), "User country name was not set up!");
	}
	
	@Test
	void checkIfuserEmploymentStateUnemployedSetUp() {
		UserEmploymentState currentEmploymentState = UserEmploymentState.UNEMPLOYED;
		
		assertEquals(currentEmploymentState,userObj.getCurrentEmploymentState(), "User unemployed state was not set up!");
	}
	@Test
	void checkIfuserEmploymentStateEmployedSetUp() {
		UserEmploymentState currentEmploymentState = UserEmploymentState.EMPLOYED;
		userObj.setCurrentEmploymentState(UserEmploymentState.EMPLOYED);
		assertEquals(currentEmploymentState,userObj.getCurrentEmploymentState(), "User Employed state was not set up!");
	}
	
	@Test
	void checkIfuserEmploymentStateSelfEmployedSetUp() {
		UserEmploymentState currentEmploymentState = UserEmploymentState.SELFEMPLOYED;
		userObj.setCurrentEmploymentState(UserEmploymentState.SELFEMPLOYED);
		assertEquals(currentEmploymentState,userObj.getCurrentEmploymentState(), "User Selfemployed state was not set up!");
	}
}
