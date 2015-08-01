package com.dstevens.web.admin.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacter;
import com.dstevens.troupe.DisplayableTroupe;
import com.dstevens.troupe.Troupe;
import com.dstevens.troupe.TroupeRepository;
import com.dstevens.user.DisplayableUser;
import com.dstevens.user.User;
import com.dstevens.user.UserRepository;
import com.google.gson.Gson;

@Controller
public class TroupeController {

	private final TroupeRepository troupeRepository;
	private final UserRepository userRepository;

	@Autowired
	public TroupeController(TroupeRepository troupeRepository, UserRepository userRepository) {
		this.troupeRepository = troupeRepository;
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/troupes", method = RequestMethod.GET)
	public @ResponseBody String getTroupes() {
		List<DisplayableTroupe> collect = StreamSupport.stream(troupeRepository.findAllUndeleted().spliterator(), false).
				             map((Troupe t) -> DisplayableTroupe.listable(t)).
				             sorted().
				             collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/troupes/{id}", method = RequestMethod.GET)
	public @ResponseBody String getTroupe(@PathVariable Integer id) {
		Troupe troupe = troupeRepository.findWithId(id);
		DisplayableTroupe listable = DisplayableTroupe.listable(troupe);
		listable.storytellers = troupe.getStorytellers().stream().map((User t) -> DisplayableUser.listable(t)).collect(Collectors.toSet());
		listable.characters = troupe.getCharacters().stream().map((PlayerCharacter t) -> DisplayablePlayerCharacter.listable(t)).collect(Collectors.toSet());
		return new Gson().toJson(listable);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(value = "/troupes", method = RequestMethod.POST)
	public @ResponseBody void  addTroupe(@RequestBody final DisplayableTroupe troupe) {
		troupeRepository.ensureExists(troupe.name, troupe.venue.to());
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/troupes/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteTroupe(@PathVariable Integer id) {
		Optional<Troupe> troupeToDelete = troupeRepository.findOptionalWithId(id);
		if(troupeToDelete.isPresent()) {
			troupeToDelete.get().withoutCharacters().withoutStorytellers();
			troupeRepository.delete(troupeToDelete.get());
		}
	}
	
	@RequestMapping(value = "/troupes/{id}", method = RequestMethod.PUT)
	public @ResponseBody void updateTroupe(@PathVariable Integer id, @RequestBody final DisplayableTroupe displayedTroupe) {
		Troupe troupe = troupeRepository.findWithId(id);
		troupeRepository.save(troupe.withName(displayedTroupe.name).withVenue(displayedTroupe.venue.to()).withStorytellers(storytellersFor(displayedTroupe)));
	}

	private Set<User> storytellersFor(final DisplayableTroupe displayedTroupe) {
		return displayedTroupe.storytellers.stream().map((DisplayableUser t) -> userRepository.findUser(t.id)).collect(Collectors.toSet());
	}
}
