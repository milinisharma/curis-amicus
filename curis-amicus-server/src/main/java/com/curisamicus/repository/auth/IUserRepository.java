package com.curisamicus.repository.auth;

import java.util.List;

import com.curisamicus.domain.User;

public interface IUserRepository {
	
	public List<User> findAll();
	
	public User findByUsername(String username);
	
	public User registerUser(User user);

}
