package ModelTests;

import static org.junit.jupiter.api.Assertions.*;

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
	
}
