package com.curisamicus.service.auth.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curisamicus.domain.ERole;
import com.curisamicus.domain.Role;
import com.curisamicus.domain.User;
import com.curisamicus.repository.auth.impl.UserRepositoryImpl;
import com.curisamicus.service.auth.IAuthenticationService;

@Service("authServiceImpl")
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	@Autowired
	private UserRepositoryImpl userDao;
	
	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public User registerUser(User user) {
		calculateAge(user);
		return userDao.registerUser(user);
	}
	
	@Override
	public Boolean existsByUsername(String username) {
		return userDao.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userDao.existsByEmail(email);
	}
	
	@Override
	public Role findByName(ERole name) {
		return userDao.findByName(name);
	}

	private void calculateAge(User user) {
		Instant instant = user.getDob().toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
		LocalDate givenDate = zone.toLocalDate();
		Period period = Period.between(givenDate, LocalDate.now());
		user.setAge(period.getYears());
	}

}
