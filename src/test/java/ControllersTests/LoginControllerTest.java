package ControllersTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.frontend.jobmanger.controller.LoginController;

class LoginControllerTest
{
	private MockMvc mockMvc;
	private LoginController loginController;
        
	@BeforeEach
	public void setUp()
	{
		loginController = new LoginController();
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	public void checkThatLoginPageHasAValidViewNameForLogin() throws Exception
	{
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("loginUserPage"));	
	}
	
	@Test
	public void checkThatLoginPageHasAValidViewNameForSlash() throws Exception
	{
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("loginUserPage"));	
	}
	
	@Test
	public void checkIfUserInputBlankInputForLogin() throws Exception{
       String blankInput = "";
       mockMvc.perform(post("/loginAsUserToJobManger")
    			.param("userNickName", blankInput)
			.param("userPassword", blankInput)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
        .andExpect(view().name("loginUserPage"));
	}
	
}
