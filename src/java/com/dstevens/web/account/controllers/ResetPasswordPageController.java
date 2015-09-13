package com.dstevens.web.account.controllers;

import java.util.function.Supplier;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dstevens.config.Authorization;
import com.dstevens.config.ElysiumUserDetailsService;
import com.dstevens.mail.MailMessageSupplier;
import com.dstevens.user.User;
import com.dstevens.user.UserDao;
import com.dstevens.user.security.UserPasswordResetTokenService;

@Controller
public class ResetPasswordPageController {

	private final Supplier<PasswordEncoder> passwordEncoderSupplier;
	private final UserDao userDao;
	private final MailMessageSupplier messageFactory;
	private final UserPasswordResetTokenService passwordResetTokenService;
	private final ElysiumUserDetailsService userService;

	@Autowired
	public ResetPasswordPageController(Supplier<PasswordEncoder> passwordEncoderSupplier, UserDao userDao, ElysiumUserDetailsService userService, MailMessageSupplier messageFactory, UserPasswordResetTokenService passwordResetTokenService) {
		this.passwordEncoderSupplier = passwordEncoderSupplier;
		this.userDao = userDao;
		this.userService = userService;
		this.messageFactory = messageFactory;
		this.passwordResetTokenService = passwordResetTokenService;
	}
	
	@RequestMapping(value = { "/requestPasswordReset"}, method = RequestMethod.GET)
	public ModelAndView requestPasswordResetPage() {
		return new ModelAndView("/account/requestPasswordReset");
	}
	
	@RequestMapping(value = { "/passwordResetRequested"}, method = RequestMethod.POST)
	public ModelAndView passwordResetRequested(@RequestParam(value = "email") String email) {
		passwordResetRequestMessage(email, passwordResetTokenService.produceTokenExpiringIn(email, 5));
		
		return new ModelAndView("login");
	}

	private void passwordResetRequestMessage(String email, String token) {
		StringBuilder body = new StringBuilder();
		body.append("A password reset request for your email address was made of the Underground Theater character database.  If you did not make this request, feel free to ignore this message.\n");
		body.append("If you would like to reset your password, please go to " + "http://localhost:8080/resetPassword\n");
		body.append("Your password reset code is " + token);
		messageFactory.get().
		 from("database@undergroundtheater.org", "UT Database").
		 to(email).
		 subject("Password Reset Request for your Underground Theater Database account").
		 body(body.toString()).send();
	}
	
	@RequestMapping(value = { "/resetPassword"}, method = RequestMethod.GET)
	public ModelAndView resetPasswordPage() {
		return new ModelAndView("/account/resetPassword");
	}
	
	@RequestMapping(value = { "/resetPassword"}, method = RequestMethod.POST)
	public ModelAndView resetPassword(HttpServletRequest request, HttpServletResponse response,
			                          @RequestParam(value = "email") String email,
			                          @RequestParam(value = "resetPasswordToken") String token,
			                          @RequestParam(value = "newPassword") String password) {
		if(userDao.findWithEmail(email) != null && 
		   passwordResetTokenService.isResetPasswordTokenValid(email, token)) {
			User userWithUpdatedPassword = userDao.save(userDao.findWithEmail(email).withPassword(passwordEncoderSupplier.get().encode(password)));
			sendPasswordResetEmailTo(email);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userWithUpdatedPassword, null, userWithUpdatedPassword.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			Authorization authorization = userService.authorize(authentication);
			response.addHeader("AUTHORIZATION", authorization.getToken());
			Cookie cookie = new Cookie("AUTHORIZATION", authorization.getToken());
			cookie.setPath("/");
			response.addCookie(cookie);
				
			return new ModelAndView(new RedirectView("/user/main"));
		}
		return new ModelAndView("login");
	}

	private void sendPasswordResetEmailTo(String email) {
		messageFactory.get().
		               from("database@undergroundtheater.org", "UT Database").
		               to(email).
		               subject("Your Underground Theater password has been changed").
		               body("Your Underground Theater account's password has been changed.").
		               send();
	}
	
}
