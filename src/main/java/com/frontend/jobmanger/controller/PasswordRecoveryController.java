package com.frontend.jobmanger.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.User;
import models.UserEmploymentState;
import models.UserSexState;

@Controller
public class PasswordRecoveryController
{

	@GetMapping("/recoverUserPass")
	public String showPassRecoveryForm(Model model)
	{
		model.addAttribute("user", new User());
		return "userPasswordRecovery";
	}
	
	@PostMapping("/submitUserPassRecovery")
	public String userPasswordRecovery(@RequestParam String userFirstName,@RequestParam String userLastName,
			@RequestParam String userEmail, @RequestParam String userCity,
			@RequestParam String userStreetName, @RequestParam Integer userStreetNumber,
			@RequestParam String userCountryName, @Valid @ModelAttribute User currentUser, BindingResult bindingResult) {
		String pageAfterNewUserValidation = "passwordRecoveryConfirmation";

		if (bindingResult.hasErrors()) {
			pageAfterNewUserValidation = "userPasswordRecovery";
		}

		
		return pageAfterNewUserValidation;
	}
	
}
