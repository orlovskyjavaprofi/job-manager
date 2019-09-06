package com.frontend.jobmanger.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;
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
public class SearchForApplicationController
{
	@Resource
	private InMemoryUserService inMemUserService;
	private User actualUserWhichIslogedIn;
	
	@GetMapping(value = { "/memberarea/userAccountOffice/searchForUserApplications" })
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String notAuthAccessToMemberArea()
	{
		return "restrictedAccess";
	}
	
	@GetMapping(value = {"/memberarea/userAccountOffice/searchForUserApplications/" })
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
	
	@PostMapping("/submitSearchForJobUserAppl/")
	public String searchJobApplicationsForGivenUser(@RequestParam String userNickName, 
			@RequestParam String companyNameForSearch, @RequestParam String companyContactPersonForSearch,
			@RequestParam String companyContactEmailForSearch, @RequestParam String companyJobTitleForSearch,
			@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate companyDateWhenUserAppliedForJobForSearch, 
			@RequestParam String companyIndustryTypeForJobForSearch, 
			@Valid @ModelAttribute UserApplication userJobApplication, BindingResult bindingResult, Model userModel ) throws ParseException {
		
		String pathToPageForSearchOfJobApplication = "restrictedAccess";
		String pageToForwardTo = validateThatGivenUserReallyRegistered(userNickName,pathToPageForSearchOfJobApplication );
        String dateOfJobAppl= new String("");
        Boolean companyNameForSearchState =false;
        Boolean companyContactPersonForSearchState =false;
        Boolean companyContactEmailForSearchState =false;
        Boolean companyJobTitleForSearchState =false;
        Boolean companyDateWhenUserAppliedForJobForSearchState =false;
        Boolean companyIndustryTypeForJobForSearchState =false;
       		
		if (pageToForwardTo.isEmpty() == false) {
			
			companyNameForSearchState = checkThatGivenStringIsNotNull(companyNameForSearch,
					companyNameForSearchState);

			companyContactPersonForSearchState = checkThatGivenStringIsNotNull(companyContactPersonForSearch,
					companyContactPersonForSearchState);

			companyContactEmailForSearchState = checkThatGivenStringIsNotNull(companyContactEmailForSearch,
					companyContactEmailForSearchState);

			companyJobTitleForSearchState = checkThatGivenStringIsNotNull(companyJobTitleForSearch,
					companyJobTitleForSearchState);
			
			if(companyDateWhenUserAppliedForJobForSearch != null) {
				dateOfJobAppl = companyDateWhenUserAppliedForJobForSearch.toString();
			}
			
			companyDateWhenUserAppliedForJobForSearchState = checkThatGivenStringIsNotNull(
					dateOfJobAppl, companyDateWhenUserAppliedForJobForSearchState);
			
			companyIndustryTypeForJobForSearchState = checkThatGivenStringIsNotNull(
					companyIndustryTypeForJobForSearch, companyIndustryTypeForJobForSearchState);
			
			System.out.println(companyNameForSearchState);
			System.out.println(companyContactPersonForSearchState);
			System.out.println(companyContactEmailForSearchState);
			System.out.println(companyJobTitleForSearchState);
			System.out.println(companyDateWhenUserAppliedForJobForSearchState);
			System.out.println(companyIndustryTypeForJobForSearchState);
			
			pathToPageForSearchOfJobApplication = caseAllSearchJobApplFieldsFilled(userNickName, companyNameForSearch,
					companyContactPersonForSearch, companyContactEmailForSearch, companyJobTitleForSearch,
					dateOfJobAppl,
					companyIndustryTypeForJobForSearch, userModel, pathToPageForSearchOfJobApplication,
					companyNameForSearchState, companyContactPersonForSearchState, companyContactEmailForSearchState,
					companyJobTitleForSearchState, companyDateWhenUserAppliedForJobForSearchState,
					companyIndustryTypeForJobForSearchState);

			pathToPageForSearchOfJobApplication = caseSearchForCompanyNameOnly(userNickName, companyNameForSearch,
					userModel, pathToPageForSearchOfJobApplication, companyNameForSearchState,
					companyContactPersonForSearchState, companyContactEmailForSearchState,
					companyJobTitleForSearchState, companyDateWhenUserAppliedForJobForSearchState,
					companyIndustryTypeForJobForSearchState);
			
			pathToPageForSearchOfJobApplication = caseSearchForCompanyContactLastNameOnly(userNickName,
					companyContactPersonForSearch, userModel, pathToPageForSearchOfJobApplication,
					companyNameForSearchState, companyContactPersonForSearchState, companyContactEmailForSearchState,
					companyJobTitleForSearchState, companyDateWhenUserAppliedForJobForSearchState,
					companyIndustryTypeForJobForSearchState);
			
			if( (companyNameForSearchState == true) && (companyContactPersonForSearchState == true) &&
					(companyContactEmailForSearchState == false) &&(companyJobTitleForSearchState == true) &&
					(companyDateWhenUserAppliedForJobForSearchState == true) &&(companyIndustryTypeForJobForSearchState == true)
				  ) {
						userModel.addAttribute("userLoginName", userNickName);
						userModel.addAttribute("searchResult", inMemUserService
						.searchForCompanyContactEmailOfUserJobAppl(userNickName, companyContactEmailForSearch));
						pathToPageForSearchOfJobApplication = "memberarea/searchForUserJobApplication";
				    }
//			
//			if( (companyNameForSearchState == true) && (companyContactPersonForSearchState == true) &&
//					(companyContactEmailForSearchState == true) &&(companyJobTitleForSearchState == false) &&
//					(companyDateWhenUserAppliedForJobForSearchState == true) &&(companyIndustryTypeForJobForSearchState == true)
//				  ) {
//					 // case when only Job title is given: call inMemService!
//				    }
//			
//			if( (companyNameForSearchState == true) && (companyContactPersonForSearchState == true) &&
//					(companyContactEmailForSearchState == true) &&(companyJobTitleForSearchState == true) &&
//					(companyDateWhenUserAppliedForJobForSearchState == false) &&(companyIndustryTypeForJobForSearchState == true)
//				  ) {
//					 // case when only date of application is given: call inMemService!
//				    }
//			
//			if( (companyNameForSearchState == true) && (companyContactPersonForSearchState == true) &&
//					(companyContactEmailForSearchState == true) &&(companyJobTitleForSearchState == true) &&
//					(companyDateWhenUserAppliedForJobForSearchState == true) &&(companyIndustryTypeForJobForSearchState == false)
//				  ) {
//					 // case when only company industry type is given: call inMemService!
//				    }
			
		}
		
		return pathToPageForSearchOfJobApplication;
	}

