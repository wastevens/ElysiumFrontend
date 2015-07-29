package com.dstevens.web.config;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dstevens.user.User;
import com.dstevens.user.UserRepository;

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
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof User) {
			return (User)principal;
		}
		String username = (String)principal;
		User user = repository.findUserWithEmail(username);
		if(user == null) {
			throw new IllegalStateException("User not found with name " + username);
		}
		return user;
	}
	
}
