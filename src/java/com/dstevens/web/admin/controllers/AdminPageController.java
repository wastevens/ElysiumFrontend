package com.dstevens.web.admin.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class AdminPageController {
 
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public ModelAndView mainPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Admin Page");
		model.addObject("message", "This is a page for administrators");
		model.setViewName("admin/main");
 
		return model;
	}
}