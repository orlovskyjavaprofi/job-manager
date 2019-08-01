package ControllersTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class ShowJobApplControllerTest extends BasicControllerTest
{

	 @Test
	 public void checkShowListIsNotReachableForAnonymNothAuthUsers() throws Exception {
		  mockMvcLogin.perform(get("/memberarea/userAccountOffice/showListOfUserJobApplications"))
          .andExpect(status().isForbidden());
	 }

}
