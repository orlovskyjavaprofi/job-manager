package com.frontend.controller.ControllersTests;

import com.frontend.jobmanger.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@WebMvcTest()
@AutoConfigureMockMvc
class CreateNewApplicationControllerTest {
    private MockMvc mockMvcLogin;
    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setUp() {
        mockMvcLogin = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void checkThatCreateNewApplicationIsNotReachableForAnonymNothAuthUsers() throws Exception {
        mockMvcLogin.perform(get("/memberarea/userAccountOffice/composeNewApp"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void checkIfAvalidUserCanAccesCreateNewApplication() throws Exception {

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
                );


        //test create new application
        //then access with post the application composer
        // following attributes username, application date when it was send,
        // Countyname where company is located, City where company located,
        // Industry of the company, Company size, Company type, Company contact email
        // Contact salutation, contact last name
    }


}
