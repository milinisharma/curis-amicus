package com.curisamicus.repository.auth;

import java.util.concurrent.ExecutionException;

import com.curisamicus.domain.auth.User;

public interface IUserRepository {
	
	public User findByUsername(String username) throws InterruptedException, ExecutionException;
	
	public String registerUser(User user) throws InterruptedException, ExecutionException;

}
