package com.dstevens.web.admin.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.users.DisplayableUser;
import com.dstevens.users.Role;
import com.dstevens.users.UnknownUserException;
import com.dstevens.users.User;
import com.dstevens.users.UserRepository;
import com.google.gson.Gson;

@Controller
public class UserController {

	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/admin/page/users", method = RequestMethod.GET)
	public ModelAndView getUsersPage() {
		return new ModelAndView("/admin/users");
	}
	
	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.PUT)
	public @ResponseBody void updateUser(@PathVariable Integer id, @RequestBody final DisplayableUser userWrapper) {
		User user = userRepository.findUser(id);
		if(user == null) {
			throw new UnknownUserException("Could not find user with id " + id);
		}
		userRepository.save(user.withEmail(userWrapper.email).withFirstName(userWrapper.firstName).withLastName(userWrapper.lastName).withRoles(userWrapper.roles()));
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
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				map(DisplayableUser.fromUserOn(new Date())).
				filter((DisplayableUser t) -> t.patronageId == null).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons", method = RequestMethod.GET)
	public @ResponseBody String getPatrons() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				map(DisplayableUser.fromUserOn(new Date())).
				filter((DisplayableUser t) -> t.patronageId != null).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons/active", method = RequestMethod.GET)
	public @ResponseBody String getActivePatrons() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				map(DisplayableUser.fromUserOn(new Date())).
				filter((DisplayableUser t) -> t.patronageId != null && t.activePatron).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons/active/{year}/{month}", method = RequestMethod.GET)
	public @ResponseBody String getActivePatronsOn(@PathVariable int year, @PathVariable int month) {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				map(DisplayableUser.fromUserOn(dateOf(year, month))).
				filter((DisplayableUser t) -> t.patronageId != null && t.activePatron).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons/expired", method = RequestMethod.GET)
	public @ResponseBody String getInactivePatrons() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				map(DisplayableUser.fromUserOn(new Date())).
				filter((DisplayableUser t) -> t.patronageId != null && !t.activePatron).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/users/patrons/expired/{year}/{month}", method = RequestMethod.GET)
	public @ResponseBody String getInactivePatronsOn(@PathVariable int year, @PathVariable int month) {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				map(DisplayableUser.fromUserOn(dateOf(year, month))).
				filter((DisplayableUser t) -> t.activePatron != null && !t.activePatron).
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
