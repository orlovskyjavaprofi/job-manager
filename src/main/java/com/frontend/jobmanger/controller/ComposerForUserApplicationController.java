package com.frontend.jobmanger.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

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

import models.CompanySalutationType;
import models.CompanyType;
import models.User;
import models.UserApplication;
import models.UserEmploymentState;
import models.UserSexState;

@Controller
public class ComposerForUserApplicationController
{
	@Resource
	private InMemoryUserService inMemUserService;
	private User actualUserWhichIslogedIn;
	
	@GetMapping(value = { "/memberarea/userAccountOffice/composeNewApp" })
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String notAuthAccessToMemberArea()
	{
		return "restrictedAccess";
	}
	
	@GetMapping(value = { "/memberarea/userAccountOffice/composeNewApp/" })
	public String authUserAccessToOfficeMemberArea(@RequestParam String uName,
			@Valid @ModelAttribute User currentLogedUser, BindingResult bindingResult, Model userModel)
	{
		String pathToUserAccountOffice = "restrictedAccess";
		
		caseForRunningUnitTestsWhenInMemServiceNotAvailable();
		actualUserWhichIslogedIn = inMemUserService.findUserByNickname(uName);
		if(actualUserWhichIslogedIn != null) {
			currentLogedUser = actualUserWhichIslogedIn;
			userModel.addAttribute("userLoginName",uName);
		}
		
		pathToUserAccountOffice = validateThatGivenUserReallyRegistered(uName, pathToUserAccountOffice);	
		verifyThatPathIsValidAndAddNewAtribute(userModel, pathToUserAccountOffice);
		
		return pathToUserAccountOffice;
	}

	private void verifyThatPathIsValidAndAddNewAtribute(Model userModel, String pathToUserAccountOffice)
	{
		if(pathToUserAccountOffice.equals("memberarea/composeAppForAUser")) {

			userModel.addAttribute("userJobApplication", new UserApplication());
		}
	}
	
	@PostMapping("/submitNewJobApplicationForUser")
	public String insertNewJobApplicationToUserJobApplicationSet(
			@RequestParam String userNickName, 
			@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateWhenApplicationWasSend,
			@RequestParam String companyCountryName, @RequestParam String companyCityName,
			@RequestParam String companyIndustry, @RequestParam String companyAmountOfEmployee,
			@RequestParam CompanyType currentCompanyType,	@RequestParam String companyContactEmail,
			@RequestParam CompanySalutationType typesOFCompanySalutationType,
			@RequestParam String companyContactLastName,
			@Valid @ModelAttribute UserApplication userJobApplicationAtCompany, BindingResult bindingResult
		) {
		
		//output
		System.out.println("Following information received: ");
		System.out.print("Username: "+userNickName+"\nUserJobDate: "+dateWhenApplicationWasSend+
				"\nCountry: "+companyCountryName+"\nCity: "+companyCityName+"\nIndustry: "+companyIndustry+"\n"
						+ "Employee amount: "+ companyAmountOfEmployee+"\nType of company: "+ 
				        currentCompanyType+"\nCompany contact email: "+
						companyContactEmail+"\nTypes of salutation: "+typesOFCompanySalutationType+
						"\nCompany contact person last name: "+companyContactLastName+"\n");
		
		String pathToComposeNewJobApplication = "restrictedAccess";
				
		pathToComposeNewJobApplication = validateThatGivenUserReallyRegistered(userNickName,pathToComposeNewJobApplication);

		if (pathToComposeNewJobApplication.equals("memberarea/composeAppForAUser")) {
			UserApplication newUserJobApplication = 	createNewJobApplicationForUser(dateWhenApplicationWasSend, companyCountryName,
							companyCityName, companyIndustry, companyAmountOfEmployee, currentCompanyType,
							companyContactEmail, typesOFCompanySalutationType, companyContactLastName);
			
			inMemUserService.findUserByNickname(userNickName).insertNewCompanyApplication(newUserJobApplication);
			pathToComposeNewJobApplication = "memberarea/userAccountOffice";
			System.out.println("current path "+pathToComposeNewJobApplication );
	    }
		
		return pathToComposeNewJobApplication;
	}

	private UserApplication createNewJobApplicationForUser(LocalDate dateWhenUserSendApplicationToCompany,
			String countryWhereCompanyLocated, String cityWhereCompanyLocated, String industryOfCompany,
			String amountOfCompanyEmployees, CompanyType typeOfCompany, String companyContactEmail,
			CompanySalutationType typesOfUserCompanyContactSalutation, String companyContactPersonLastName)
	{
		return new UserApplication(
				dateWhenUserSendApplicationToCompany
				  .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
				  .toString(),
		        countryWhereCompanyLocated, 
		        cityWhereCompanyLocated,
		        industryOfCompany,new Integer(amountOfCompanyEmployees),
		        companyContactEmail, typesOfUserCompanyContactSalutation,
		        companyContactPersonLastName,typeOfCompany
		);
	}
	
	private String validateThatGivenUserReallyRegistered(String uName, String pathToUserAccountOffice)
	{
		if (actualUserWhichIslogedIn != null)
		{
			if(actualUserWhichIslogedIn.getUserNickName().equals(uName) ) {
				
			  pathToUserAccountOffice = "memberarea/composeAppForAUser";
			}
		}
		return pathToUserAccountOffice;
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
	
	@ModelAttribute("allCompanySalutationTypes")
	public List<CompanySalutationType> populateCompanySalutationType()
	{
		return Arrays.asList(CompanySalutationType.values());
	}
	
	@ModelAttribute("allCompanyStateTypes")
	public List<CompanyType> populateCompanyTypes()
	{
		return Arrays.asList(CompanyType.values());
	}
	
}
