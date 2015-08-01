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

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.PlayerCharacterService;
import com.dstevens.character.UnknownCharacterException;
import com.dstevens.character.trait.change.TraitChange;
import com.dstevens.character.trait.change.TraitChangeFactoryProvider;
import com.dstevens.user.User;
import com.dstevens.web.config.RequestingUserProvider;
import com.google.gson.Gson;

@Controller
public class CharacterController {

	private final PlayerCharacterService playerCharacterService;
	private final RequestingUserProvider requestingUserSupplier;
	private final TraitChangeFactoryProvider traitChangeFactoryProvider;

	@Autowired
	public CharacterController(RequestingUserProvider requestingUserSupplier, PlayerCharacterService playerCharacterService,
							   TraitChangeFactoryProvider traitChangeFactoryProvider) {
		this.requestingUserSupplier = requestingUserSupplier;
		this.playerCharacterService = playerCharacterService;
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
	
	@RequestMapping(value = "/characters/{id}", method = RequestMethod.GET)
	public @ResponseBody String getCharacter(@PathVariable Integer id) {
		PlayerCharacter character = playerCharacterService.getCharacter(id);
		if(character == null) {
			throw new UnknownCharacterException("Could not find character with id " + id);
		}
		
		return new Gson().toJson(DisplayablePlayerCharacter.from(character));
	}
	
	@RequestMapping(value = "/characters", method = RequestMethod.GET)
	public @ResponseBody String getCharacters() {
		User user = requestingUserSupplier.get();
		Set<DisplayablePlayerCharacter> characters = user.getCharacters().stream().map((PlayerCharacter pc) -> DisplayablePlayerCharacter.from(pc)).collect(Collectors.toSet());
		
		return new Gson().toJson(characters);
	}
}
