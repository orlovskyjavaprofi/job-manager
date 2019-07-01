package com.frontend.jobmanager.service;

import models.User;

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
}
