package ControllersTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.frontend.jobmanger.controller.JobManagerErrorController;

class ErrorControllerTest
{
	private MockMvc mockMvc;
	private JobManagerErrorController errorController;
	
	@BeforeEach
	public void setUp()
	{
		errorController = new JobManagerErrorController();
		mockMvc = MockMvcBuilders.standaloneSetup(errorController).build();
	}

	@Test
	public void checkThatIfUserCallErrorPage() throws Exception
	{
		mockMvc.perform(get("/error"))
		.andExpect(status().isOk())
		.andExpect(view().name("errorPage"));	
	}
	
	@Test
	public void checkThatIfUserCallNonExistPage() throws Exception
	{
		mockMvc.perform(get("/btrd"))
		.andExpect(status().isNotFound());
	}
	
}
