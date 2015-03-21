package com.dstevens.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.dstevens.users.ElysiumUserDetailsService;

@Component
public class TokenAuthorizationInterceptor extends HandlerInterceptorAdapter {

	private ElysiumUserDetailsService userService;

	@Autowired
	public TokenAuthorizationInterceptor(ElysiumUserDetailsService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authorization = getAuthorizationFrom(request);
		if(authorization == null) {
			System.out.println("No authorization found");
			throw new ForbiddenException("Request not authorized");
		}
		String username = request.getUserPrincipal().getName();
		if(!userService.isUserAuthorized(username, authorization)) {
			System.out.println("Authorization failed");
			throw new ForbiddenException("Request not authorized");
		}
		
		return true;
	}

	private String getAuthorizationFrom(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("AUTHORIZATION");
		if(authorizationHeader == null) {
			Cookie cookie = WebUtils.getCookie(request, "AUTHORIZATION");
			if(cookie != null) {
				authorizationHeader = cookie.getValue(); 
			}
		}
		return authorizationHeader;
	}

}
