package com.dstevens.web.user.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.suppliers.ClockSupplier;
import com.dstevens.user.DisplayableUser;
import com.dstevens.user.User;
import com.dstevens.web.config.RequestingUserProvider;
import com.google.gson.Gson;

@Controller
public class PatronagePageController {
	
	private RequestingUserProvider requestingUserProvider;
	private ClockSupplier clockSupplier;

	@Autowired
	public PatronagePageController(RequestingUserProvider requestingUserProvider, ClockSupplier clockSupplier) {
		this.requestingUserProvider = requestingUserProvider;
		this.clockSupplier = clockSupplier;
		
	}
	
	@RequestMapping(value = "/user/page/patronage", method = RequestMethod.GET)
	public ModelAndView getCharactersPage() {
		ModelAndView modelAndView = new ModelAndView("/user/patronage");
		User user = requestingUserProvider.get();
		String json = new Gson().toJson(DisplayableUser.fromOn(user, Date.from(clockSupplier.get().instant())));
		modelAndView.addObject("user", json);
		return modelAndView;
	}
}
