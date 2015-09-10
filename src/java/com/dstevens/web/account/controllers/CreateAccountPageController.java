package com.dstevens.web.account.controllers;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dstevens.config.Authorization;
import com.dstevens.user.ElysiumUserDetailsService;
import com.dstevens.user.User;
import com.dstevens.user.UserDao;
 
@Controller
public class CreateAccountPageController {
	
	private final UserDao userDao;
	private final ElysiumUserDetailsService userService;
	private final AccountCreator accountCreator;

	@Autowired
	public CreateAccountPageController(UserDao userDao, ElysiumUserDetailsService userService, AccountCreator accountCreator) {
		this.userDao = userDao;
		this.userService = userService;
		this.accountCreator = accountCreator;
	}
	
	@RequestMapping(value = { "/createAccount"}, method = RequestMethod.GET)
	public ModelAndView createAccountPage() {
		return new ModelAndView("/account/createAccount");
	}
	
	@RequestMapping(value = { "/createAccount"}, method = RequestMethod.POST)
	public ModelAndView createAccount(HttpServletRequest request, HttpServletResponse response, 
			                          @RequestParam(value = "email") String email,
			                          @RequestParam(value = "password") String password,
			                          @RequestParam(value = "firstName", required=false) String firstName,
			                          @RequestParam(value = "lastName", required=false) String lastName,
			                          @RequestParam(value = "originalUsername", required=false) String originalUsername,
			                          @RequestParam(value = "paymentReceiptIdentifier", required=false) String paymentReceiptIdentifier) {
		if(userDao.findWithEmail(email) != null) {
			ModelAndView model = new ModelAndView("/account/createAccount");
			model.addObject("error", "An account already exists for user with email address " + email);
			return model;
		}
		User newUser = accountCreator.createUser(email, password, firstName, lastName, originalUsername, paymentReceiptIdentifier);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(newUser, null, newUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
			
		Authorization authorization = userService.authorize(authentication);
		response.addHeader("AUTHORIZATION", authorization.getToken());
		Cookie cookie = new Cookie("AUTHORIZATION", authorization.getToken());
		cookie.setPath("/");
		response.addCookie(cookie);
			
		return new ModelAndView(new RedirectView("/user/main"));
	}
}