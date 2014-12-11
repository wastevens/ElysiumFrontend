package com.dstevens.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ElysiumAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch((GrantedAuthority t) -> t.getAuthority().contains("ADMIN"))) {
			return "/admin/main";
		}
		return "/user/main";
		
	}
	
}
