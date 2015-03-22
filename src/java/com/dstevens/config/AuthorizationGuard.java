package com.dstevens.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dstevens.users.ElysiumUserDetailsService;

import static com.dstevens.config.AuthorizationReader.authorizationIn;

@Component
public class AuthorizationGuard extends HandlerInterceptorAdapter {

	private ElysiumUserDetailsService userService;

	@Autowired
	public AuthorizationGuard(ElysiumUserDetailsService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Authorization authorization = authorizationIn(request);
		if(authorization == null) {
			System.out.println("No authorization found");
			throw new ForbiddenException("Request not authorized");
		}
		if(!userService.isUserAuthorized(authorization)) {
			System.out.println("Authorization failed");
			throw new ForbiddenException("Request not authorized");
		}
		
		return true;
	}

}
