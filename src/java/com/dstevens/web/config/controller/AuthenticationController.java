package com.dstevens.web.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dstevens.config.AuthorizationToken;
import com.dstevens.config.ForbiddenException;
import com.dstevens.users.ElysiumUserDetailsService;

@Controller
public class AuthenticationController {

	private ElysiumUserDetailsService userService;

	@Autowired
	public AuthenticationController(ElysiumUserDetailsService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public AuthorizationToken authenticate(@RequestBody AuthorizationRequest request) {
		UserDetails userDetails = userService.loadUserByUsername(request.username);
		if(userDetails.getUsername().equals(request.username) &&
		   userDetails.getPassword().equals(request.password)) {
			return userService.authorize(request.username);
		}
		throw new ForbiddenException("User not authenticated");
	}
	
	private static class AuthorizationRequest {
		public String username;
		public String password;
	}
	
}
