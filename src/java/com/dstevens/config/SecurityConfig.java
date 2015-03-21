package com.dstevens.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.dstevens.users.ElysiumUserDetailsService;
 
@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired private ElysiumUserDetailsService userDetailService;
	@Autowired private ElysiumAuthenticationSuccessHandler successHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.
	         sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
	    	 formLogin().loginPage("/login").successHandler(successHandler).failureUrl("/login?error").permitAll().and().
	    	 logout().logoutUrl("/logout").permitAll().and().
	    	 authorizeRequests().anyRequest().permitAll().and().
		     csrf().disable(); 
 
	}
}
