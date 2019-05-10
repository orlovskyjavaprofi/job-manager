package ServicesTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.User;

class InMemoryUserServiceTest
{
	private InMemoryUserService inMemUserService;

	@BeforeEach
	void setup()
	{
		inMemUserService = new InMemoryUserService();
	}

	@Test
	void checkIfMemUserServiceExist()
	{
		assertNotNull(inMemUserService,"InMemory User Service Object was not created!");
	}

	@Test
	void checkIfUserCanBeAddedToInMemRepo() {
		User expectedUser = new User();
		inMemUserService.saveNewUser(expectedUser);
		User actualUser = inMemUserService.findUser(expectedUser);
		
		assertNotNull(actualUser,"User was not found in UserRepo");
	}
	
	@Test
	void checkIfUserServiceCanGenerateRandomStringWithAlphabeticChars(){
		int expectLength = 6;
		String result = inMemUserService.genRandomAlphabeticString(expectLength);
	    assertEquals(expectLength,result.length(),"User service wasn't unable to generate a alphabetic string with given length");
	}
	
	@Test
	void checkIfUserServiceCanGenerateRandomStringWithAlphanumeric() {
		int expectLength = 6;
		String result = inMemUserService.genRandomAlphaNumericString(expectLength);
		assertEquals(expectLength,result.length(),"User service wasn't unable to generate a numeric string with given length");
	}
	
	@Test
	void checkIfAClearPasswordCanBeGenerated() {
		int expectLength = 12;
		String resultClearPassword= inMemUserService.genRandomClearPass(expectLength);
		assertEquals(expectLength,resultClearPassword.length(),"User service wasn't unable to generate a numeric string with given length");
	}
	
	@Test
	void checkIfAClearPasswordWithInvalidInputCanBeGenerated() {
		int expectedLength =12;
		int givenLength = 24;
		String resultClearPassword= inMemUserService.genRandomClearPass(givenLength);
		assertEquals(expectedLength,resultClearPassword.length(),"User service was given the invalid length for clear number , expected generated pass length exactly 12 signs");
	}
	
	@Test
	void checkIfARandomUserHashPasswordCanBeGenerated() {
		int maxLengthOfClearPassword = 12;
		boolean expectedResult = true;
		String resultClearPassword= inMemUserService.genRandomClearPass(maxLengthOfClearPassword);
//		System.out.println("Clear pass: "+ resultClearPassword);
		String hashedUserPass = inMemUserService.genHashedPassword(resultClearPassword);
//		System.out.println("Hashed pass: "+ hashedUserPass);
		assertEquals(expectedResult, !hashedUserPass.isEmpty(), "A hash password for User was not generated!");
	}
	
}
