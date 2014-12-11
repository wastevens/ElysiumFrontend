package com.dstevens.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ElysiumAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Credentials: " + auth.getCredentials());
		System.out.println("Authorities: " + auth.getAuthorities());
		System.out.println("Details: " + auth.getDetails());
		System.out.println("Principal: " + auth.getPrincipal());
		
		String targetUrl = "/user/main";
		if(auth.getAuthorities().stream().anyMatch((GrantedAuthority t) -> t.getAuthority().contains("ADMIN"))) {
			targetUrl = "/admin/main";
		}
		System.out.println("targetUrl: " + targetUrl);
		return targetUrl;
		
	}
	
}
