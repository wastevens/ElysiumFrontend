package com.dstevens.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.dstevens.users.ElysiumUserDetailsService;
 
@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired private ElysiumUserDetailsService userDetailService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.
	    	formLogin().loginPage("/login").successHandler(new ElysiumAuthenticationSuccessHandler()).failureUrl("/login?error").permitAll().and().
	    	logout().logoutUrl("/logout").permitAll().and().
	        authorizeRequests().antMatchers("/createAccount/**").permitAll().and().
	        authorizeRequests().antMatchers("/resetPassword/**").permitAll().and().
	        authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and().
	        authorizeRequests().antMatchers("/troupes/**").hasRole("USER").and().
	        authorizeRequests().antMatchers("/characters/**").hasRole("USER").and().
	    	authorizeRequests().antMatchers("/user/**").hasRole("USER").and().
	    	authorizeRequests().antMatchers("/traits/**").hasRole("USER").and().
		    csrf(); 
 
	}
}
