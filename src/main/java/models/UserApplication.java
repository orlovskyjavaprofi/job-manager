package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class UserApplication implements Comparable<UserApplication>
{
	@NotEmpty(message = "Company country name input must be 2 or more signs!")
	@Size(min = 2, max = 40)
	private String companyCountryName;

	@NotEmpty(message = "Company city name input must be 2 or more signs!")
	@Size(min = 2, max = 40)
	private String companyCityName;

	@NotEmpty(message = "Company industry name input must be 3 or more signs!")
	@Size(min = 3, max = 40)
	private String companyIndustry;

	@NotNull(message = "Company employee amount input must be 1 or more!")
	@NumberFormat
	private Integer companyAmountOfEmployee;

	@NotEmpty(message = "Invalid input for company contact email!")
	@Size(min = 5, max = 40)
	@Email
	private String companyContactEmail;
	
	@NotNull(message = "Company salutation type must be selected!")
	private CompanySalutationType currentCompanySalutationType;
	
	@NotNull(message = "Company type must be selected!")
	private CompanyType currentCompanyType;

	@NotEmpty(message = "User nickname input must be 5 or more signs!")
	@Size(min=5, max=40)
	private String companyContactLastName;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date for application input is wrong, examle for valid input 12.01.2019") 
	private LocalDate dateWhenApplicationWasSend;
	
	public UserApplication() {
		this.setDateWhenApplicationWasSend("01.01.1999");
		this.setCompanyCountryName("defaultName");
		this.setCompanyCityName("defaultCityName");
		this.setCompanyIndustry("defaultIndustry");
		this.setCompanyAmountOfEmployee(0);
		this.setCompanyContactEmail("default@mail.com");
		this.setCurrentCompanySalutationType(currentCompanySalutationType.Mrs);
		this.setCompanyContactLastName("defaultLastName");
		this.setCurrentCompanyType(CompanyType.MIDDLE);
	}
	
	public UserApplication(String userDateWhenApplicationWasSend, String companyCountryName,
			String companyCityName, String companyIndustry, 
			Integer amountOfEmployeeInCompany, String contactEmail, 
			CompanySalutationType currentSalut, String contactLastName,CompanyType companyType)
	{
		this.setDateWhenApplicationWasSend(userDateWhenApplicationWasSend);
		this.setCompanyCountryName(companyCountryName);
		this.setCompanyCityName(companyCityName);
		this.setCompanyIndustry(companyIndustry);
		this.setCompanyAmountOfEmployee(amountOfEmployeeInCompany);
		this.setCompanyContactEmail(contactEmail);
		this.setCurrentCompanySalutationType(currentSalut);
		this.setCompanyContactLastName(contactLastName);
		this.setCurrentCompanyType(companyType);
	}

	public String getCompanyCountryName()
	{
		return companyCountryName;
	}

	public void setCompanyCountryName(String inputCompanyCountryName)
	{
		companyCountryName = inputCompanyCountryName;
	}

	public String getCompanyCityName()
	{
		return companyCityName;
	}

	public void setCompanyCityName(String inputCompanyCityName)
	{
		companyCityName = inputCompanyCityName;
	}

	public String getCompanyIndustry()
	{
		return companyIndustry;
	}

	public void setCompanyIndustry(String inputCompanyIndustry)
	{
		companyIndustry = inputCompanyIndustry;
	}

	public CompanyType getCurrentCompanyType()
	{
		return currentCompanyType;
	}

	public void setCurrentCompanyType(CompanyType currentCompanyType)
	{
		this.currentCompanyType = currentCompanyType;
	}

	public Integer getCompanyAmountOfEmployee()
	{
		return companyAmountOfEmployee;
	}

	public void setCompanyAmountOfEmployee(Integer companyAmountOfEmployee)
	{
		checkAmountOfEmployeeInCompany(companyAmountOfEmployee);
	}

	private void checkAmountOfEmployeeInCompany(Integer inputCompanyAmountOfEmployee)
	{
		if (inputCompanyAmountOfEmployee < 0)
		{
			companyAmountOfEmployee = 0;
		} else
		{
			companyAmountOfEmployee = inputCompanyAmountOfEmployee;
		}
	}

	public String getCompanyContactEmail()
	{
		return companyContactEmail;
	}

	public void setCompanyContactEmail(String inputCompanyContactEmail)
	{
		companyContactEmail = inputCompanyContactEmail;
	}

	public String getCompanyContactLastName()
	{
		return companyContactLastName;
	}
	
	public void setCompanyContactLastName(String inputCompanyContactLastName)
	{
		companyContactLastName = inputCompanyContactLastName;
	}
	
	public CompanySalutationType getCurrentCompanySalutationType()
	{
		return currentCompanySalutationType;
	}

	public void setCurrentCompanySalutationType(CompanySalutationType currentCompanySalutationType)
	{
		this.currentCompanySalutationType = currentCompanySalutationType;
	}

	public String getDateWhenApplicationWasSend()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
		return dateWhenApplicationWasSend.format(formatter);
	}

	public void setDateWhenApplicationWasSend(String inputFordateWhenApplicationWasSend)
	{	
		inputFordateWhenApplicationWasSend = inputFordateWhenApplicationWasSend.replaceAll("[.]", "-");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.dateWhenApplicationWasSend = LocalDate.parse(inputFordateWhenApplicationWasSend, formatter);
	}

	@Override
	public String toString()
	{
		return "UserApplication:"
				+ "\n companyCountryName = " + companyCountryName + 
				"\n companyCityName = " + companyCityName
				+ "\n companyIndustry = " + companyIndustry 
				+ "\n companyAmountOfEmployee = " + companyAmountOfEmployee
				+ "\n companyContactEmail = " + companyContactEmail 
				+ "\n currentCompanySalutationType = " + currentCompanySalutationType 
				+ "\n currentCompanyType = " + currentCompanyType
				+ "\n companyContactLastName = " + companyContactLastName 
				+ "\n dateWhenApplicationWasSend = " + dateWhenApplicationWasSend ;
	}

	@Override
	public int compareTo(UserApplication inputUserApplication)
	{
  	    int result = 0;		

  	    result = inputUserApplication.getCompanyContactEmail().length() - this.getCompanyContactEmail().length();
		
		return result;
	}

}
