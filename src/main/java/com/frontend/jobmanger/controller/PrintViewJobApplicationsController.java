package com.frontend.jobmanger.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.User;
import models.UserApplication;
import models.UserEmploymentState;
import models.UserSexState;

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
	
	@GetMapping(value = { "/memberarea/printAllUserJobAppls/" })
	public String authUserAccessToOfficeMemberArea(@RequestParam String uName,
			@Valid @ModelAttribute User currentLogedUser, BindingResult bindingResult, Model userModel) {
		String pathToUserPrintViewJobApplicationsPage = "restrictedAccess";
		
		caseForRunningUnitTestsWhenInMemServiceNotAvailable();
		
		actualUserWhichIslogedIn = inMemUserService.findUserByNickname(uName);
		if(actualUserWhichIslogedIn != null) {
			currentLogedUser = actualUserWhichIslogedIn;
			userModel.addAttribute("userLoginName",uName);
		}
		pathToUserPrintViewJobApplicationsPage = validateThatGivenUserReallyRegistered(uName, pathToUserPrintViewJobApplicationsPage);
		
		verifyThatPathIsValidAndAddNewAtribute(userModel, pathToUserPrintViewJobApplicationsPage, uName);
		
		return pathToUserPrintViewJobApplicationsPage;
	}
	
	private String validateThatGivenUserReallyRegistered(String uName, String pathToUserPrintViewJobApplPage)
	{
		if (actualUserWhichIslogedIn != null)
		{
			if(actualUserWhichIslogedIn.getUserNickName().equals(uName) ) {
				
			  pathToUserPrintViewJobApplPage = "memberarea/printViewAllJobApplOfUser";
			}
		}
		return pathToUserPrintViewJobApplPage;
	}
	
	private void verifyThatPathIsValidAndAddNewAtribute(Model userModel, String pathToJobUserApplPrintViewPage, String userNickName)
	{
		if(pathToJobUserApplPrintViewPage.equals("memberarea/printViewAllJobApplOfUser")) {

			userModel.addAttribute("currentUserJobApplications", inMemUserService.findUserByNickname(userNickName).getUserApplicationsSet());
		}
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
