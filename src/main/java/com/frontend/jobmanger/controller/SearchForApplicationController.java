package com.frontend.jobmanger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SearchForApplicationController
{
	@GetMapping(value = { "/memberarea/userAccountOffice/searchForUserApplications" })
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String notAuthAccessToMemberArea()
	{
		return "restrictedAccess";
	}
}
