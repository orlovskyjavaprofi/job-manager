package com.frontend.jobmanger.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.User;

@Controller
public class PrintViewJobApplicationsController
{

	@Resource
	private InMemoryUserService inMemUserService;
	private User actualUserWhichIslogedIn;
	
	@GetMapping(value = { "/memberarea/printAllUserJobAppls" })
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String notAuthAccessToMemberArea()
	{
		return "restrictedAccess";
	}
	
}
