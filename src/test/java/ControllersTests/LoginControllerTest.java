package ControllersTests;

import models.UserEmploymentState;
import models.UserSexState;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
class LoginControllerTest extends BasicControllerTest {

    @Test
    public void checkIfLoginPageHasAValidViewNameForLogin() throws Exception {
        mockMvcLogin.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginUserPage"));
    }

    @Test
    public void checkIfLoginPageHasAValidViewNameForSlash() throws Exception {
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
                .andExpect(status().isForbidden());
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
