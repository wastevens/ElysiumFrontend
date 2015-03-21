package com.dstevens.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dstevens.users.ElysiumUserDetailsService;

@Component
public class ElysiumAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private ElysiumUserDetailsService userService;

	@Autowired
	public ElysiumAuthenticationSuccessHandler(ElysiumUserDetailsService userService) {
		this.userService = userService;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String authorization = userService.authorize(authentication.getName());
		response.addHeader("AUTHORIZATION", authorization);
		Cookie cookie = new Cookie("AUTHORIZATION", authorization);
		cookie.setPath("/");
		response.addCookie(cookie);
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch((GrantedAuthority t) -> t.getAuthority().contains("ADMIN"))) {
			return "/admin/main";
		}
		return "/user/main";
	}
	
}
