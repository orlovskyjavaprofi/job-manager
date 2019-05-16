package com.frontend.jobmanager.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import models.InMemoryUserRepo;
import models.User;

@Service
public class InMemoryUserService implements InMemoryUserRepoContract
{
	@Autowired
	private InMemoryUserRepo inMemRepo;
	
	private PasswordEncoder passEncoder;
	
	public InMemoryUserService() {
		inMemRepo = new InMemoryUserRepo();
		passEncoder = new BCryptPasswordEncoder();
	}
	
	public void saveNewUserWithRandomPass(User newRegUser, String RandomPass)
	{		String passwordInClear="";
		if (newRegUser != null)  {
			
			passwordInClear = verifyRandomPassword(RandomPass);
			System.out.println("current pass "+passwordInClear);
			newRegUser.setUserPassword( this.genHashedPassword(passwordInClear ));
						
			System.out.println("following user was save to memory: "+ newRegUser.toString());
			
			// Check if this User already exist in system, following criteria
			// user first name and last name the same as user input or user provided the same email again
			// check user street name and street number und user city name
			// check user nickname is already available
			
		   inMemRepo.addUser(newRegUser);
		}
	}

	private String verifyRandomPassword(String RandomPass)
	{ 
		String passwordInClear = "";
		
		if ((RandomPass.length() <= 12) && (RandomPass.isEmpty() == false))
		{
			passwordInClear = RandomPass;			
		} else
		{
			passwordInClear = this.genRandomClearPass(12);
		}
		
		return passwordInClear;
	}
	
	public boolean saveUserWithGivenHashPass(User givenUser)
	{
		boolean result = false;
		
		if(givenUser != null) {
			inMemRepo.addUser(givenUser);
			result = true;
		}
		
		return result;
		
	}
	
	public boolean authUserByGivenNickNameAndPass(String givenUserNickName, String givenUserPassword)
	{
		boolean result= false;
	
		System.out.println("\nUser supplied pass: "+givenUserPassword);
		User userToBeAuthInSystem = findUserByNickname(givenUserNickName);
		System.out.println("\nfollowing User was found "+ userToBeAuthInSystem);
		
		if (userToBeAuthInSystem != null) {
			if( passEncoder.matches(givenUserPassword, userToBeAuthInSystem.getUserPassword()) ) {
				// check if user has activated account
				// if the user account activated then, only then he must be allowed to login into page, otherwise not! 
				result = true;
			}
		}
		
		return result;
	}
	
	public User findUser(User userForSearch)
	{	
		User userFound = inMemRepo.findUser(userForSearch);

		return userFound;
	}

	public String genRandomAlphabeticString(int expectLength)
	{
		String resultAlphabeticString="tuxtux";
		
		if (expectLength < 6) {
			return resultAlphabeticString;
		}else {
			resultAlphabeticString = RandomStringUtils.randomAlphabetic(expectLength);
			return resultAlphabeticString;
		}
		
	}

	public String genRandomAlphaNumericString(int expectLength)
	{
		String resultAlphaNumericString="111000";
		
		if (expectLength < 6) {			
			return resultAlphaNumericString;
		}else {
			resultAlphaNumericString = RandomStringUtils.randomNumeric(expectLength);
			return resultAlphaNumericString;
		}
		
	}

	public String genRandomClearPass(int expectLength)
	{
		String givenPass="067Uikl";
		String generatedPass= new String();
		
		int maxRangeOfPass = 12;
		int allowedLength= 6;
		
		if (expectLength < maxRangeOfPass) {
			generatedPass = givenPass;
		}
		else if(expectLength >= maxRangeOfPass) {
			generatedPass= genRandomAlphabeticString(allowedLength)+genRandomAlphaNumericString(allowedLength);
		}

		return generatedPass;
	}

	public String genHashedPassword(String resultClearPassword)
	{
		return passEncoder.encode(resultClearPassword);
	}

	public User findUserByNickname(String userNickName)
	{
	   return inMemRepo.findUserByGivenName(userNickName);
	}

	
}
