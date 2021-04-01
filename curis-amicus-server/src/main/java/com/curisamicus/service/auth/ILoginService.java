package com.curisamicus.service.auth;

import java.util.concurrent.ExecutionException;

import com.curisamicus.domain.auth.User;

public interface ILoginService {

	public User findByUsername(String username) throws InterruptedException, ExecutionException;
}
