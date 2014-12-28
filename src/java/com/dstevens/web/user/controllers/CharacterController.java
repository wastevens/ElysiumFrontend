package com.dstevens.web.user.controllers;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.characters.DisplayablePlayerCharacter;
import com.dstevens.users.User;
import com.google.gson.Gson;

@Controller
public class CharacterController {

	private final Supplier<User> requestingUserSupplier;

	@Autowired
	public CharacterController(Supplier<User> requestingUserSupplier) {
		this.requestingUserSupplier = requestingUserSupplier;
	}
	
	@RequestMapping(value = "/user/page/characters", method = RequestMethod.GET)
	public ModelAndView getCharactersPage() {
		return new ModelAndView("/user/characters");
	}
	
	@RequestMapping(value = "/user/page/character/create", method = RequestMethod.GET)
	public ModelAndView getCharacterCreationPage() {
		return new ModelAndView("/user/character_creation");
	}
	
	@RequestMapping(value = "/characters", method = RequestMethod.GET)
	public @ResponseBody String getCharacters() {
		User user = requestingUserSupplier.get();
		Set<DisplayablePlayerCharacter> characters = user.getCharacters().stream().map(DisplayablePlayerCharacter.fromCharacter()).collect(Collectors.toSet());
		
		return new Gson().toJson(characters);
	}
	
}
