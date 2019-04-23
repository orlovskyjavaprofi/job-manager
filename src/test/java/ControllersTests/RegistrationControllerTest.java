package ControllersTests;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.frontend.jobmanger.controller.RegistrationController;


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
    	   mockMvc.perform(get("/regnewuser"))
    	        
    	   .andExpect(status().isOk())
    	   .andExpect(view().name("regnewuserform"));
    }
    
}