package com.dstevens.web.user.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class UserMainPageController {
 
	@RequestMapping(value = "/user/main", method = RequestMethod.GET)
	public ModelAndView userPage() {
		return new ModelAndView("user/main");
	}
}