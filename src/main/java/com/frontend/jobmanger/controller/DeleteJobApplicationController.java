package com.frontend.jobmanger.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.frontend.jobmanager.service.InMemoryUserService;

import models.User;
import models.UserApplication;
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
	
	@GetMapping(value = {"/memberarea/userAccountOffice/deleteApp/" })
	public String authUserAccessToOfficeMemberArea(@RequestParam String uName,
			@Valid @ModelAttribute User currentLogedUser, BindingResult bindingResult, Model userModel)
	{
		String pathToUserAccountOffice = "restrictedAccess";
		
		caseForRunningUnitTestsWhenInMemServiceNotAvailable();
		actualUserWhichIslogedIn = inMemUserService.findUserByNickname(uName);
		
		if(actualUserWhichIslogedIn != null) {
			currentLogedUser = actualUserWhichIslogedIn;
			pathToUserAccountOffice = validateThatGivenUserReallyRegistered(uName, pathToUserAccountOffice);			
			verifyThatPathIsValidAndAddNewAtribute(userModel, pathToUserAccountOffice, uName);
		}

		return pathToUserAccountOffice;
	}
	
	
	@PostMapping("/deleteUserJobApplication/")
	public String deleteUserJobApplication(@RequestParam String[] companyNameSelection ,
	   @RequestParam String userNickName, @RequestParam String jobTittleOfApplicationForCompany, 
	   @Valid @ModelAttribute UserApplication userJobApplication,	BindingResult bindingResult, Model userModel) {
		String pathToPageForDeletionOfJobApplication = "restrictedAccess";
		
		pathToPageForDeletionOfJobApplication = deleteAllSelectedJobApplications(companyNameSelection, userNickName,
				jobTittleOfApplicationForCompany, userModel, pathToPageForDeletionOfJobApplication);
		
		return pathToPageForDeletionOfJobApplication;
	}

	private String deleteAllSelectedJobApplications(String[] companyNameSelection, String userNickName,
			String jobTittleOfApplicationForCompany, Model userModel, String pathToPageForDeletionOfJobApplication)
	{
		for (String companyWhichWasSelectedByUser : companyNameSelection)
		{
			if (companyWhichWasSelectedByUser.isEmpty() == false) {
				pathToPageForDeletionOfJobApplication = deleteJobApplOfUser(userNickName, companyWhichWasSelectedByUser,
						jobTittleOfApplicationForCompany, userModel, pathToPageForDeletionOfJobApplication);
				}
		}
		return pathToPageForDeletionOfJobApplication;
	}

	private String deleteJobApplOfUser(String userNickName, String companyName, String jobTittleOfApplicationForCompany,
			Model userModel, String pathToPageForDeletionOfJobApplication)
	{
		pathToPageForDeletionOfJobApplication = validateThatGivenUserReallyRegistered(userNickName,pathToPageForDeletionOfJobApplication);
		
		if(pathToPageForDeletionOfJobApplication.equals("memberarea/listOfUserJobApplicationsForDeletion")) {
			if (jobTittleOfApplicationForCompany.isEmpty() == false) {
			  inMemUserService.findUserByNickname(userNickName).deleteJobApplicationByCompanyName(companyName);
			  userModel.addAttribute("userLoginName",userNickName);
			  pathToPageForDeletionOfJobApplication = "memberarea/userAccountOffice";
			}
		  }
		return pathToPageForDeletionOfJobApplication;
	}
	
	
	private String validateThatGivenUserReallyRegistered(String uName, String pathToUserAccountOffice)
	{
		if (actualUserWhichIslogedIn != null)
		{
			if(actualUserWhichIslogedIn.getUserNickName().equals(uName) ) {
				
			  pathToUserAccountOffice = "memberarea/listOfUserJobApplicationsForDeletion";
			}
		}
		return pathToUserAccountOffice;
	}	
	
	private void verifyThatPathIsValidAndAddNewAtribute(Model userModel, String pathToUserAccountOffice, String inputName)
	{
		if(pathToUserAccountOffice.equals("memberarea/listOfUserJobApplicationsForDeletion")) {
			userModel.addAttribute("userLoginName",inputName);
			userModel.addAttribute("setOfUserJobAppl", inMemUserService.
					               findUserByNickname(actualUserWhichIslogedIn.getUserNickName()).getUserApplicationsSet());			
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
