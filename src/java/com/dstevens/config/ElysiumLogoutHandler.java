package com.dstevens.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import static com.dstevens.config.AuthorizationReader.authorizationIn;

@Component
public class ElysiumLogoutHandler implements LogoutHandler {

	private ElysiumUserDetailsService userService;

	@Autowired
    public ElysiumLogoutHandler(ElysiumUserDetailsService userService) {
		this.userService = userService;
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		userService.removeAuthorizationFor(authorizationIn(request));
	}

}
