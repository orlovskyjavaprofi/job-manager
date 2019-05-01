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
import com.frontend.jobmanger.controller.RegistrationController;
import org.junit.runners.Parameterized;

import models.UserSexState;


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
	void runWithEnumsSexState() throws Exception
	{
		for (UserSexState currentSex : UserSexState.values())
		{
			checkThatNoEmptyTextCanBePostedForAllInputFields(currentSex);
		}
	}
	

    void checkThatNoEmptyTextCanBePostedForAllInputFields(UserSexState currentSexState) throws Exception
    {
    	System.out.println("Params: "+currentSexState+"\n");
		String blankInput = "";
        String inputForStreetNumber="0";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userFirstName", blankInput)
				.param("userLastName", blankInput)
				.param("userBirthDate", blankInput)
				.param("userEmail", blankInput)
				.param("userCity", blankInput)
				.param("userStreetName", blankInput)
				.param("userStreetNumber", inputForStreetNumber)
				.param("userCountryName", blankInput)
				.param("userNickName", blankInput)
				.param("typesOfUserSex", currentSexState.toString())
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
	@Test
    void checkThatNoEmptyTextCanBePostForUserBirthDay() throws Exception
    {
		String userBirthDate = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userBirthDate", userBirthDate)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest());		
    }
	@Test
    void checkThatNoEmptyTextCanBePostForUserEmail() throws Exception
    {
		String userEmail = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userEmail", userEmail)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest());		
    }
	
	@Test
    void checkThatNoEmptyTextCanBePostForUserCity() throws Exception
    {
		String userCity = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userCity", userCity)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest());		
    }
	
	@Test
    void checkThatNoEmptyTextCanBePostForUserStreetName() throws Exception
    {
		String userStreetName = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userStreetName", userStreetName)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest());		
    }
	
	@Test
    void checkThatNoEmptyTextCanBePostForUserStreetNumber() throws Exception
    {
		String userStreetNumber = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userStreetNumber", userStreetNumber)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest());		
    }
	
	@Test
    void checkThatNoEmptyTextCanBePostForUserCountry() throws Exception
    {
		String userCountryName = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userCountryName", userCountryName)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest());		
    }
	
	@Test
    void checkThatNoEmptyTextCanBePostForUserNickname() throws Exception
    {
		String userNickName = "";
		mockMvc.perform(post("/submitNewUserReg")
				.param("userNickName", userNickName)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest());		
    }
}