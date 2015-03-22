package com.dstevens.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.dstevens.users.ElysiumUserDetailsService;

@Component
public class ElysiumLogoutHandler implements LogoutHandler {

	private ElysiumUserDetailsService userService;

	@Autowired
    public ElysiumLogoutHandler(ElysiumUserDetailsService userService) {
		this.userService = userService;
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		userService.removeAuthorizationFor(getAuthorizationFrom(request));
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
