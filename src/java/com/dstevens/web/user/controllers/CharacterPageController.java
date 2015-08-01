package com.dstevens.web.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.PlayerCharacterService;
import com.dstevens.character.Setting;
import com.dstevens.character.UnknownCharacterException;
import com.dstevens.character.trait.change.TraitChange;
import com.dstevens.troupe.Troupe;
import com.dstevens.troupe.TroupeRepository;
import com.dstevens.user.User;
import com.dstevens.web.config.RequestingUserProvider;
import com.google.gson.Gson;

@Controller
public class CharacterPageController {

	private final PlayerCharacterService playerCharacterService;
	private final RequestingUserProvider requestingUserSupplier;
	private final TroupeRepository troupeRepository;

	@Autowired
	public CharacterPageController(RequestingUserProvider requestingUserSupplier, PlayerCharacterService playerCharacterService,
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
	
	@RequestMapping(value = "/characters", method = RequestMethod.POST)
	public ModelAndView createCharacter(@RequestBody final RawCharacter rawCharacter) {
		User user = requestingUserSupplier.get();
		Troupe troupe = troupeRepository.findWithId(rawCharacter.troupeId);
		Setting setting = Setting.values()[rawCharacter.settingId];
		PlayerCharacter newCharacter = playerCharacterService.createCharacter(user, troupe, setting, rawCharacter.name);
		
		return getCharacterPage(newCharacter.getId());
	}
	
	private static class RawCharacter {

		public String name;
		public Integer troupeId;
		public Integer settingId;
		
	}
	
	@RequestMapping(value = "/user/page/characters/{id}", method = RequestMethod.GET)
	public ModelAndView getCharacterPage(@PathVariable Integer id) {
		PlayerCharacter character = playerCharacterService.getCharacter(id);
		if(character == null) {
			throw new UnknownCharacterException("Could not find character with id " + id);
		}
		character.getRequestedTraitChanges().forEach((TraitChange t) -> character.apply(t));
		ModelAndView modelAndView = new ModelAndView("/user/character/manage");
		modelAndView.addObject("character", new Gson().toJson(DisplayablePlayerCharacter.from(character)));
		return modelAndView;
	}
	
}
