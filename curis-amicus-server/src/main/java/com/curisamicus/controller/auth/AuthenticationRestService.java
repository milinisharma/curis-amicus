package com.curisamicus.controller.auth;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curisamicus.domain.User;
import com.curisamicus.service.auth.impl.AuthenticationServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest/authRestService/")
@Slf4j
public class AuthenticationRestService {

	@Autowired
	private AuthenticationServiceImpl authServiceImpl;

	@GetMapping("findByUsername")
	public ResponseEntity<User> findByUsername(@RequestParam String username) throws InterruptedException, ExecutionException {
		
		log.info("Retrieving user by username = " + username);
		User user = authServiceImpl.findByUsername(username);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Responded", "AuthenticationRestService.findByUsername");
		log.info("User retrieved : " + user.toString());
		return ResponseEntity.accepted().headers(headers).body(user);
	}
	
	@PutMapping("registerUser")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		log.info("Trying to register user...");
		User registerUser = authServiceImpl.registerUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Responded", "AuthenticationRestService.registerUser");
		log.info("User Registered: " + registerUser.toString());
		return new ResponseEntity<String>("User Registered Successfully", HttpStatus.OK);
	}
	
}
