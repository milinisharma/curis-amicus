package com.curisamicus.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curisamicus.domain.User;
import com.curisamicus.repository.auth.impl.UserRepositoryImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepositoryImpl userRepositoryImpl;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			User user = userRepositoryImpl.findByUsername(username);
			return UserDetailsImpl.build(user);
		} 
		catch (Exception e) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		
	}

}
