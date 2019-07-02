package com.frontend.controller.ControllersTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.frontend.jobmanger.controller.PasswordRecoveryController;
import com.frontend.jobmanger.controller.RegistrationController;

class PasswordRecoveryControllerTest
{
	private MockMvc mockMvc;
	private PasswordRecoveryController passRecController;
	
	@BeforeEach
	public void setUp()
	{
		passRecController = new PasswordRecoveryController();
		mockMvc = MockMvcBuilders.standaloneSetup(passRecController).build();
	}
	
	@Test
	public void checkThatPassRecoveryPageHasAValidViewName() throws Exception
	{
		mockMvc.perform(get("/recoverUserPass"))
		.andExpect(status().isOk())
		.andExpect(view().name("userPasswordRecovery"));
	}
	
	@Test
	void checkThatSubmitRegUserPassRecoveryCantBeDirectlyInvoked() throws Exception
	{
		mockMvc.perform(post("/submitUserPassRecovery")).andExpect(status().isBadRequest());
	}
	
	@Test
	void checkThatEmptyTextCanBePostedForAllInputFields() throws Exception {
		String blankInput = "";
		String inputForStreetNumber="0";
		mockMvc.perform(post("/submitUserPassRecovery")
				.param("userFirstName", blankInput)
				.param("userLastName", blankInput)
				.param("userEmail", blankInput)
				.param("userCity", blankInput)
				.param("userStreetName", blankInput)
				.param("userStreetNumber", inputForStreetNumber)
				.param("userCountryName", blankInput)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk());	
	}
	

}
