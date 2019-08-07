package com.frontend.jobmanger.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.User;
import models.UserEmploymentState;
import models.UserSexState;

@Controller
public class DeleteJobApplicationController
{
	@Resource
	private InMemoryUserService inMemUserService;
	private User actualUserWhichIslogedIn;
	
	@GetMapping(value = { "/memberarea/userAccountOffice/deleteApp" })
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String notAuthAccessToMemberArea()
	{
		return "restrictedAccess";
	}
	
	private void caseForRunningUnitTestsWhenInMemServiceNotAvailable()
	{
		if (inMemUserService == null)
		{
			inMemUserService = new InMemoryUserService();
			createTestUserAndSaveItInRepoForTestPurposeOnly();
		}
	}
	
	private void createTestUserAndSaveItInRepoForTestPurposeOnly()
	{
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
		inMemUserService.saveNewUserWithRandomPass(testUser, pass);
	}
}
