package ControllersTests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.frontend.jobmanger.controller.RegistrationController;

class RegistrationControllerTest
{
	private MockMvc mockMvc;
	private RegistrationController regController;

	@BeforeEach
	public void setUp()
	{
		regController = new RegistrationController();
		mockMvc = MockMvcBuilders.standaloneSetup(regController).build();
	}

	@Test
	public void checkThatRegistrationPageHasAValidViewName() throws Exception
	{
		mockMvc.perform(get("/regnewuser"))
		.andExpect(status().isOk())
		.andExpect(view().name("regnewuserform"));	
	}

	@Test
	void checkThatSubmitNewUserCantBeDirectlyInvoked() throws Exception
	{
		mockMvc.perform(post("/submitNewUserReg")).andExpect(status().isBadRequest());
	}
	
	@Test
    void checkThatNoEmptyTextCanBePosForUserFirstName() throws Exception
    {
		String userFirstName = "";
		String userLastName = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userFirstName", userFirstName)
				.param("userLastName", userLastName)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk());		
    }
	@Test
    void checkThatNoEmptyTextCanBePosForUserLastName() throws Exception
    {
		String userLastName = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userLastName", userLastName)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest());		
    }
	
}