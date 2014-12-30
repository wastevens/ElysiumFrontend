package com.dstevens.web.user.controllers;

import java.time.Clock;
import java.util.Date;
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
import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.PlayerCharacterRepository;
import com.dstevens.characters.status.PlayerStatus;
import com.dstevens.characters.status.PlayerStatusChange;
import com.dstevens.troupes.Troupe;
import com.dstevens.troupes.TroupeRepository;
import com.dstevens.users.User;
import com.dstevens.users.UserRepository;
import com.google.gson.Gson;

@Controller
public class CharacterController {

	private final Supplier<Clock> clockSupplier;
	private final Supplier<User> requestingUserSupplier;
	private final PlayerCharacterRepository characterRepository;
	private final UserRepository userRepository;
	private final TroupeRepository troupeRepository;

	@Autowired
	public CharacterController(Supplier<User> requestingUserSupplier, Supplier<Clock> clockSupplier,
							   PlayerCharacterRepository characterRepository, UserRepository userRepository, TroupeRepository troupeRepository) {
		this.requestingUserSupplier = requestingUserSupplier;
		this.clockSupplier = clockSupplier;
		this.characterRepository = characterRepository;
		this.userRepository = userRepository;
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
		
		PlayerCharacter character = characterRepository.ensureExists(rawCharacter.name, troupe.getSetting()).
				                                        changeActivityStatus(new PlayerStatusChange(PlayerStatus.SECONDARY, Date.from(clockSupplier.get().instant()))).requestApproval();
		character = characterRepository.update(character);
		user.getCharacters().add(character);
		userRepository.save(user);
		troupe.getCharacters().add(character);
		troupeRepository.save(troupe);
		
		return new ModelAndView("/user/characters");
	}
	
	private static class RawCharacter {

		public String name;
		public String troupeId;
		
	}
}
