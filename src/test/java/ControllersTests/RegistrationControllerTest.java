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

import models.UserEmploymentState;
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
			for (UserEmploymentState currentEmploymentState: UserEmploymentState.values()) {
				checkThatNoEmptyTextCanBePostedForAllInputFields(currentSex, currentEmploymentState);
			}
		}
	}

    void checkThatNoEmptyTextCanBePostedForAllInputFields(UserSexState currentSexState, 
    		                     UserEmploymentState currentEmploymentState) throws Exception
    {
		System.out.println("Params sex: " + currentSexState );
		System.out.println("Params employment state: " + currentEmploymentState + "\n");
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
				.param("currentEmploymentState", currentEmploymentState.toString())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk());		
    }
	
//    @Test
//    void checkIfASubmitNewUserCanBeRegistered() throws Exception {
//    	
//    	mockMvc.perform(post("/submitNewUserReg")
//				.param("userFirstName", "John")
//				.param("userLastName", "Smith")
//				.param("userBirthDate", "01.04.1957")
//				.param("userEmail", "hardcorejavadev@hotmail.com")
//				.param("userCity", "Detroit")
//				.param("userStreetName", "Bankerstreet")
//				.param("userStreetNumber", "1234556")
//				.param("userCountryName", "USA")
//				.param("userNickName", "superduperjavadev01")
//				.param("typesOfUserSex", "MALE")
//				.param("currentEmploymentState", "SELFEMPLOYED")
//				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
//				.andExpect(status().isOk())
//				.andExpect(view().name("newUserAddConfirmation"));
//    }
    
    
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