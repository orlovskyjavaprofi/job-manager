package ControllersTests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.apache.catalina.filters.SetCharacterEncodingFilter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import controllers.RegistrationController;


class RegistrationControllerTest
{
	private MockMvc mockMvc;
    private RegistrationController regController;  
	
    @BeforeEach
    public void setUp() {
    	   regController = new RegistrationController();
    	   mockMvc = MockMvcBuilders.standaloneSetup(regController).build();
    
    }
	
    @Test
    public void checkThatRegistrationPageHasAValidViewName() throws Exception {
    	   String expectedResult="New member registration";
    	   mockMvc.perform(get("/regnewuser"))
    	        
    	   .andExpect(status().isOk())
    	   .andExpect(view().name("regnewuserform"));
    }
}
