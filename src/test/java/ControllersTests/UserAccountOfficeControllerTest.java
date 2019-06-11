package ControllersTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.frontend.jobmanger.controller.UserAccountOfficeController;

class UserAccountOfficeControllerTest
{
   private MockMvc mockMvcLogin;
   private UserAccountOfficeController userOfficeController;
   
    @BeforeEach
	public void setUp()
	{
    	   userOfficeController = new UserAccountOfficeController();
	   mockMvcLogin = MockMvcBuilders.standaloneSetup(userOfficeController).build();
	}

    @Test
	public void checkThatUserAccountOfficeIsNotReachableForAnonymNothAuthUsers() throws Exception
	{
    	mockMvcLogin.perform(get("/memberarea/userAccountOffice"))
		.andExpect(status().isForbidden())
		.andExpect(view().name("restrictedAccess"));	
	}
    
    //write next test for access of user account office area 
    //test should be like reg user 
    //using new reg user cridentials login to the page
    //then access the user account office page , it should return 200 Ok!
}
