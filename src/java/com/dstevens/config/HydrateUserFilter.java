package com.dstevens.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.WebUtils;

import com.dstevens.users.ElysiumUserDetailsService;

@Component
public class HydrateUserFilter extends GenericFilterBean {

	private final ElysiumUserDetailsService userService;

	@Autowired
	public HydrateUserFilter(ElysiumUserDetailsService userService) {
		this.userService = userService;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Authorization authorization = getAuthorizationFrom((HttpServletRequest) request);
		if(authorization == null) {
			chain.doFilter(request, response);
			return;
		}
		Authentication authenticationFor = userService.authenticationFor(authorization);
		if(authenticationFor == null) {
			chain.doFilter(request, response);
			return;
		}
		SecurityContextHolder.getContext().setAuthentication(authenticationFor);
		chain.doFilter(request, response);
	}
	
	private Authorization getAuthorizationFrom(HttpServletRequest request) {
		String token = request.getHeader("AUTHORIZATION");
		if(token == null) {
			Cookie cookie = WebUtils.getCookie(request, "AUTHORIZATION");
			if(cookie != null) {
				token = cookie.getValue(); 
			}
		}
		if(token != null) {
			return Authorization.from(token);
		}
		return null;
	}

}
