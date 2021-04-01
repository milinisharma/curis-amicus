package com.curisamicus.controller.auth;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curisamicus.domain.auth.User;
import com.curisamicus.service.auth.impl.LoginServiceImpl;

@RestController
@RequestMapping("/rest/loginRestService/")
public class LoginRestService {

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@GetMapping("findByUsername")
	public User findByUsername(@RequestParam String username) throws InterruptedException, ExecutionException {
		return loginServiceImpl.findByUsername(username);
	}
}
