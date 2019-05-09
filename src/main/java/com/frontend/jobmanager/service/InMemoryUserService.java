package com.frontend.jobmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.InMemoryUserRepo;
import models.User;

@Service
public class InMemoryUserService implements InMemoryUserRepoContract
{
    @Autowired
	private InMemoryUserRepo inMemRepo;

	public void saveNewUser(User newRegUser)
	{
		System.out.println("following user was save to memory: "+ newRegUser.toString());
		if (newRegUser != null) {
		   inMemRepo.addUser(newRegUser);
		}
	}

	
}
