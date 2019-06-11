package com.frontend.jobmanger.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RolesAllowed("USER")
public class UserAccountOfficeController
{
	@GetMapping( value = { "/memberarea/userAccountOffice", "/adminarea" })
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String notAuthAccessToMemberArea(){
		
		return "restrictedAccess";
	}
}
