package com.dstevens.web.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.PlayerCharacterService;
import com.dstevens.character.UnknownCharacterException;
import com.dstevens.character.trait.change.TraitChange;

@Controller
public class CharacterPageController {

	private final PlayerCharacterService playerCharacterService;

	@Autowired
	public CharacterPageController(PlayerCharacterService playerCharacterService) {
		this.playerCharacterService = playerCharacterService;
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
	
}
