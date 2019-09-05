package ServicesTests;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.CompanySalutationType;
import models.CompanyType;
import models.User;
import models.UserApplication;
import models.UserEmploymentState;
import models.UserSexState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.SortedSet;
import java.util.TreeSet;

class InMemoryUserServiceTest {
    private InMemoryUserService inMemUserService;
    private User mockUser, anotherMockUser;
    private UserApplication userMockJobApplication;
    
    @BeforeEach
    void setup() {
        inMemUserService = new InMemoryUserService();
        mockUser = new User();
        userMockJobApplication = new UserApplication();
    }

    @Test
    void checkIfMemUserServiceExist() {
        assertNotNull(inMemUserService, "InMemory User Service Object was not created!");
    }

    @Test
    void checkIfUserCanBeAddedToInMemRepo() {
        String randomPassword = inMemUserService.genRandomClearPass(12);
        User expectedUser = new User();
        inMemUserService.saveNewUserWithRandomPass(expectedUser, randomPassword);
        User actualUser = inMemUserService.findUser(expectedUser);

        assertNotNull(actualUser, "User was not found in UserRepo");
    }


    @Test
    void checkIFUserCanBeAddedWherePasswordNotGive() {
        String randomPassword = "";
        User expectedUser = new User();
        inMemUserService.saveNewUserWithRandomPass(expectedUser, randomPassword);
        User actualUser = inMemUserService.findUser(expectedUser);

        assertNotNull(actualUser, "User was not found in UserRepo");
    }

    @Test
    void checkIfUserServiceCanGenerateRandomStringWithAlphabeticChars() {
        int expectLength = 6;
        String result = inMemUserService.genRandomAlphabeticString(expectLength);

        assertEquals(expectLength, result.length(),
                "User service wasn't unable to generate a alphabetic string with given length");
    }

    @Test
    void checkIfUserServiceCanGenerateRandomStringWithAlphanumeric() {
        int expectLength = 6;
        String result = inMemUserService.genRandomAlphaNumericString(expectLength);

        assertEquals(expectLength, result.length(),
                "User service wasn't unable to generate a numeric string with given length");
    }

    @Test
    void checkIfAClearPasswordCanBeGenerated() {
        int expectLength = 12;
        String resultClearPassword = inMemUserService.genRandomClearPass(expectLength);

        assertEquals(expectLength, resultClearPassword.length(),
                "User service wasn't unable to generate a numeric string with given length");
    }

    @Test
    void checkIfAClearPasswordWithInvalidInputCanBeGenerated() {
        int expectedLength = 12;
        int givenLength = 24;
        String resultClearPassword = inMemUserService.genRandomClearPass(givenLength);

        assertEquals(expectedLength, resultClearPassword.length(),
                "User service was given the invalid length for clear number , expected generated pass length exactly 12 signs");
    }

    @Test
    void checkIfARandomUserHashPasswordCanBeGenerated() {
        int maxLengthOfClearPassword = 12;
        boolean expectedResult = true;
        String resultClearPassword = inMemUserService.genRandomClearPass(maxLengthOfClearPassword);
        // System.out.println("Clear pass: "+ resultClearPassword);
        String hashedUserPass = inMemUserService.genHashedPassword(resultClearPassword);
        // System.out.println("Hashed pass: "+ hashedUserPass);

        assertEquals(expectedResult, !hashedUserPass.isEmpty(), "A hash password for User was not generated!");
    }

    @Test
    void checkIfAExistingUserCanBeReceivedForLoginAuth() {
        String randomPassword = inMemUserService.genRandomClearPass(12);

        inMemUserService.saveNewUserWithRandomPass(userSetUp(mockUser), randomPassword);
        String userNickName = "superduperjavadev01";
        User retrievedUser = inMemUserService.findUserByNickname(userNickName);

        assertNotNull(retrievedUser);
    }

    @Test
    void checkIfAUserWithGivenHashCanBeSaved() {
        boolean expectedResult = true;
        boolean actualResult = inMemUserService.saveUserWithGivenHashPass(userSetUp(mockUser));

        assertEquals(expectedResult, actualResult, "The User with given Hash Password was saved");
    }

