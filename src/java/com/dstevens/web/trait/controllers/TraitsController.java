package com.dstevens.web.trait.controllers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dstevens.character.clan.Bloodline;
import com.dstevens.character.clan.Clan;
import com.dstevens.character.clan.DisplayableBloodline;
import com.dstevens.character.clan.DisplayableClan;
import com.dstevens.character.trait.power.discipline.Discipline;
import com.dstevens.character.trait.power.discipline.DisplayableDiscipline;
import com.dstevens.character.trait.power.discipline.DisplayableElderPower;
import com.dstevens.character.trait.power.discipline.DisplayableTechnique;
import com.dstevens.character.trait.power.discipline.ElderPower;
import com.dstevens.character.trait.power.discipline.Technique;
import com.dstevens.character.trait.power.magic.necromancy.DisplayableNecromanticRitual;
import com.dstevens.character.trait.power.magic.necromancy.NecromanticRitual;
import com.dstevens.character.trait.power.magic.thaumaturgy.DisplayableThaumaturgicalRitual;
import com.dstevens.character.trait.power.magic.thaumaturgy.ThaumaturgicalRitual;
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
		    case "clans":                 return new Gson().toJson(list(Clan.values()).stream().map((Clan t1) -> DisplayableClan.from(t1)).collect(Collectors.toList()));
		    case "bloodlines":            return new Gson().toJson(list(Bloodline.values()).stream().map((Bloodline t1) -> DisplayableBloodline.from(t1)).collect(Collectors.toList()));
			case "thaumaturgicalrituals": return new Gson().toJson(list(ThaumaturgicalRitual.values()).stream().map((ThaumaturgicalRitual t1) -> DisplayableThaumaturgicalRitual.from(t1)).collect(Collectors.toList()));
			case "necromanticrituals":    return new Gson().toJson(list(NecromanticRitual.values()).stream().map((NecromanticRitual t1) -> DisplayableNecromanticRitual.from(t1)).collect(Collectors.toList()));
			case "disciplines":           return new Gson().toJson(list(Discipline.values()).stream().map((Discipline t1) -> DisplayableDiscipline.from(t1)).collect(Collectors.toList()));
			case "elderpowers":           return new Gson().toJson(list(ElderPower.values()).stream().map((ElderPower t1) -> DisplayableElderPower.from(t1)).collect(Collectors.toList()));
			case "techniques":            return new Gson().toJson(list(Technique.values()).stream().map((Technique t1) -> DisplayableTechnique.from(t1)).collect(Collectors.toList()));
		}
		return null;
	}
}
