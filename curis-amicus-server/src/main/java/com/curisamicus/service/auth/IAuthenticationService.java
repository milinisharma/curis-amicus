package com.curisamicus.service.auth;

import java.util.concurrent.ExecutionException;

import com.curisamicus.domain.User;

public interface IAuthenticationService {

	public User findByUsername(String username) throws InterruptedException, ExecutionException;
	
	public User registerUser(User user);
}
