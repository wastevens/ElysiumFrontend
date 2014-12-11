package com.dstevens.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
 
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
 
	    http.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ROLE_USER')")
		.and()
		    .formLogin().defaultSuccessUrl("/admin").
		    	loginPage("/login").failureUrl("/login?error").permitAll().
//		    	usernameParameter("username").passwordParameter("password")
		    and().
		    logout().logoutSuccessUrl("/login?logout").
		and().csrf(); 
 
	}
}
