package ServicesTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.User;
import models.UserEmploymentState;
import models.UserSexState;

class InMemoryUserServiceTest
{
	private InMemoryUserService inMemUserService;
    private User mockUser;

    @BeforeEach
	void setup()
	{
		inMemUserService = new InMemoryUserService();
		mockUser = new User();
	}

	@Test
	void checkIfMemUserServiceExist()
	{
		assertNotNull(inMemUserService, "InMemory User Service Object was not created!");
	}

	@Test
	void checkIfUserCanBeAddedToInMemRepo()
	{
		String randomPassword = inMemUserService.genRandomClearPass(12);
		User expectedUser = new User();
		inMemUserService.saveNewUserWithRandomPass(expectedUser,randomPassword);
		User actualUser = inMemUserService.findUser(expectedUser);

		assertNotNull(actualUser, "User was not found in UserRepo");
	}
	
	
	@Test 
	void checkIFUserCanBeAddedWherePasswordNotGive(){
		String randomPassword = "";
		User expectedUser = new User();
		inMemUserService.saveNewUserWithRandomPass(expectedUser,randomPassword);
		User actualUser = inMemUserService.findUser(expectedUser);

		assertNotNull(actualUser, "User was not found in UserRepo");
	}

	@Test
	void checkIfUserServiceCanGenerateRandomStringWithAlphabeticChars()
	{
		int expectLength = 6;
		String result = inMemUserService.genRandomAlphabeticString(expectLength);
		
		assertEquals(expectLength, result.length(),
				"User service wasn't unable to generate a alphabetic string with given length");
	}

	@Test
	void checkIfUserServiceCanGenerateRandomStringWithAlphanumeric()
	{
		int expectLength = 6;
		String result = inMemUserService.genRandomAlphaNumericString(expectLength);
		
		assertEquals(expectLength, result.length(),
				"User service wasn't unable to generate a numeric string with given length");
	}

	@Test
	void checkIfAClearPasswordCanBeGenerated()
	{
		int expectLength = 12;
		String resultClearPassword = inMemUserService.genRandomClearPass(expectLength);
		
		assertEquals(expectLength, resultClearPassword.length(),
				"User service wasn't unable to generate a numeric string with given length");
	}

	@Test
	void checkIfAClearPasswordWithInvalidInputCanBeGenerated()
	{
		int expectedLength = 12;
		int givenLength = 24;
		String resultClearPassword = inMemUserService.genRandomClearPass(givenLength);
		
		assertEquals(expectedLength, resultClearPassword.length(),
				"User service was given the invalid length for clear number , expected generated pass length exactly 12 signs");
	}

	@Test
	void checkIfARandomUserHashPasswordCanBeGenerated()
	{
		int maxLengthOfClearPassword = 12;
		boolean expectedResult = true;
		String resultClearPassword = inMemUserService.genRandomClearPass(maxLengthOfClearPassword);
		// System.out.println("Clear pass: "+ resultClearPassword);
		String hashedUserPass = inMemUserService.genHashedPassword(resultClearPassword);
		// System.out.println("Hashed pass: "+ hashedUserPass);
		
		assertEquals(expectedResult, !hashedUserPass.isEmpty(), "A hash password for User was not generated!");
	}
	
	@Test
	void checkIfAExistingUserCanBeReceivedForLoginAuth()
	{	
		String randomPassword = inMemUserService.genRandomClearPass(12);
		
		inMemUserService.saveNewUserWithRandomPass(userSetUp(mockUser),randomPassword );
		String userNickName = "superduperjavadev01";
		User retrievedUser = inMemUserService.findUserByNickname(userNickName);

		assertNotNull(retrievedUser);
	}

	@Test
	void checkIfAUserWithGivenHashCanBeSaved() {
		boolean expectedResult = true;
		boolean actualResult = inMemUserService.saveUserWithGivenHashPass(userSetUp(mockUser));
				
	    assertEquals(expectedResult,actualResult,"The User with given Hash Password was saved");
	}
	
	@Test
	void checkIfGivenPasswordForAGivenUserIsAValidOne() {
		boolean expectedAuthResponse = true;
		inMemUserService.saveUserWithGivenHashPass(userSetUp(mockUser));
		String givenUserNickName = "superduperjavadev01";
		String givenUserPassword =  "tuxtuxtux*";

		boolean actualAuthResponse = inMemUserService.authUserByGivenNickNameAndPass(givenUserNickName,givenUserPassword);
        
		assertEquals(expectedAuthResponse,actualAuthResponse,"Given user nickname and password were invalid credentials!");
	}
	
	private User userSetUp(User givenUser)
	{
		String clearPassword = "tuxtuxtux*";
		String protectedPassword = inMemUserService.genHashedPassword(clearPassword);
		givenUser.setUserFirstName("John");
		givenUser.setUserLastName("Smith");
		givenUser.setUserBirthDate("01.04.1957");
		givenUser.setUserEmail("hardcorejavadev@hotmail.com");
		givenUser.setUserCity("Detroit");
		givenUser.setUserStreetName("Bankerstreet");
		givenUser.setUserStreetNumber(1234556);
		givenUser.setUserCountryName("USA");
		givenUser.setUserNickName("superduperjavadev01");
		givenUser.setCurrentUserSexState(UserSexState.MALE);
		givenUser.setCurrentEmploymentState(UserEmploymentState.SELFEMPLOYED);
		givenUser.setUserPassword(protectedPassword);

		return givenUser;
	}
		
}
