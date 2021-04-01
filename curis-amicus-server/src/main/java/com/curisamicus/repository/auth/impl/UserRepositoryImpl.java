package com.curisamicus.repository.auth.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.curisamicus.domain.auth.User;
import com.curisamicus.repository.auth.IUserRepository;

@Repository
public class UserRepositoryImpl implements IUserRepository {
	
	@Override
	public User findByUsername(String username) throws InterruptedException, ExecutionException {
		return null;
	}

	@Override
	public String registerUser(User user) throws InterruptedException, ExecutionException  {
		return null;
	}
	
}
