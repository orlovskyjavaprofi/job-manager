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

}
