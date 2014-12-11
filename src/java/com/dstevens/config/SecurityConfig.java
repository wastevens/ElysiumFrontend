package com.dstevens.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("admin").password("password").roles("USER", "ADMIN").and()
	                               .withUser("lead_storyteller").password("password").roles("USER", "PATRON", "AST", "LST").and()
	                               .withUser("assistant_storyteller").password("password").roles("USER", "PATRON", "AST").and()
	                               .withUser("patron").password("password").roles("USER", "PATRON").and()
	                               .withUser("user").password("password").roles("USER");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.
	         authorizeRequests().antMatchers("/user/**").hasRole("USER").and().
	         authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and().
	    	 formLogin().loginPage("/login").successHandler(new ElysiumAuthenticationSuccessHandler()).permitAll().and().
	    	 logout().logoutUrl("/logout").addLogoutHandler(logoutHandler()).permitAll().and().
		     csrf(); 
 
	}

	private LogoutHandler logoutHandler() {
		return new LogoutHandler() {
			@Override
			public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
				authentication.setAuthenticated(false);
			}
		};
	}
}
