package com.dstevens.web.admin.controllers;

import java.time.Year;
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

import com.dstevens.config.controllers.BadRequestException;
import com.dstevens.config.controllers.ResourceNotFoundException;
import com.dstevens.user.User;
import com.dstevens.user.UserRepository;
import com.dstevens.user.patronage.DisplayablePatronage;
import com.dstevens.user.patronage.Patronage;
import com.dstevens.user.patronage.PatronageRepository;
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
                map((Patronage t) -> DisplayablePatronage.fromOn(t, new Date())).
                sorted().
                collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages/{id}", method = RequestMethod.GET)
	public @ResponseBody String getPatronage(@PathVariable String id) {
		return new Gson().toJson(DisplayablePatronage.fromOn(findPatronage(id), new Date()));
	}
	
	private Patronage findPatronage(String id) {
		Patronage patronage = patronageRepository.findPatronageByMembershipId(id);
		if(patronage == null) {
			throw new ResourceNotFoundException("Did not find patronage with membership id " + id);
		}
		return patronage;
	}
	
	@RequestMapping(value = "/admin/patronages/unassigned", method = RequestMethod.GET)
	public @ResponseBody String getUnassignedPatronages() {
		List<DisplayablePatronage> collect = StreamSupport.stream(patronageRepository.findAllUndeleted().spliterator(), false).
				filter((Patronage t) -> t.getUser() == null).
				map((Patronage t) -> DisplayablePatronage.fromOn(t, new Date())).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages/assigned", method = RequestMethod.GET)
	public @ResponseBody String getAssignedPatronages() {
		List<DisplayablePatronage> collect = StreamSupport.stream(patronageRepository.findAllUndeleted().spliterator(), false).
				filter((Patronage t) -> t.getUser() != null).
				map((Patronage t) -> DisplayablePatronage.fromOn(t, new Date())).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody String createPatronage(@RequestBody DisplayablePatronage requestBody, HttpServletResponse response) {
		User user = userRepository.findUser(requestBody.userId);
		if(user != null && user.getPatronage() != null) {
			throw new BadRequestException("User " + requestBody.userId + " already has a patronage.");
		}
		Patronage patronage = patronageRepository.save(requestBody.toPatronage(Year.now().getValue()));
		if(user != null) {
			user = userRepository.save(user.withPatronage(patronage));
			patronage = patronage.forUser(user);
//			patronage = patronageRepository.save(patronage);
		}
		addPatronageLocationHeader(response, patronage);
		return new Gson().toJson(DisplayablePatronage.fromOn(patronage, new Date()));
	}
	
	@RequestMapping(value = "/admin/patronages/{id}", method = RequestMethod.PUT)
	public @ResponseBody String updatePatronage(@PathVariable String id, @RequestBody DisplayablePatronage requestBody, HttpServletResponse response) {
		Patronage patronage = findPatronage(id);
		if(patronage == null) {
			throw new ResourceNotFoundException("No patronage " + id + " found");
		}
		User user = userRepository.findUser(requestBody.userId);
		Patronage patronageToSave = requestBody.toPatronage(Year.now().getValue());
		
		if(user == null) {
			if(patronage.getUser() != null) {
				throw new BadRequestException("Patronage " + patronage.displayMembershipId() + " is associated with user " + patronage.getUser().getId());
			}
		} else {
			if(patronage.getUser() != null && user.getPatronage() != null) {
				if(patronage.getUser().getId() == user.getId() &&
				   patronage.getId() == user.getPatronage().getId()) {
					patronageToSave = patronageToSave.forUser(user);
			   } else {
				   throw new BadRequestException("Patronage " + patronage.displayMembershipId() + " is associated with user " + patronage.getUser().getId());
			   }
			} else if(patronage.getUser() == null && user.getPatronage() == null) {
				patronageToSave = patronageToSave.forUser(user);
			} else {
				throw new BadRequestException("Could not associate patronage " + patronage.displayMembershipId() + " with user " + user);
			}
		}
		
		Patronage updatedPatronage = patronageRepository.save(patronage.updateTo(patronageToSave));
		addPatronageLocationHeader(response, updatedPatronage);
		return new Gson().toJson(DisplayablePatronage.fromOn(updatedPatronage, new Date()));
	}

	private void addPatronageLocationHeader(HttpServletResponse response, Patronage patronage) {
		response.addHeader("LOCATION", "/admin/patronages/" + patronage.displayMembershipId());
	}
}
