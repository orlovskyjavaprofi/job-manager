package ModelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.CompanySalutationType;
import models.CompanyType;
import models.UserApplication;

class UserApplicationTest
{
   private UserApplication userApplObj;
   
	@BeforeEach
	void setup() {
		userApplObj = new UserApplication();
	}
	
	@Test
	void checkIfCompanyNameCanBeSet() {
		String expectedCompanyName = "Lockheed Martin";
		userApplObj.setCompanyName(expectedCompanyName);
		
		assertEquals(expectedCompanyName,userApplObj.getCompanyName(),"Company name cant be set up!");
	}
	
	@Test
	void checkJobTitleForApplicationCanBeSet() {
		String expectedJobTitleForCompanyApplication = "Software developer";
		userApplObj.setJobTittleOfApplicationForCompany(expectedJobTitleForCompanyApplication);
		
		assertEquals(expectedJobTitleForCompanyApplication,userApplObj.getJobTittleOfApplicationForCompany(),"Job title for company can't be set up!");
	}
	
	@Test
	void checkIfUserApplObjExist(){

		assertNotNull(userApplObj,"User application object was not created!");
	}

    @Test
    void checkIfCompanyCountryNameSetUp() {
      	String expectedCompanyCountryName = "USA";
      	userApplObj.setCompanyCountryName(expectedCompanyCountryName);
      	
      	assertEquals(expectedCompanyCountryName,userApplObj.getCompanyCountryName(),"Company country name wasnt set up!");
    }
    
    @Test
    void checkIfCompanyCityNameSetUp() {
    	   String expectedCompanyCityName = "Austin";
    	   userApplObj.setCompanyCityName(expectedCompanyCityName);
    	   
    	   assertEquals(expectedCompanyCityName,userApplObj.getCompanyCityName(),"Company city name wasnt set up!");
    }
    
    @Test
    void checkIfCompanyIndustrySetUp() {
    	   String expectedCompanyIndustry ="aerospace";
       userApplObj.setCompanyIndustry(expectedCompanyIndustry);
       
       assertEquals(expectedCompanyIndustry,userApplObj.getCompanyIndustry(),"Company industry wasnt set up!");
    }
    
    @Test
    void checkIfCompanyTypeSetUp() {
    	   String expectedCompanyType = "MIDDLE";
    	   CompanyType companyType = CompanyType.MIDDLE;
    	   userApplObj.setCurrentCompanyType(companyType);
    	   
    	   assertEquals(expectedCompanyType,userApplObj.getCurrentCompanyType().toString(),"Company type wasnt set up!");
    }
    
    @Test
    void checkIfCompanyEmployeeSizeSetUp() {
    	   Integer expectedAmountOfEmployeeInCompany = 200;
    	   userApplObj.setCompanyAmountOfEmployee(expectedAmountOfEmployeeInCompany);
    	   
    	   assertEquals(expectedAmountOfEmployeeInCompany,userApplObj.getCompanyAmountOfEmployee(),"Company amount of employee wasnt set up!");
    }
    
    @Test
    void checkIfNegativeCompanyEmployeeSizeCanBeSetUp(){
    	  Integer negativeResult = -1;
    	  userApplObj.setCompanyAmountOfEmployee(negativeResult);
    	  
    	  assertEquals(0,userApplObj.getCompanyAmountOfEmployee().intValue(),"Company amount of employee negative value was allowed to set up!");
    }
    
    @Test
    void checkIfCompanyEmailContactCanBeSetUp() {
    	  String expectedContactEmail = "highvipcontact@bigdealcompany.com";
    	  userApplObj.setCompanyContactEmail(expectedContactEmail);
    	  
    	  assertEquals(expectedContactEmail,userApplObj.getCompanyContactEmail(),"Company contact email wasnt set up!");
    }
	
    @Test
    void checkIfCompanySalutationCanBeSetUp() {
    	   String expectedSalutationForCompany = "Mrs";
    	   CompanySalutationType currentSalut = CompanySalutationType.Mrs;
    	   userApplObj.setCurrentCompanySalutationType(currentSalut);
    	   
    	   assertEquals(expectedSalutationForCompany,userApplObj.getCurrentCompanySalutationType().toString(),"Company salutation wasn't set up!");
    }
    
    @Test
    void checkIfCompanyContactLastName() {
      	String expectedContactLastName = "Meyers";
      	userApplObj.setCompanyContactLastName(expectedContactLastName);
      	
      	assertEquals(expectedContactLastName,userApplObj.getCompanyContactLastName(),"Company contact last name");
    }
    
    @Test 
    void checkIfCompanyDateOfApplicationSetUp() {
    	    String userDateWhenApplicationWasSend = "01.02.2019";		
    	    userApplObj.setDateWhenApplicationWasSend(userDateWhenApplicationWasSend);
		assertEquals(userDateWhenApplicationWasSend,userApplObj.getDateWhenApplicationWasSend());
    }
    
    @Test
    void checkIfDefaultStateOfApplicationSetUp() {
    	   String expectedStateOfJobApplication = "NOTAPPLIED";
    	   String actualStateOfJobApplication = userApplObj.getUserJobApplicationState().toString();
    		assertEquals(expectedStateOfJobApplication,actualStateOfJobApplication, "The default state of user job application not set!");
    }
    
	@Test
	void checkIfANewApplicationForCompanyCanBeCreated()
	{
		UserApplication aplForComapny = createNewAppl();

		assertNotNull(aplForComapny);
	}

	private UserApplication createNewAppl()
	{
		String userDateWhenApplicationWasSend = "01.02.2019";
		String expectedCompanyCountryName = "USA";
		String expectedCompanyCityName = "Austin";
		String expectedCompanyIndustry = "aerospace";
		Integer expectedAmountOfEmployeeInCompany = 200;
		String expectedContactEmail = "highvipcontact@bigdealcompany.com";
		CompanySalutationType currentSalut = CompanySalutationType.Mrs;
		String expectedContactLastName = "Meyers";
		CompanyType companyType = CompanyType.MIDDLE;
		String expectCompanyName = "Lockheed Martin";
		String jobTitle = "Developer";
		
		UserApplication aplForComapny = new UserApplication(
				userDateWhenApplicationWasSend, expectedCompanyCountryName,
				expectedCompanyCityName, expectedCompanyIndustry,
				expectedAmountOfEmployeeInCompany, expectedContactEmail,
				currentSalut, expectedContactLastName, companyType, expectCompanyName, jobTitle
				);
		return aplForComapny;
	}
	

    
}
