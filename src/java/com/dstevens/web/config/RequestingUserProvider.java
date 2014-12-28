package com.dstevens.web.config;

import java.util.function.Supplier;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dstevens.users.User;

@Service
public class RequestingUserProvider implements Supplier<User> {

	public User get() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
