package com.frontend.jobmanger.controller;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.User;
import models.UserEmploymentState;
import models.UserSexState;


@Controller
public class RegistrationController
{
    @GetMapping("/regnewuser")
	public String showRegistrationForm(Model model) {
    	    model.addAttribute("user", new User());
		return "regnewuserform";
	}
    
	@PostMapping("/submitNewUserReg")
	public String handleAddNewUser(@RequestParam String userFirstName, @RequestParam String userLastName,
			@RequestParam String userBirthDate, @RequestParam String userEmail, @RequestParam String userCity,
			@RequestParam String userStreetName, @RequestParam Integer userStreetNumber,
			@RequestParam String userCountryName, @RequestParam String typesOfUserSex,
			@RequestParam String userNickName, @RequestParam String currentEmploymentState)
	{

		return "/newUserAddConfirmation";
	}

    @ModelAttribute("allUserSexTypes")
    public List<UserSexState> populateUserSexType() {
        return Arrays.asList(UserSexState.values());
    }
    
    @ModelAttribute("allUserEmployeeStateTypes")
    public List<UserEmploymentState> populateUSerEmployeeStateTypes(){
    	 return Arrays.asList(UserEmploymentState.values());
    }
}