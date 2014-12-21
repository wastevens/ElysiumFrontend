package com.dstevens.web.admin.controllers;

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
	
	@RequestMapping(value = "/admin/users/{id}/roles", method = RequestMethod.POST)
	public void addRoleToUser(@PathVariable String id, @RequestBody final RawRole roleWrapper) {
		User user = userRepository.findUser(id);
		if(user == null) {
			throw new UnknownUserException("Could not find user with id " + id);
		}
		userRepository.save(user.withRole(roleWrapper.role));
	}
	
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public @ResponseBody String getUsers() {
		List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
				                                      map(DisplayableUser.fromUser()).
				                                      sorted().
				                                      collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	private static class RawRole {

		public Role role;
		
	}
}
