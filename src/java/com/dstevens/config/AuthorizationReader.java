package com.dstevens.config;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

public class AuthorizationReader {

	public static Authorization authorizationIn(ServletRequest request) {
		return authorizationIn((HttpServletRequest) request);
	}
	
	public static Authorization authorizationIn(HttpServletRequest request) {
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