	private String caseSearchForCompanyContactLastNameOnly(String userNickName, String companyContactPersonForSearch,
			Model userModel, String pathToPageForSearchOfJobApplication, Boolean companyNameForSearchState,
			Boolean companyContactPersonForSearchState, Boolean companyContactEmailForSearchState,
			Boolean companyJobTitleForSearchState, Boolean companyDateWhenUserAppliedForJobForSearchState,
			Boolean companyIndustryTypeForJobForSearchState)
	{
		if( (companyNameForSearchState == true) && (companyContactPersonForSearchState == false) &&
				(companyContactEmailForSearchState == true) &&(companyJobTitleForSearchState == true) &&
				(companyDateWhenUserAppliedForJobForSearchState == true) &&(companyIndustryTypeForJobForSearchState == true)
			  ) {					 
			     userModel.addAttribute("userLoginName", userNickName);
			     userModel.addAttribute("searchResult", inMemUserService
					.searchForCompanyContactLastNameofUserJobAppl(userNickName, companyContactPersonForSearch));
			     pathToPageForSearchOfJobApplication = "memberarea/searchForUserJobApplication";
			    }
		return pathToPageForSearchOfJobApplication;
	}

	private String caseSearchForCompanyNameOnly(String userNickName, String companyNameForSearch, Model userModel,
			String pathToPageForSearchOfJobApplication, Boolean companyNameForSearchState,
			Boolean companyContactPersonForSearchState, Boolean companyContactEmailForSearchState,
			Boolean companyJobTitleForSearchState, Boolean companyDateWhenUserAppliedForJobForSearchState,
			Boolean companyIndustryTypeForJobForSearchState)
	{
		if( (companyNameForSearchState == false) && (companyContactPersonForSearchState == true) &&
				(companyContactEmailForSearchState == true) &&(companyJobTitleForSearchState == true) &&
				(companyDateWhenUserAppliedForJobForSearchState == true) &&(companyIndustryTypeForJobForSearchState == true)
			  ) {
			       userModel.addAttribute("userLoginName",userNickName);
			       userModel.addAttribute("searchResult", 
			    		   inMemUserService.searchOnlyForCompanyNameMatchOfUserJobAppl(userNickName, companyNameForSearch));
			       pathToPageForSearchOfJobApplication = "memberarea/searchForUserJobApplication";
			    }
		return pathToPageForSearchOfJobApplication;
	}

