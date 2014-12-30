package com.dstevens.web.user.controllers;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.characters.DisplayablePlayerCharacter;
import com.dstevens.characters.PlayerCharacterService;
import com.dstevens.troupes.Troupe;
import com.dstevens.troupes.TroupeRepository;
import com.dstevens.users.User;
import com.google.gson.Gson;

@Controller
public class CharacterController {

	private final PlayerCharacterService playerCharacterService;
	private final Supplier<User> requestingUserSupplier;
	private final TroupeRepository troupeRepository;

	@Autowired
	public CharacterController(Supplier<User> requestingUserSupplier, PlayerCharacterService playerCharacterService,
							   TroupeRepository troupeRepository) {
		this.requestingUserSupplier = requestingUserSupplier;
		this.playerCharacterService = playerCharacterService;
		this.troupeRepository = troupeRepository;
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
	
	@RequestMapping(value = "/characters", method = RequestMethod.POST)
	public ModelAndView createCharacter(@RequestBody final RawCharacter rawCharacter) {
		User user = requestingUserSupplier.get();
		Troupe troupe = troupeRepository.findWithId(rawCharacter.troupeId);
		playerCharacterService.createCharacter(user, troupe, rawCharacter.name);
		
		return new ModelAndView("/user/characters");
	}
	
	private static class RawCharacter {

		public String name;
		public String troupeId;
		
	}
}
