package com.dstevens.web.admin.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class AdminMainPageController {
 
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public ModelAndView mainPage() {
		return new ModelAndView("admin/main");
	}
}