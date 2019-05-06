package com.frontend.jobmanger.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.User;

@Controller
public class LoginController
{

	@GetMapping(value = { "", "/", "/login" })
	public String showLoginForm(User currenUser, @RequestParam Optional<String> error)
	{
		String errorMessage = "Incorrect login, warning the user";
		if (error.isPresent())
		{
			System.out.println(errorMessage);
		}

		return "loginUserPage";
	}

	@PostMapping("/loginAsUserToJobManger")
	public String authenticateUserForPrivateAccount(@RequestParam String userNickName,
			@RequestParam String userPassword, @Valid @ModelAttribute User currentUser, BindingResult bindingResult)
	{
		String pageAfterUserAuth = "userAuthlandPage";

		if (bindingResult.hasErrors()) {
			pageAfterUserAuth = "loginUserPage";
		}

		return pageAfterUserAuth;
	}
}
