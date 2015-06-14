package com.dstevens.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class ElysiumAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	public ElysiumAuthenticationFailureHandler() {
        super("/login?error");
    }
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		System.err.println("Login failed: " + exception);
		
		super.onAuthenticationFailure(request, response, exception);
	}
}
