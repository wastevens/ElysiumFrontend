package com.dstevens.config;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired private ElysiumUserDetailsService userDetailService;
	@Autowired private ElysiumAuthenticationSuccessHandler successHandler;
	@Autowired private ElysiumLogoutHandler logoutHandler;
	@Autowired private HydrateUserFilter hydrateUserFilter;
	@Autowired private Supplier<PasswordEncoder> passwordEncoderSupplier;
	@Autowired private ElysiumAuthenticationFailureHandler failureHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoderSupplier.get());
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		auth.authenticationProvider(daoAuthenticationProvider);
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
	         sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
	    	 formLogin().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler).permitAll().and().
	    	 logout().logoutUrl("/logout").addLogoutHandler(logoutHandler). permitAll().and().
	    	 addFilterBefore(hydrateUserFilter, UsernamePasswordAuthenticationFilter.class).
	    	 authorizeRequests().antMatchers("/requestPasswordReset", "/passwordResetRequested").permitAll().and().
	    	 authorizeRequests().antMatchers("/admin/**", "/admin**").hasAnyRole("ADMIN").and().
		     csrf().disable(); 
 
	}
}
