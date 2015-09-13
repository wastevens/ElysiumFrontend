package com.dstevens.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import static com.dstevens.config.AuthorizationReader.authorizationIn;

@Component
public class HydrateUserFilter extends GenericFilterBean {

	private final ElysiumUserDetailsService userService;

	@Autowired
	public HydrateUserFilter(ElysiumUserDetailsService userService) {
		this.userService = userService;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Authorization authorization = authorizationIn(request);
		if(authorization == null) {
			chain.doFilter(request, response);
			return;
		}
		Authentication authentication = userService.authenticationFor(authorization);
		if(authentication == null) {
			chain.doFilter(request, response);
			return;
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

}
