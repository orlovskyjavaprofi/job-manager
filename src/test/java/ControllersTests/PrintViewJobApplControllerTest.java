package ControllersTests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import models.CompanySalutationType;
import models.CompanyType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringJUnit4ClassRunner.class)
class PrintViewJobApplControllerTest extends BasicControllerTest
{
	@Test
	public void checkPrintViewJobApplNotReachableForAnon() throws Exception
	{
		mockMvcLogin.perform(get("/memberarea/printAllUserJobAppls"))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void checkIfPrintViewJobApplCanBeDisplayed() throws Exception {
		
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
                                  mockMvcLogin.perform(get("/memberarea/userAccountOffice/composeNewApp/?uName=testdude000"))
                                          .andExpect(status().isOk())
                                          .andExpect(view().name("memberarea/composeAppForAUser"))
                          )
                          .andDo(accessUserAccountOfficeComposePageMvcResult ->
                             mockMvcLogin.perform(post("/submitNewJobApplicationForUser")
                            		         .param("userNickName", "testdude000")
                            		         .param("dateWhenApplicationWasSend", "20.04.2018")
                            		         .param("companyCountryName", "USA")
                            		         .param("companyCityName", "Alexandria")
                            		         .param("companyIndustry","Renewable energy")
                            		         .param("companyAmountOfEmployee", "250")
                            		         .param("currentCompanyType", CompanyType.MIDDLE.toString())
                            		         .param("companyContactEmail", "coolcompany@siliconvaley.com")
                            		         .param("typesOFCompanySalutationType", CompanySalutationType.Mr.toString())
                            		         .param("companyContactLastName", "Uberson")        
                            		         .param("companyName", "Lockheed Martin")  
                            		         .param("jobTittleOfApplicationForCompany", "Software developer")  
                            		 )
                            .andExpect(status().isOk())
                           .andExpect(view().name("memberarea/userAccountOffice"))
                          )
                          .andDo(accessUserAccountOfficeComposePageMvcResult ->
                          mockMvcLogin.perform(post("/submitNewJobApplicationForUser")
                         		         .param("userNickName", "testdude000")
                         		         .param("dateWhenApplicationWasSend", "20.05.2018")
                         		         .param("companyCountryName", "USA")
                         		         .param("companyCityName", "Austin")
                         		         .param("companyIndustry","Logistics")
                         		         .param("companyAmountOfEmployee", "100")
                         		         .param("currentCompanyType", CompanyType.MIDDLE.toString())
                         		         .param("companyContactEmail", "coolcompany@logisticsustexas.com")
                         		         .param("typesOFCompanySalutationType", CompanySalutationType.Mrs.toString())
                         		         .param("companyContactLastName", "Mayfly")        
                         		         .param("companyName", "Trucksloaded")  
                         		         .param("jobTittleOfApplicationForCompany", "Database administrator")  
                         		 )
                         .andExpect(status().isOk())
                        .andExpect(view().name("memberarea/userAccountOffice"))
                       )
                     .andDo(accessUserAccountOfficeComposePageMvcResult ->
                      mockMvcLogin.perform(get("/memberarea/printAllUserJobAppls/?uName=testdude000"))           		
                      .andExpect(status().isOk())
                      .andExpect(view().name("memberarea/printViewAllJobApplOfUser"))
                     )
                  );
		
	}
}
