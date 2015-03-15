package com.dstevens.web.traits.controllers;

import java.util.List;

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
		switch(genre) {
			case "vampire":
				return vampireTraits(type);
		}
		return null;
	}

	private String vampireTraits(String type) {
		switch(type) {
			case "thaumaturgicalRituals":
				return thaumaturgicalRituals();
		}
		return null;
	}

	private static class DisplayableTrait {
		public String name;
		public int ordinal;
		public int rating;
		
		public DisplayableTrait(String name, int ordinal, int rating) {
			this.name = name;
			this.ordinal = ordinal;
			this.rating = rating;
		}
	}
	
	private String thaumaturgicalRituals() {
		int i = -1;
		List<DisplayableTrait> thaumaturgicalRituals = list(
				new DisplayableTrait("Blood Mastery",                       ++i, 1),
				new DisplayableTrait("Bind the Accusing Tongue",            ++i, 1),
				new DisplayableTrait("Communicate with Kindred Sire",       ++i, 1),
				new DisplayableTrait("Defense of the Sacred Haven",         ++i, 1),
				new DisplayableTrait("Engaging the Vessel of Transference", ++i, 1),
				new DisplayableTrait("Illuminate the Trail of Prey",        ++i, 1),
				new DisplayableTrait("Principal Focus of Vitae Infusion",   ++i, 1),
				new DisplayableTrait("Warding Circle",                      ++i, 1),
				new DisplayableTrait("Banish Big Brother",                  ++i, 2),
				new DisplayableTrait("Burning Blade",                       ++i, 2),
				new DisplayableTrait("Craft Bloodstone",                    ++i, 2),
				new DisplayableTrait("Eyes of the Nighthawk",               ++i, 2),
				new DisplayableTrait("Illusion of Peaceful Death",          ++i, 2),
				new DisplayableTrait("Machine Blitz",                       ++i, 2),
				new DisplayableTrait("Recure of the Homeland",              ++i, 2),
				new DisplayableTrait("Detect the Hidden Observer",          ++i, 3),
				new DisplayableTrait("Flesh of Fiery Touch",                ++i, 3),
				new DisplayableTrait("Incorporeal Passage",                 ++i, 3),
				new DisplayableTrait("Mirror of Second Sight",              ++i, 3),
				new DisplayableTrait("Pavis of Foul Presence",              ++i, 3),
				new DisplayableTrait("Soul of the Homunculi",               ++i, 3),
				new DisplayableTrait("Shaft of Belated Quiescence",         ++i, 3),
				new DisplayableTrait("Innocence of the Child's Heart",      ++i, 4),
				new DisplayableTrait("Mirror Walk",                         ++i, 4),
				new DisplayableTrait("Severed Hand",                        ++i, 4),
				new DisplayableTrait("Scry",                                ++i, 4),
				new DisplayableTrait("Blood Contract",                      ++i, 5),
				new DisplayableTrait("Cobra's Favor",                       ++i, 5),
				new DisplayableTrait("Paper Flesh",                         ++i, 5),
				new DisplayableTrait("Stone of the True Form",              ++i, 5) 
				);
		
		return new Gson().toJson(thaumaturgicalRituals);
	}
	
}
