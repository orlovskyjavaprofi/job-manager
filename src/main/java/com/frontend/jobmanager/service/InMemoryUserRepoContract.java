package com.frontend.jobmanager.service;

import models.CompanySalutationType;
import models.User;
import models.UserApplication;

public interface InMemoryUserRepoContract {
    void saveNewUserWithRandomPass(User newRegUser, String RandomPass);

    boolean saveUserWithGivenHashPass(User givenUser);

    User findUser(User userForSearch);

    String genRandomAlphabeticString(int expectLength);

    String genRandomAlphaNumericString(int expectLength);

    String genRandomClearPass(int expectLength);

    String genHashedPassword(String resultClearPassword);

    User findUserByNickname(String userNickName);

    boolean authUserByGivenNickNameAndPass(String givenUserNickName, String givenUserPassword);

    long getNumberOfRegisteredUsers();
    
    UserApplication searchForFullMatchOfUserJobAppl(String userNickName, String companyName,
			String companyContactLastName, String companyContactEmail, String companyJobTitle, String companyDate, 
			 String companyIndustryType  );
 
}
