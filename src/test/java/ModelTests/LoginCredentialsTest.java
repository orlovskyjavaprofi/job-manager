package ModelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.LoginCredentials;

class LoginCredentialsTest
{

	private LoginCredentials loginCredentObj;

	@BeforeEach
	void setup()
	{
		loginCredentObj = new LoginCredentials();
		assertNotNull(loginCredentObj, "login loginCredentObj cant be created!");
	}

	@Test
	void checkIfloginCredentObjObjExist(){
	    assertNotNull(loginCredentObj, "cant create a logincredentialsObj");	
	}
}
