package com.dstevens.web.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dstevens.users.DisplayablePatronage;
import com.dstevens.users.PatronageRepository;
import com.google.gson.Gson;

@Controller
public class PatronageController {

	private final PatronageRepository patronageRepository;

	@Autowired
	public PatronageController(PatronageRepository patronageRepository) {
		this.patronageRepository = patronageRepository;
	}
	
	@RequestMapping(value = "/admin/patronages/{id}", method = RequestMethod.GET)
	public @ResponseBody String getPatronage(@PathVariable String id) {
		return new Gson().toJson(DisplayablePatronage.from(patronageRepository.findPatronageByMembershipId(id)));
	}
	
}
