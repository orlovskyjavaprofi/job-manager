package ControllersTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class ShowJobApplControllerTest extends BasicControllerTest
{

	 @Test
	 public void checkShowListIsNotReachableForAnonymNothAuthUsers() throws Exception {
		  mockMvcLogin.perform(get("/memberarea/userAccountOffice/showListOfUserJobApplications"))
          .andExpect(status().isForbidden());
	 }
	 
	 @Test
	 public void checkIfValidUserCanAccessListViewAppl() throws Exception{
		  mockMvcLogin.perform(post("/submitNewUserReg")
	                .param("userFirstName", "Thomas")
	                .param("userLastName", "Jefferson")
	                .param("userBirthDate", "01.12.1978")
	                .param("userEmail", "cooldude@io.com")
	                .param("userCity", "Detroit")
	                .param("userStreetName", "BerlinerStreet")
	                .param("userStreetNumber", "123")
	                .param("userCountryName", "USA")
	                .param("userNickName", "testdude000")
	                .param("typesOfUserSex", "MALE")
	                .param("currentEmploymentState", "SELFEMPLOYED")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
	                .andDo(mvcResult -> mockMvcLogin.perform(post("/loginAsUserToJobManger")
	                        .param("userNickName", "testdude000")
	                        .param("userPassword", "tuxtux123456"))
	                        .andExpect(status().isOk())
	                        .andExpect(view().name("memberarea/landingUserMemberAreaPage"))
	                        .andDo(accessUserAccountOfficeMvcResult -> mockMvcLogin.perform(get("/memberarea/userAccountOffice/?uName=testdude000"))
	                                .andExpect(status().isOk())
	                                .andExpect(view().name("memberarea/userAccountOffice"))
	                        )
	                        .andDo(accessUserAccountOfficeComposePageMvcResult ->
	                                mockMvcLogin.perform(get("/memberarea/userAccountOffice/showListOfUserJobApplications/?uName=testdude000"))
	                                        .andExpect(status().isOk())
	                                        .andExpect(view().name("memberarea/listOfUserJobApplications"))
	                        )
	                );
	 }

}
