package com.curisamicus.service.auth;

import com.curisamicus.domain.ERole;
import com.curisamicus.domain.Role;
import com.curisamicus.domain.User;

public interface IAuthenticationService {

	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	public User registerUser(User user);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
	
	public Role findByName(ERole name);
}
