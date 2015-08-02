package com.dstevens.web.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventPageController {
	
	@RequestMapping(value = "/admin/page/events", method = RequestMethod.GET)
	public ModelAndView getTroupesPage() {
		return new ModelAndView("/admin/event/events");
	}
}
