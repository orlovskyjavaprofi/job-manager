package com.frontend.jobmanger.controller;

import com.frontend.jobmanager.service.InMemoryUserRepoContract;
import com.frontend.jobmanager.service.InMemoryUserService;
import models.LoginCredentials;
import models.User;
import models.UserEmploymentState;
import models.UserSexState;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {
    @Resource
    private InMemoryUserRepoContract inMemoryUserRepoContract;

    @GetMapping(value = {"", "/", "/login"})
    public String showLoginForm(LoginCredentials cridentialsOfUser, @RequestParam Optional<String> error) {
        cridentialsOfUser = new LoginCredentials();

        String errorMessage = "Something gone bad!";
        if (error.isPresent()) {
            //To:Do Log Error
            System.out.println(errorMessage);
        }

        return "loginUserPage";
    }

    @PostMapping("/loginAsUserToJobManger")
    public String authenticateUserForPrivateAccount(@RequestParam String userNickName, @RequestParam String userPassword,
                                                    @Valid @ModelAttribute LoginCredentials cridentialsOfUser, BindingResult bindingResult, Model userModel) {
        String pageAfterUserAuth = "loginUserPage";
        if (bindingResult.hasErrors()) {

            //To:Do Log Error
            System.out.println(bindingResult.toString());
        } else {
            pageAfterUserAuth = userAuthSuccess(userNickName, userPassword, pageAfterUserAuth);
            if (pageAfterUserAuth.isEmpty() == false) {
                userModel.addAttribute("userLoginName", userNickName);
            }
        }

        return pageAfterUserAuth;
    }

    @GetMapping(value = {"/adminarea"})
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public String notAuthAccessToMemberArea() {

        return "restrictedAccess";
    }

    private String userAuthSuccess(String inputForUserNickName, String inputForUserPassword, String pageAfterUserAuth) {
        boolean authUserResult = false;
        if (inMemoryUserRepoContract == null) {
            inMemoryUserRepoContract = new InMemoryUserService();
            createTestUserAndSaveItInRepoForTestPurposeOnly();
        }
        authUserResult = inMemoryUserRepoContract.authUserByGivenNickNameAndPass(inputForUserNickName, inputForUserPassword);

        if (authUserResult == true) {
            pageAfterUserAuth = "memberarea/landingUserMemberAreaPage";
        }

        return pageAfterUserAuth;
    }

    private void createTestUserAndSaveItInRepoForTestPurposeOnly() {
        User testUser = new User();
        testUser.setUserFirstName("Thomas");
        testUser.setUserLastName("Jefferson");
        testUser.setUserBirthDate("01.12.1978");
        testUser.setUserEmail("cooldude@io.com");
        testUser.setUserCity("Detroit");
        testUser.setUserStreetName("BerlinerStreet");
        testUser.setUserStreetNumber(123);
        testUser.setUserCountryName("USA");
        testUser.setUserNickName("testdude000");
        testUser.setCurrentUserSexState(UserSexState.MALE);
        testUser.setCurrentEmploymentState(UserEmploymentState.SELFEMPLOYED);
        String pass = "tuxtux123456";
        inMemoryUserRepoContract.saveNewUserWithRandomPass(testUser, pass);
    }

    @ModelAttribute("numberOfRegisteredUsers")
    public long populateNumberOfRegisteredUsers() {

        return inMemoryUserRepoContract.getNumberOfRegisteredUsers();
    }

}
