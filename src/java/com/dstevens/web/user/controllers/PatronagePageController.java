package com.dstevens.web.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatronagePageController {

	@RequestMapping(value = "/user/page/patronage", method = RequestMethod.GET)
	public ModelAndView getCharactersPage() {
		return new ModelAndView("/user/patronage");
	}
}
