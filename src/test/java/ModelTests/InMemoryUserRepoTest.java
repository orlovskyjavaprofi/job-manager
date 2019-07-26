package ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.CompanySalutationType;
import models.CompanyType;
import models.InMemoryUserRepo;
import models.User;
import models.UserApplication;
import models.UserEmploymentState;
import models.UserSexState;

public class InMemoryUserRepoTest
{
   private InMemoryUserRepo inMemUserRepo;
   private InMemoryUserService inMemUserService;
   private User userObj;
   private UserApplication userAppl;
   
   @BeforeEach
	void setup() {
	   inMemUserRepo = new InMemoryUserRepo();
	   userObj= new User();
	   inMemUserService = new InMemoryUserService();
	   userAppl = new UserApplication();
   }
   
   @Test
	void checkIfinMemUserRepoExist(){
		assertNotNull(inMemUserRepo,"User Inmemory Repo Object was not created!");
   }
   
   @Test
   void checkIfAUserCanBeAddToInMemRepo() {
	   Boolean expectedResult = true;
	  
	   Boolean actualResult = inMemUserRepo.addUser(userObj);
	   assertEquals(expectedResult, actualResult, "User wasn't added to inmemory repo!");
   }
   
   @Test
   void checkIfAGivenUserCanBeFound() {
	   inMemUserRepo.addUser(userObj);
	   assertNotNull(inMemUserRepo.findUser(userObj),"Given User can't be found");
   }
   
   @Test
   void checkIfAGivenNicknameOfUserCanBeRetrieved() {
		User mockUser= this.userSetUp(userObj);
		inMemUserRepo.addUser(mockUser);
		String userNickName = "superduperjavadev01";
		User retrievedUser = inMemUserRepo.findUserByGivenName(userNickName);
		assertNotNull(retrievedUser,"User by given name cant be found!");
   }
   
   @Test
   void checkIfaNewUserCanInsertJobApplicationToHisSetsOfApplications() {
	   boolean expectedResult = true;
	   User mockUser= this.userSetUp(userObj);
	   inMemUserRepo.addUser(mockUser);
	   setUpApplicationForInsertToUserList();
	   boolean actualResult = inMemUserRepo.insertJobApplicationToUserSet(mockUser, userAppl);
	   assertEquals(expectedResult, actualResult, "User application can't be inserted via inmem repo service!");
   }
   
   private void setUpApplicationForInsertToUserList()
	{
		userAppl.setCompanyAmountOfEmployee(250);
		userAppl.setCompanyCityName("Alexandria");
		userAppl.setCompanyContactEmail("coolcompany@siliconvaley.com");
		userAppl.setCompanyCountryName("USA");
		userAppl.setCompanyIndustry(CompanyType.MIDDLE.toString());
		userAppl.setCurrentCompanySalutationType(CompanySalutationType.Mrs);
		userAppl.setDateWhenApplicationWasSend("20.04.2018");
		userAppl.setCompanyContactLastName("Uberson");
	}
  
   private User userSetUp(User givenUser)
	{
		String clearPassword ="tuxtuxtux*";
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
