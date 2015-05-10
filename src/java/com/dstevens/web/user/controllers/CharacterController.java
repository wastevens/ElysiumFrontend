package com.dstevens.web.user.controllers;

import java.util.List;
import java.util.Set;
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
import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.PlayerCharacterService;
import com.dstevens.character.UnknownCharacterException;
import com.dstevens.character.trait.change.TraitChange;
import com.dstevens.character.trait.change.TraitChangeFactoryProvider;
import com.dstevens.troupe.Troupe;
import com.dstevens.troupe.TroupeRepository;
import com.dstevens.user.User;
import com.dstevens.web.config.RequestingUserProvider;
import com.google.gson.Gson;

@Controller
public class CharacterController {

	private final PlayerCharacterService playerCharacterService;
	private final RequestingUserProvider requestingUserSupplier;
	private final TroupeRepository troupeRepository;
	private final TraitChangeFactoryProvider traitChangeFactoryProvider;

	@Autowired
	public CharacterController(RequestingUserProvider requestingUserSupplier, PlayerCharacterService playerCharacterService,
							   TroupeRepository troupeRepository, TraitChangeFactoryProvider traitChangeFactoryProvider) {
		this.requestingUserSupplier = requestingUserSupplier;
		this.playerCharacterService = playerCharacterService;
		this.troupeRepository = troupeRepository;
		this.traitChangeFactoryProvider = traitChangeFactoryProvider;
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
