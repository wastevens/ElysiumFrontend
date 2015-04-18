package com.dstevens.web.admin.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
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
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.config.exceptions.BadRequestException;
import com.dstevens.config.exceptions.ResourceNotFoundException;
import com.dstevens.user.DisplayableUser;
import com.dstevens.user.Role;
import com.dstevens.user.User;
import com.dstevens.user.UserRepository;
import com.dstevens.users.patronage.Patronage;
import com.dstevens.users.patronage.PatronageRepository;
import com.google.gson.Gson;

@Controller
public class UserController {

	private final UserRepository userRepository;
	private final PatronageRepository patronageRepository;

	@Autowired
	public UserController(UserRepository userRepository, PatronageRepository patronageRepository) {
		this.userRepository = userRepository;
		this.patronageRepository = patronageRepository;
	}
	
	@RequestMapping(value = "/admin/page/users", method = RequestMethod.GET)
	public ModelAndView getUsersPage() {
		return new ModelAndView("/admin/users");
	}
	
	@RequestMapping(value = "/admin/users", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody String createUser(@RequestBody DisplayableUser userWrapper, HttpServletResponse response) {
		Patronage patronage = patronageRepository.findPatronageByMembershipId(userWrapper.membershipId);
		if(patronage != null && patronage.getUser() != null) {
			throw new IllegalArgumentException("Patronage " + userWrapper.membershipId + " already has a user.");
		}
		User user = userRepository.save(new User(userWrapper.getEmail(), userWrapper.getPassword(), userWrapper.getRoles().stream().map((Integer t) -> Role.values()[t]).collect(Collectors.toSet())).withFirstName(userWrapper.getFirstName()).withLastName(userWrapper.getLastName()));
		if(patronage != null) {
			user = user.withPatronage(patronage);
			patronage = patronage.forUser(user);
			user = userRepository.save(user);
		}
		addLocationHeader(response, user);
		return new Gson().toJson(DisplayableUser.fromUserOn(new Date()).apply(user));
	}
	
	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.PUT)
	public @ResponseBody String updateUser(@PathVariable Integer id, @RequestBody DisplayableUser userWrapper, HttpServletResponse response) {
		User user = userRepository.findUser(id);
		if(user == null) {
			throw new ResourceNotFoundException("No user " + id + " found");
		}
		User userToSave = user.withEmail(userWrapper.email).withFirstName(userWrapper.firstName).withLastName(userWrapper.lastName).withRoles(userWrapper.roles());
		
		Patronage patronage = patronageRepository.findPatronageByMembershipId(userWrapper.membershipId);
		if(patronage == null) {
			if(user.getPatronage() != null) {
				throw new BadRequestException("User " + user.getId() + " is associated with patronage " + user.getPatronage().displayMembershipId());
			}
		} else {
			if(patronage.getUser() != null && user.getPatronage() != null) {
				if(patronage.getUser().getId() == user.getId() &&
				   patronage.getId() == user.getPatronage().getId()) {
				   userToSave = userToSave.withPatronage(patronage);
			   } else {
				   throw new BadRequestException("User " + user.getId() + " is associated with patronage " + user.getPatronage().displayMembershipId());
			   }
			} else if(patronage.getUser() == null && user.getPatronage() == null) {
				userToSave = userToSave.withPatronage(patronage);
			} else {
				throw new BadRequestException("Could not associate user " + user.getId() + " cannot be associated with patronage " + patronage);
			}
		}
		
		User updatedUser = userRepository.save(userToSave);
		addLocationHeader(response, updatedUser);
		return new Gson().toJson(DisplayableUser.fromUserOn(new Date()).apply(updatedUser));

	}

	private void addLocationHeader(HttpServletResponse response, User user) {
		response.addHeader("LOCATION", "/admin/users/" + user.getId());
	}
	
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public @ResponseBody String getUsers() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				                                      map(DisplayableUser.fromUserOn(new Date())).
				                                      sorted().
				                                      collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
	public @ResponseBody String getUser(@PathVariable Integer id) {
		return new Gson().toJson(DisplayableUser.fromUserOn(new Date()).apply(userRepository.findUser(id)));
	}
	
	@RequestMapping(value = "/admin/users/clients", method = RequestMethod.GET)
	public @ResponseBody String getClients() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findClients().spliterator(), false).
				map(DisplayableUser.fromUserOn(new Date())).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons", method = RequestMethod.GET)
	public @ResponseBody String getPatrons() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findPatrons().spliterator(), false).
				map(DisplayableUser.fromUserOn(new Date())).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons/active", method = RequestMethod.GET)
	public @ResponseBody String getActivePatrons() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findPatrons().spliterator(), false).
				map(DisplayableUser.fromUserOn(new Date())).
				filter((DisplayableUser t) -> t.activePatron).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons/active/{year}/{month}", method = RequestMethod.GET)
	public @ResponseBody String getActivePatronsOn(@PathVariable int year, @PathVariable int month) {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findPatrons().spliterator(), false).
				map(DisplayableUser.fromUserOn(dateOf(year, month))).
				filter((DisplayableUser t) -> t.activePatron).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons/expired", method = RequestMethod.GET)
	public @ResponseBody String getInactivePatrons() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				map(DisplayableUser.fromUserOn(new Date())).
				filter((DisplayableUser t) -> t.membershipId != null && !t.activePatron).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons/expired/{year}/{month}", method = RequestMethod.GET)
	public @ResponseBody String getInactivePatronsOn(@PathVariable int year, @PathVariable int month) {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findPatrons().spliterator(), false).
				map(DisplayableUser.fromUserOn(dateOf(year, month))).
				filter((DisplayableUser t) -> !t.activePatron).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}

	private Date dateOf(int year, int month) {
		return Date.from(LocalDate.of(year, month, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	@RequestMapping(value = "/admin/users/role/{roleId}", method = RequestMethod.GET)
	public @ResponseBody String getUsersWithRole(@PathVariable int roleId) {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				filter((User t) -> t.getRoles().contains(Role.values()[roleId])).
				map(DisplayableUser.fromUserOn(new Date())).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
}