	private Boolean checkThatGivenStringIsNotNull(String givenString, Boolean searchState)
	{
		if (givenString != null)
		{
			searchState = givenString.isEmpty();
		}
		
		return searchState;
	}

	private String caseAllSearchJobApplFieldsFilled(String userNickName, String companyNameForSearch,
			String companyContactPersonForSearch, String companyContactEmailForSearch, String companyJobTitleForSearch,
			String companyDateWhenUserAppliedForJobForSearch,
			String companyIndustryTypeForJobForSearch, Model userModel, String pathToPageForSearchOfJobApplication,
			Boolean companyNameForSearchState, Boolean companyContactPersonForSearchState,
			Boolean companyContactEmailForSearchState, Boolean companyJobTitleForSearchState,
			Boolean companyDateWhenUserAppliedForJobForSearchState, Boolean companyIndustryTypeForJobForSearchState) throws ParseException
	{
      
		if( (companyNameForSearchState == false) && (companyContactPersonForSearchState == false) &&
			(companyContactEmailForSearchState == false) &&(companyJobTitleForSearchState == false) &&
			(companyDateWhenUserAppliedForJobForSearchState == false) && (companyIndustryTypeForJobForSearchState == false)
		  ) {
	
				  populateViewModelForSearchUserJobAppl(userNickName, companyNameForSearch, companyContactPersonForSearch,
						companyContactEmailForSearch, companyJobTitleForSearch,
						companyDateWhenUserAppliedForJobForSearch, companyIndustryTypeForJobForSearch, userModel);	
				 
				  pathToPageForSearchOfJobApplication = "memberarea/searchForUserJobApplication";
			  
		    }
		return pathToPageForSearchOfJobApplication;
	}

	private void populateViewModelForSearchUserJobAppl(String userNickName, String companyNameForSearch,
			String companyContactPersonForSearch, String companyContactEmailForSearch, String companyJobTitleForSearch,
			String companyDateWhenUserAppliedForJobForSearch, String companyIndustryTypeForJobForSearch,
			Model userModel) throws ParseException
	{
		userModel.addAttribute("userLoginName",userNickName);
	
		String formatedDateEU = convertUsDateToEuDate(companyDateWhenUserAppliedForJobForSearch);
		
		userModel.addAttribute("searchResult", inMemUserService.searchForFullMatchOfUserJobAppl(
				                  userNickName, companyNameForSearch, companyContactPersonForSearch,
				                  companyContactEmailForSearch, companyJobTitleForSearch, 
				                  formatedDateEU, companyIndustryTypeForJobForSearch
		   )
		  );
	}

	private String convertUsDateToEuDate(String companyDateWhenUserAppliedForJobForSearch) throws ParseException
	{
		String formatedDateEU;
		DateFormat UStimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat EUtimeFormat = new SimpleDateFormat("dd.MM.yyyy");
		formatedDateEU = EUtimeFormat.format(UStimeFormat.parse(companyDateWhenUserAppliedForJobForSearch));
		return formatedDateEU;
	}
	
	private String validateThatGivenUserReallyRegistered(String uName, String pathToUserAccountOffice)
	{
		if (actualUserWhichIslogedIn != null)
		{
			if(actualUserWhichIslogedIn.getUserNickName().equals(uName) ) {
				
			  pathToUserAccountOffice = "memberarea/searchForUserJobApplication";
			}
		}
		return pathToUserAccountOffice;
	}	
	
	private void verifyThatPathIsValidAndAddNewAtribute(Model userModel, String pathToUserAccountOffice, String inputName)
	{
		if(pathToUserAccountOffice.equals("memberarea/searchForUserJobApplication")) {
			userModel.addAttribute("userLoginName",inputName);						
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
