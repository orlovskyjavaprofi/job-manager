package ControllersTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.frontend.jobmanger.controller.ComposerForUserApplicationController;
import com.frontend.jobmanger.controller.LoginController;
import com.frontend.jobmanger.controller.RegistrationController;
import com.frontend.jobmanger.controller.UserAccountOfficeController;

class CreateNewApplicationControllerTest
{
	   private MockMvc mockMvcLogin;
	   private LoginController userLoginController;
	   private RegistrationController userRegController;
	   private UserAccountOfficeController userAccountOfficeController;
       private ComposerForUserApplicationController userComposerAppController; 
       
	   @BeforeEach
		public void setUp()
		{
	    	   userLoginController = new LoginController();
	    	   userRegController =  new RegistrationController();   		
	    	   userAccountOfficeController = new UserAccountOfficeController();
	    	   userComposerAppController = new ComposerForUserApplicationController();
		   mockMvcLogin = MockMvcBuilders.standaloneSetup(userAccountOfficeController,userLoginController,
	  			          userRegController, userComposerAppController).build();
		}
	   
	    @Test
		public void checkThatCreateNewApplicationIsNotReachableForAnonymNothAuthUsers() throws Exception
		{
	       	mockMvcLogin.perform(get("/memberarea/userAccountOffice/composeNewApp"))
	       	.andExpect(status().isForbidden());
		}
	   
	   
}