    @Test
    void checkIfGivenPasswordForAGivenUserIsAValidOne() {
        boolean expectedAuthResponse = true;
        inMemUserService.saveUserWithGivenHashPass(userSetUp(mockUser));
        String givenUserNickName = "superduperjavadev01";
        String givenUserPassword = "tuxtuxtux*";

        boolean actualAuthResponse = inMemUserService.authUserByGivenNickNameAndPass(givenUserNickName, givenUserPassword);

        assertEquals(expectedAuthResponse, actualAuthResponse, "Given user nickname and password were invalid credentials!");
    }

    @Test
    void testGetNumberOfRegisteredUsers() {
        inMemUserService.saveUserWithGivenHashPass(userSetUp(mockUser));
        assertEquals(1, inMemUserService.getNumberOfRegisteredUsers(),
                "Number of registered users seems OK.");
        anotherMockUser = new User();
        inMemUserService.saveUserWithGivenHashPass(userSetUp(anotherMockUser));
        assertEquals(2, inMemUserService.getNumberOfRegisteredUsers(),
                "Number of registered users seems OK.");
    }
    
    @Test
    void testIfGivenUserJobApplicationCanBeFound() {
    	    User userAtTest;
    	    UserApplication actualUserJobApplication  = new UserApplication();
    	    
	    	Integer companySize = 250;
	    	String companyName = "SUSE";
	    	String companyCityName = "Alexandria";
	    	String companyContactEmail = "coolcompany@siliconvaley.com";
	    	String companyCountryName = "USA";
	    	String companyTypeSize = CompanyType.MIDDLE.toString();
	    	CompanySalutationType companySalut = CompanySalutationType.Mrs;
	    	String companyDate = "20.04.2018";
	    	String companyContactLastName = "Uberson";
    	    String companyJobTitle ="Software Developer";
    	    String companyIndustry ="Auerospace";
	    	initMockUserJobApp(companySize, companyName, companyCityName, companyContactEmail, companyCountryName,
					companyTypeSize, companySalut, companyDate, companyContactLastName,companyJobTitle,companyIndustry);
	    	
	    	userAtTest=userSetUp(mockUser);
	    	userAtTest.getUserApplicationsSet().add(userMockJobApplication);
	 	inMemUserService.saveUserWithGivenHashPass(userAtTest);
	    	mockUser = inMemUserService.findUserByNickname("superduperjavadev01");
	    	actualUserJobApplication = inMemUserService.searchForFullMatchOfUserJobAppl(
	    			     userAtTest.getUserNickName(), 
	    			     companyName, companyContactLastName, 
	    			     companyContactEmail, companyJobTitle,
	    			     companyDate, companyIndustry );
	    	    	
	    	System.out.println("test");
	    	System.out.println(companyName);
	    	System.out.println(companyContactLastName);
	    	System.out.println(companyContactEmail);
	    	System.out.println(companyJobTitle);
	    	System.out.println(companyDate);
	    	System.out.println(companyIndustry);
	    	assertEquals(userMockJobApplication, actualUserJobApplication, "The complete full match for user job application wasnt found! " );
        
    }

	private void initMockUserJobApp(Integer companySize, String companyName, String companyCityName,
			String companyContactEmail, String companyCountryName, String companyTypeSize,
			CompanySalutationType companySalut, String companyDate, String companyContactLastName, String jobTitle, String industryType)
	{
		userMockJobApplication.setCompanyAmountOfEmployee(companySize);
		userMockJobApplication.setCompanyName(companyName);
		userMockJobApplication.setCompanyCityName(companyCityName);
		userMockJobApplication.setCompanyContactEmail(companyContactEmail);
		userMockJobApplication.setCompanyCountryName(companyCountryName);
		userMockJobApplication.setCompanyIndustry(companyTypeSize);
		userMockJobApplication.setCurrentCompanySalutationType(companySalut);
		userMockJobApplication.setDateWhenApplicationWasSend(companyDate);
		userMockJobApplication.setCompanyContactLastName(companyContactLastName);
		userMockJobApplication.setJobTittleOfApplicationForCompany(jobTitle);
		userMockJobApplication.setCompanyIndustry(industryType);
	}
    
    private User userSetUp(User givenUser) {
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
