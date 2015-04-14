package com.dstevens.web.traits.controllers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import static com.dstevens.collections.Lists.list;

@Controller
public class TraitsController {

	@RequestMapping(value = "/traits/{genre}/{type}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getTraits(@PathVariable String genre, @PathVariable String type) {
		switch(genre.toLowerCase()) {
			case "vampire":
				return vampireTraits(type);
		}
		return null;
	}

	private String vampireTraits(String type) {
		switch(type.toLowerCase()) {
			case "thaumaturgicalrituals":
				return thaumaturgicalRituals();
		}
		return null;
	}
	
	private String thaumaturgicalRituals() {
		return new Gson().toJson(list(DisplayableThaumaturgicalRituals.values()).stream().map((DisplayableTraitSource t) -> t.toDisplayableTrait()).collect(Collectors.toList()));
	}
	
}
