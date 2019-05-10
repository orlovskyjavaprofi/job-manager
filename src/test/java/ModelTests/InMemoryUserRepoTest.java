package ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.InMemoryUserRepo;
import models.User;

public class InMemoryUserRepoTest
{
   private InMemoryUserRepo inMemUserRepo;
   
   @BeforeEach
	void setup() {
	   inMemUserRepo = new InMemoryUserRepo();
   }
   
   @Test
	void checkIfinMemUserRepoExist(){
		assertNotNull(inMemUserRepo,"User Inmemory Repo Object was not created!");
   }
   
   @Test
   void checkIfAUserCanBeAddToInMemRepo() {
	   Boolean expectedResult = true;
	   User userObj= new User();
	   Boolean actualResult = inMemUserRepo.addUser(userObj);
	   assertEquals(expectedResult, actualResult, "User wasn't added to inmemory repo!");
   }
   
   @Test
   void checkIfAGivenUserCanBeFound() {
	   User userObj = new User();
	   inMemUserRepo.addUser(userObj);
	   assertNotNull(inMemUserRepo.findUser(userObj),"Given User can't be found");
   }
}
