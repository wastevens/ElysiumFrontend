package com.dstevens.web.user.controllers;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacterService;
import com.dstevens.character.UnknownCharacterException;
import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactoryProvider;
import com.dstevens.troupes.Troupe;
import com.dstevens.troupes.TroupeRepository;
import com.dstevens.users.User;
import com.google.gson.Gson;

@Controller
public class CharacterController {

	private final PlayerCharacterService playerCharacterService;
	private final Supplier<User> requestingUserSupplier;
	private final TroupeRepository troupeRepository;
	private final TraitChangeFactoryProvider traitChangeFactoryProvider;

	@Autowired
	public CharacterController(Supplier<User> requestingUserSupplier, PlayerCharacterService playerCharacterService,
							   TroupeRepository troupeRepository, TraitChangeFactoryProvider traitChangeFactoryProvider) {
		this.requestingUserSupplier = requestingUserSupplier;
		this.playerCharacterService = playerCharacterService;
		this.troupeRepository = troupeRepository;
		this.traitChangeFactoryProvider = traitChangeFactoryProvider;
	}
	
	@RequestMapping(value = "/user/page/characters", method = RequestMethod.GET)
	public ModelAndView getCharactersPage() {
		return new ModelAndView("/user/characters");
	}
	
	@RequestMapping(value = "/user/page/characters/{id}", method = RequestMethod.GET)
	public ModelAndView getCharacterPage(@PathVariable Integer id) {
		PlayerCharacter character = playerCharacterService.getCharacter(id);
		if(character == null) {
			throw new UnknownCharacterException("Could not find character with id " + id);
		}
		//Push this to PlayerCharacter; get the character with all current requests applied
		character.getRequestedTraitChanges().forEach((TraitChange t) -> character.apply(t));
		ModelAndView modelAndView = new ModelAndView("/user/character/manage");
		modelAndView.addObject("character", DisplayablePlayerCharacter.fromCharacter().apply(character).serialized());
		return modelAndView;
	}
	
	@RequestMapping(value = "/characters/{id}", method = RequestMethod.POST)
	public @ResponseBody void addRequests(@PathVariable Integer id, @RequestBody final List<RawTraitChange> requests) {
		PlayerCharacter character = playerCharacterService.getCharacter(id);
		if(character == null) {
			throw new UnknownCharacterException("Could not find character with id " + id);
		}
		//Introduce #request that takes a list.  No reason to do this single file.
		List<TraitChange> traitChanges = requests.stream().map(RawTraitChange.toTraitChangeUsing(traitChangeFactoryProvider.giveTraits())).collect(Collectors.toList());
		for (TraitChange traitChange : traitChanges) {
			character = character.request(traitChange);
		}
		playerCharacterService.save(character);
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
		public Integer troupeId;
		
	}
}
