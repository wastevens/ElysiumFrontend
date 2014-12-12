package com.dstevens.web.config.controller;
import static com.dstevens.collections.Sets.set;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.users.Role;
import com.dstevens.users.User;
import com.dstevens.users.UserDao;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
@Controller
public class CreateAccountController {
	
	private UserDao userDao;

	@Autowired
	public CreateAccountController(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping(value = { "/createAccount"}, method = RequestMethod.GET)
	public ModelAndView createAccountPage() {
		return new ModelAndView("createAccount");
	}
	
	@RequestMapping(value = { "/createAccount"}, method = RequestMethod.POST)
	public ModelAndView createAccount(@RequestParam(value = "email") String email,
			                          @RequestParam(value = "username") String username,
			                          @RequestParam(value = "password") String password) {
		if(userDao.findWithEmail(email) != null) {
			ModelAndView model = new ModelAndView("createAccount");
			model.addObject("error", "An account already exists for user with email address " + email);
			return model;
		}
		if(userDao.findWithName(username) != null) {
			ModelAndView model = new ModelAndView("createAccount");
			model.addObject("error", "An account already exists for user with name " + username);
			return model;
		}
		userDao.save(new User(username, email, password, set(Role.USER)));
		sendConfirmatoryEmailTo(email);
		return new ModelAndView("/user/main");
	}

	private void sendConfirmatoryEmailTo(String email) {
        Properties properties = new Properties();
        Session session = Session.getDefaultInstance(properties);

        String msgBody = "Your Underground Theater User Account has been created";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("database@undergroundtheater.org", "UT Database Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(email));
            msg.setSubject("Your Underground Theater User Account has been created");
            msg.setText(msgBody);
            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
        	throw new IllegalStateException("Failed to send message to " + email, e);
        }
	}
}