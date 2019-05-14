package com.frontend.jobmanger.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.User;
import models.UserEmploymentState;
import models.UserSexState;

@Controller
public class RegistrationController
{
	@Autowired
	private InMemoryUserService inMemUserService;
	
	@GetMapping("/regnewuser")
	public String showRegistrationForm(Model model)
	{
		model.addAttribute("user", new User());
		
		return "regnewuserform";
	}

	@PostMapping("/submitNewUserReg")
	public String addNewUser(@RequestParam String userFirstName, @RequestParam String userLastName,
			@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate userBirthDate,
			@RequestParam String userEmail, @RequestParam String userCity,
			@RequestParam String userStreetName, @RequestParam Integer userStreetNumber,
			@RequestParam String userCountryName, @RequestParam String userNickName,
			@RequestParam UserSexState typesOfUserSex, @RequestParam UserEmploymentState currentEmploymentState,
			@Valid @ModelAttribute User newRegUser, BindingResult bindingResult)
	{

		String pageAfterNewUserValidation = "regnewuserform";

		if (bindingResult.hasErrors()) {
			pageAfterNewUserValidation = "regnewuserform";
			//To:Do Log Error 
			System.out.println(bindingResult.toString() );
		}else {
	     	saveUserToInMemoryRepo(newRegUser);
			pageAfterNewUserValidation = "newUserAddConfirmation";
		}

		return pageAfterNewUserValidation;
	}

	private void saveUserToInMemoryRepo(User userWhichReg)
	{
		if (inMemUserService != null) {
		  inMemUserService.saveNewUserWithRandomPass(userWhichReg);
		}
	}

	@ModelAttribute("allUserSexTypes")
	public List<UserSexState> populateUserSexType()
	{
		return Arrays.asList(UserSexState.values());
	}

	@ModelAttribute("allUserEmployeeStateTypes")
	public List<UserEmploymentState> populateUSerEmployeeStateTypes()
	{
		return Arrays.asList(UserEmploymentState.values());
	}
}