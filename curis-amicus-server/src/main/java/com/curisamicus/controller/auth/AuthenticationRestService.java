package com.curisamicus.controller.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curisamicus.domain.ERole;
import com.curisamicus.domain.Role;
import com.curisamicus.domain.User;
import com.curisamicus.domain.payload.request.LoginRequest;
import com.curisamicus.domain.payload.request.RegisterRequest;
import com.curisamicus.domain.payload.response.JwtResponse;
import com.curisamicus.domain.payload.response.MessageResponse;
import com.curisamicus.security.jwt.JwtUtils;
import com.curisamicus.security.service.UserDetailsImpl;
import com.curisamicus.service.auth.impl.AuthenticationServiceImpl;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest/authRestService/")
@Slf4j
public class AuthenticationRestService {

	@Autowired
	private AuthenticationServiceImpl authServiceImpl;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@GetMapping("findByUsername")
	public ResponseEntity<User> findByUsername(@RequestParam String username) throws InterruptedException, ExecutionException {
		
		log.info("Retrieving user by username = " + username);
		User user = authServiceImpl.findByUsername(username);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Responded", "AuthenticationRestService.findByUsername");
		log.info("User retrieved : " + user.toString());
		return ResponseEntity.accepted().headers(headers).body(user);
	}
	
	@PostMapping("authenticateUser")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUser().getUsername(), loginRequest.getUser().getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(JwtResponse.builder()
				.token(jwt)
				.id(userDetails.getId())
				.username(userDetails.getUsername())
				.email(userDetails.getEmail())
				.roles(roles)
				.build());
	}
	
	@PutMapping("registerUser")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
		if (authServiceImpl.existsByUsername(registerRequest.getUser().getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(MessageResponse.builder().message("Error: Username is already taken!").build());
		}

		if (authServiceImpl.existsByEmail(registerRequest.getUser().getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(MessageResponse.builder().message("Error: Email is already in use!").build());
		}

		// Create new user's account
		User user = registerRequest.getUser();

		Set<Role> strRoles = registerRequest.getUser().getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			
			try {
				Role userRole = authServiceImpl.findByName(ERole.ROLE_USER);
				roles.add(userRole);
			} 
			catch (Exception e) {
				log.error("Error: Role is not found.");
			}
		} 
		else {
			strRoles.forEach(role -> {
				switch (role.getName().name()) {
				case "admin":
					try {
						Role adminRole = authServiceImpl.findByName(ERole.ROLE_ADMIN);
						roles.add(adminRole);
					}
					catch(Exception e) {
						log.error("Error: Role is not found.");
					}
					break;
				case "mod":
					try {
						Role modRole = authServiceImpl.findByName(ERole.ROLE_MODERATOR);
						roles.add(modRole);
					}
					catch(Exception e) {
						log.error("Error: Role is not found");
					}
					break;
				default:
					try {
						Role userRole = authServiceImpl.findByName(ERole.ROLE_USER);
						roles.add(userRole);
					}
					catch(Exception e) {
						log.error("Error: Role is not found.");
					}
				}
			});
		}

		user.setRoles(roles);
		authServiceImpl.registerUser(user);

		return ResponseEntity.ok(MessageResponse.builder().message("User registered successfully!").build());
	}
	
}
