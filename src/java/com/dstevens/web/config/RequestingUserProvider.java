package com.dstevens.web.config;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dstevens.users.User;
import com.dstevens.users.UserRepository;

@Service
public class RequestingUserProvider implements Supplier<User> {

	private UserRepository repository;

	@Autowired
	public RequestingUserProvider(UserRepository repository) {
		this.repository = repository;
	}
	
	public User get() {
		return repository.findUser(loggedInUser().getId());
	}

	private User loggedInUser() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
