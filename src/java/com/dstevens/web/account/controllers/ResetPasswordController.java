package com.dstevens.web.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dstevens.mail.MailMessageFactory;
import com.dstevens.user.User;
import com.dstevens.user.UserDao;
import com.dstevens.users.security.UserPasswordResetTokenService;

@Controller
public class ResetPasswordController {

	private final UserDao userDao;
	private final MailMessageFactory messageFactory;
	private final UserPasswordResetTokenService passwordResetTokenService;

	@Autowired
	public ResetPasswordController(UserDao userDao, MailMessageFactory messageFactory, UserPasswordResetTokenService passwordResetTokenService) {
		this.userDao = userDao;
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
		body.append("A password reset request for your email address was made of the Underground Theater character database.  If you did not make this request, feel free to ignore this message.");
		body.append("If you would like to reset your password, please go to " + "http://localhost:8080/resetPassword");
		body.append("Your password reset code is " + token);
		messageFactory.message().
		               from("database@undergroundtheater.org", "UT Database Admin").
		               to(email).
		               subject("Password Reset Request for your Underground Theater character database account").
		               body(body.toString()).
		               send();
	}
	
	@RequestMapping(value = { "/resetPassword"}, method = RequestMethod.GET)
	public ModelAndView resetPasswordPage() {
		return new ModelAndView("/account/resetPassword");
	}
	
	@RequestMapping(value = { "/resetPassword"}, method = RequestMethod.POST)
	public ModelAndView resetPassword(@RequestParam(value = "email") String email,
			                          @RequestParam(value = "resetPasswordToken") String token,
			                          @RequestParam(value = "newPassword") String password) {
		if(userDao.findWithEmail(email) != null && 
		   passwordResetTokenService.isResetPasswordTokenValid(email, token)) {
			User userWithUpdatedPassword = userDao.save(userDao.findWithEmail(email).withPassword(password));
			sendPasswordResetEmailTo(email);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userWithUpdatedPassword, null, userWithUpdatedPassword.getAuthorities()));
			return new ModelAndView(new RedirectView("/user/main"));
		}
		return new ModelAndView("login");
	}

	private void sendPasswordResetEmailTo(String email) {
		messageFactory.message().
					   from("database@undergroundtheater.org", "UT Database Admin").
		               to(email).
		               subject("Your Underground Theater password has been changed").
		               body("Your Underground Theater account's password has been changed.").
		               send();
	}
	
}
