package com.dstevens.web.account.controllers;
import static com.dstevens.collections.Sets.set;

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
import com.dstevens.users.Role;
import com.dstevens.users.User;
import com.dstevens.users.UserDao;
 
@Controller
public class CreateAccountController {
	
	private UserDao userDao;
	private MailMessageFactory messageFactory;

	@Autowired
	public CreateAccountController(UserDao userDao, MailMessageFactory messageFactory) {
		this.userDao = userDao;
		this.messageFactory = messageFactory;
	}
	
	@RequestMapping(value = { "/createAccount"}, method = RequestMethod.GET)
	public ModelAndView createAccountPage() {
		return new ModelAndView("/account/createAccount");
	}
	
	@RequestMapping(value = { "/createAccount"}, method = RequestMethod.POST)
	public ModelAndView createAccount(@RequestParam(value = "email") String email,
			                          @RequestParam(value = "username") String username,
			                          @RequestParam(value = "password") String password) {
		if(userDao.findWithEmail(email) != null) {
			ModelAndView model = new ModelAndView("/account/createAccount");
			model.addObject("error", "An account already exists for user with email address " + email);
			return model;
		}
		if(userDao.findWithName(username) != null) {
			ModelAndView model = new ModelAndView("/account/createAccount");
			model.addObject("error", "An account already exists for user with name " + username);
			return model;
		}
		User newUser = userDao.save(new User(username, email, password, set(Role.USER)));
		sendConfirmatoryEmailTo(email);
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(newUser, null, newUser.getAuthorities()));
		return new ModelAndView(new RedirectView("/user/main"));
	}

	private void sendConfirmatoryEmailTo(String email) {
		messageFactory.message().
					   from("database@undergroundtheater.org", "UT Database Admin").
		               to(email).
		               subject("Your Underground Theater User Account has been created").
		               body("Thank you for creating an account with Underground Theater's character database.").
		               send();
	}
}