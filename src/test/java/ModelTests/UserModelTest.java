package ModelTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.User;

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
	
}
