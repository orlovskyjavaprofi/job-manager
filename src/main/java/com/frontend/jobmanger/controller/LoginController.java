package com.frontend.jobmanger.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.LoginCredentials;
import models.User;

@Controller
public class LoginController
{
	@Resource
	private InMemoryUserService inMemUserService;

	@GetMapping(value = { "", "/", "/login" })
	public String showLoginForm(LoginCredentials cridentialsOfUser, @RequestParam Optional<String> error)
	{
		cridentialsOfUser = new LoginCredentials();
		
		String errorMessage = "Something gone bad!";
		if (error.isPresent())
		{
			//To:Do Log Error 
			System.out.println(errorMessage);
		}

		return "loginUserPage";
	}

	@PostMapping("/loginAsUserToJobManger")
	public String authenticateUserForPrivateAccount(@RequestParam String userNickName, @RequestParam String userPassword, 
			@Valid @ModelAttribute LoginCredentials cridentialsOfUser, BindingResult bindingResult)
	{
		String pageAfterUserAuth = "loginUserPage";
		if (bindingResult.hasErrors()) {
			pageAfterUserAuth = "loginUserPage";
			//To:Do Log Error 
			System.out.println(bindingResult.toString());
		}else {
			pageAfterUserAuth = userAuthSuccess(userNickName, userPassword, pageAfterUserAuth);
			
			System.out.println("Login result: " +pageAfterUserAuth);
		}

		return pageAfterUserAuth;
	}
	
	@GetMapping( value = { "/memberarea", "/adminarea" })
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String notAuthAccessToMemberArea(){
		
		return "restrictedAccess";
	}
	
	private String userAuthSuccess(String inputForUserNickName, String inputForUserPassword, String pageAfterUserAuth)
	{
		boolean authUserResult = false;
		authUserResult = inMemUserService.authUserByGivenNickNameAndPass(inputForUserNickName,inputForUserPassword);
		
		if (authUserResult == true) {
			pageAfterUserAuth="memberarea/landingUserMemberAreaPage";	
		}
		return pageAfterUserAuth;
	}

}
