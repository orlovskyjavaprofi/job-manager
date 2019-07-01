package ControllersTests;

import com.frontend.jobmanger.controller.LoginController;
import com.frontend.jobmanger.controller.RegistrationController;
import models.UserEmploymentState;
import models.UserSexState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


class LoginControllerTest {
    private MockMvc mockMvcLogin;
    private LoginController loginController;
    private RegistrationController regController;


    @BeforeEach
    public void setUp() {
        loginController = new LoginController();
        regController = new RegistrationController();
        mockMvcLogin = MockMvcBuilders.standaloneSetup(loginController, regController).build();
    }

    @Test
    public void checkThatLoginPageHasAValidViewNameForLogin() throws Exception {
        mockMvcLogin.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginUserPage"))
                .andExpect(model().attribute("numberOfRegisteredUsers", 0));
    }

    @Test
    public void checkThatLoginPageHasAValidViewNameForSlash() throws Exception {
        mockMvcLogin.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginUserPage"));
    }

    @Test
    public void checkIfUserInputBlankInputForLogin() throws Exception {
        String blankInput = "";
        mockMvcLogin.perform(post("/loginAsUserToJobManger")
                .param("userNickName", blankInput)
                .param("userPassword", blankInput)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("loginUserPage"));
    }

    @Test
    public void checkIfNotRegesteredUserTriesToAccessMemberArea() throws Exception {
        mockMvcLogin.perform(get("/memberarea"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void checkIfNotRegesteredUserTriesToAccessAdminArea() throws Exception {
        mockMvcLogin.perform(get("/adminarea"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void checkIfUserCantLoginWithRandomStuffIntoPage() throws Exception {

        mockMvcLogin.perform(post("/loginAsUserToJobManger")
                .param("userNickName", "tux")
                .param("userPassword", "")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("loginUserPage"));
    }

    @Test
    public void checkIfUserCanBeRegisteredAndSuccsessfulLoginIntoTheCreatedAccount() throws Exception {
        UserSexState currentSexState = UserSexState.MALE;
        UserEmploymentState currentEmploymentState = UserEmploymentState.SELFEMPLOYED;
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
                .param("typesOfUserSex", currentSexState.toString())
                .param("currentEmploymentState", currentEmploymentState.toString())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("newUserAddConfirmation"))
                .andDo(mvcResult -> mockMvcLogin.perform(post("/loginAsUserToJobManger")
                                .param("userNickName", "testdude000")
                                .param("userPassword", "tuxtux123456")
                        )
                                .andExpect(status().isOk())
                                .andExpect(view().name("memberarea/landingUserMemberAreaPage"))
                );

    }

}
