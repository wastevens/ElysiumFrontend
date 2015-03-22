package com.dstevens.web.admin.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dstevens.users.DisplayablePatronage;
import com.dstevens.users.Patronage;
import com.dstevens.users.PatronageRepository;
import com.google.gson.Gson;

@Controller
public class PatronageController {

	private final PatronageRepository patronageRepository;

	@Autowired
	public PatronageController(PatronageRepository patronageRepository) {
		this.patronageRepository = patronageRepository;
	}
	
	@RequestMapping(value = "/admin/patronages", method = RequestMethod.POST)
	public @ResponseBody String createPatronage(@RequestBody RawRequestBody requestBody, HttpServletResponse response) {
		Patronage savedPatronage = patronageRepository.save(new Patronage(requestBody.year, requestBody.expirationAsDate()));
		response.addHeader("LOCATION", "/admin/patronages/" + savedPatronage.displayMembershipId());
		return new Gson().toJson(DisplayablePatronage.from(savedPatronage));
	}
	
	@RequestMapping(value = "/admin/patronages/{id}", method = RequestMethod.GET)
	public @ResponseBody String getPatronage(@PathVariable String id) {
		return new Gson().toJson(DisplayablePatronage.from(patronageRepository.findPatronageByMembershipId(id)));
	}
	
	private static class RawRequestBody {
		public Integer year;
		public String expiration;
		
		private Date expirationAsDate() {
			try {
				return new SimpleDateFormat("yyyy-MM").parse(expiration);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Could not parse " + expiration + "; please make sure expiration dates are in yyyy-MM format");
			}
		}
	}
}
