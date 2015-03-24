package com.dstevens.web.admin.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dstevens.users.DisplayablePatronage;
import com.dstevens.users.Patronage;
import com.dstevens.users.PatronageRepository;
import com.dstevens.users.User;
import com.dstevens.users.UserRepository;
import com.google.gson.Gson;

@Controller
public class PatronageController {

	private final PatronageRepository patronageRepository;
	private final UserRepository userRepository;

	@Autowired
	public PatronageController(PatronageRepository patronageRepository, UserRepository userRepository) {
		this.patronageRepository = patronageRepository;
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/admin/patronages", method = RequestMethod.GET)
	public @ResponseBody String getPatronages() {
		List<DisplayablePatronage> collect = StreamSupport.stream(patronageRepository.findAllUndeleted().spliterator(), false).
                map((Patronage t) -> DisplayablePatronage.from(t)).
                sorted().
                collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages/unassigned", method = RequestMethod.GET)
	public @ResponseBody String getUnassignedPatronages() {
		List<DisplayablePatronage> collect = StreamSupport.stream(patronageRepository.findAllUndeleted().spliterator(), false).
				filter((Patronage t) -> t.getUser() == null).
				map((Patronage t) -> DisplayablePatronage.from(t)).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages/assigned", method = RequestMethod.GET)
	public @ResponseBody String getAssignedPatronages() {
		List<DisplayablePatronage> collect = StreamSupport.stream(patronageRepository.findAllUndeleted().spliterator(), false).
				filter((Patronage t) -> t.getUser() != null).
				map((Patronage t) -> DisplayablePatronage.from(t)).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages/{id}", method = RequestMethod.GET)
	public @ResponseBody String getPatronage(@PathVariable String id) {
		Patronage findPatronageByMembershipId = patronageRepository.findPatronageByMembershipId(id);
		return new Gson().toJson(DisplayablePatronage.from(findPatronageByMembershipId));
	}
	
	
	@RequestMapping(value = "/admin/patronages", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody String createPatronage(@RequestBody RawRequestBody requestBody, HttpServletResponse response) {
		User user = userRepository.findUser(requestBody.userId);
		if(user != null && user.getPatronage() != null) {
			throw new IllegalArgumentException("User " + requestBody.userId + " already has a patronage.");
		}
		Patronage patronage = patronageRepository.save(new Patronage(requestBody.year, requestBody.expirationAsDate()));
		if(user != null) {
			patronage = patronage.forUser(user);
			user = user.withPatronage(patronage);
			patronage = patronageRepository.save(patronage);
		}
		addLocationHeader(response, patronage);
		return new Gson().toJson(DisplayablePatronage.from(patronage));
	}
	
	@RequestMapping(value = "/admin/patronages/{id}", method = RequestMethod.PUT)
	public @ResponseBody String updatePatronage(@PathVariable String id, @RequestBody RawRequestBody requestBody, HttpServletResponse response) {
		Patronage patronage = patronageRepository.findPatronageByMembershipId(id);
		if(patronage == null) {
			throw new IllegalArgumentException("No patronage " + id + " found");
		}
		User user = userRepository.findUser(requestBody.userId);
		if(user != null && user.getPatronage() != null && !user.getPatronage().equals(patronage)) {
			throw new IllegalArgumentException("Patronage " + user.getPatronage().displayMembershipId() + " is associated with user " + user.getId());
		}
		Patronage updatedPatronage = patronageRepository.save(patronage.expiringOn(requestBody.expirationAsDate()).forUser(user));
		addLocationHeader(response, updatedPatronage);
		return new Gson().toJson(DisplayablePatronage.from(updatedPatronage));
	}

	private void addLocationHeader(HttpServletResponse response, Patronage patronage) {
		response.addHeader("LOCATION", "/admin/patronages/" + patronage.displayMembershipId());
	}
	
	private static class RawRequestBody {
		public Integer year;
		public String expiration;
		public Integer userId;
		
		private Date expirationAsDate() {
			try {
				return new SimpleDateFormat("yyyy-MM").parse(expiration);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Could not parse " + expiration + "; please make sure expiration dates are in yyyy-MM format");
			}
		}
	}
}
