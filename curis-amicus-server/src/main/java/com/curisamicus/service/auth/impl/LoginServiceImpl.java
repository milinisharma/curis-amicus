package com.curisamicus.service.auth.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curisamicus.domain.auth.User;
import com.curisamicus.repository.auth.impl.UserRepositoryImpl;
import com.curisamicus.service.auth.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private UserRepositoryImpl userDao;
	
	@Override
	public User findByUsername(String username) throws InterruptedException, ExecutionException {
		return userDao.findByUsername(username);
	}

}
