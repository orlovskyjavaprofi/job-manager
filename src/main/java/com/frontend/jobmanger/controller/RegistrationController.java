package com.frontend.jobmanger.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    
    @ModelAttribute("allUserSexTypes")
    public List<UserSexState> populateUserSexType() {
        return Arrays.asList(UserSexState.values());
    }
    
    @ModelAttribute("allUserEmployeeStateTypes")
    public List<UserEmploymentState> populateUSerEmployeeStateTypes(){
    	 return Arrays.asList(UserEmploymentState.values());
    }
}