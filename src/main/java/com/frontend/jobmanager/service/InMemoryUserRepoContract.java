package com.frontend.jobmanager.service;

import models.User;

public interface InMemoryUserRepoContract
{
	void saveNewUser(User newRegUser);
}
