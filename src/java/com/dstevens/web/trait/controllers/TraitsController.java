package com.dstevens.web.trait.controllers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dstevens.character.DisplayableSetting;
import com.dstevens.character.Setting;
import com.dstevens.character.clan.Bloodline;
import com.dstevens.character.clan.Clan;
import com.dstevens.character.clan.DisplayableBloodline;
import com.dstevens.character.clan.DisplayableClan;
import com.dstevens.character.trait.attribute.focus.DisplayableMentalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.DisplayablePhysicalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.DisplayableSocialAttributeFocus;
import com.dstevens.character.trait.attribute.focus.MentalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.PhysicalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.SocialAttributeFocus;
import com.dstevens.character.trait.background.Background;
import com.dstevens.character.trait.background.DisplayableBackground;
import com.dstevens.character.trait.distinction.merit.DisplayableMerit;
import com.dstevens.character.trait.distinction.merit.Merit;
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
import com.dstevens.character.trait.skill.DisplayableSkill;
import com.dstevens.character.trait.skill.Skill;
import com.dstevens.event.DisplayableEventStatus;
import com.dstevens.event.EventStatus;
import com.dstevens.troupe.DisplayableVenue;
import com.dstevens.troupe.Venue;
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
		    case "eventstatus":              return new Gson().toJson(list(EventStatus.values()).stream().map((EventStatus t1) -> DisplayableEventStatus.from(t1)).collect(Collectors.toList()));
		    case "venues":                   return new Gson().toJson(list(Venue.values()).stream().map((Venue t1) -> DisplayableVenue.from(t1)).collect(Collectors.toList()));
		    case "settings":                 return new Gson().toJson(list(Setting.values()).stream().map((Setting t1) -> DisplayableSetting.from(t1)).collect(Collectors.toList()));
		    case "clans":                    return new Gson().toJson(list(Clan.values()).stream().map((Clan t1) -> DisplayableClan.from(t1)).collect(Collectors.toList()));
		    case "bloodlines":               return new Gson().toJson(list(Bloodline.values()).stream().map((Bloodline t1) -> DisplayableBloodline.from(t1)).collect(Collectors.toList()));
			case "thaumaturgicalrituals":    return new Gson().toJson(list(ThaumaturgicalRitual.values()).stream().map((ThaumaturgicalRitual t1) -> DisplayableThaumaturgicalRitual.from(t1)).collect(Collectors.toList()));
			case "necromanticrituals":       return new Gson().toJson(list(NecromanticRitual.values()).stream().map((NecromanticRitual t1) -> DisplayableNecromanticRitual.from(t1)).collect(Collectors.toList()));
			case "disciplines":              return new Gson().toJson(list(Discipline.values()).stream().map((Discipline t1) -> DisplayableDiscipline.from(t1)).collect(Collectors.toList()));
			case "elderpowers":              return new Gson().toJson(list(ElderPower.values()).stream().map((ElderPower t1) -> DisplayableElderPower.from(t1)).collect(Collectors.toList()));
			case "techniques":               return new Gson().toJson(list(Technique.values()).stream().map((Technique t1) -> DisplayableTechnique.from(t1)).collect(Collectors.toList()));
			case "backgrounds":              return new Gson().toJson(list(Background.values()).stream().map((Background t1) -> DisplayableBackground.from(t1)).collect(Collectors.toList()));
			case "skills":                   return new Gson().toJson(list(Skill.values()).stream().map((Skill t1) -> DisplayableSkill.from(t1)).collect(Collectors.toList()));
			case "physicalattributefocuses": return new Gson().toJson(list(PhysicalAttributeFocus.values()).stream().map((PhysicalAttributeFocus t1) -> DisplayablePhysicalAttributeFocus.from(t1)).collect(Collectors.toList()));
			case "socialattributefocuses":   return new Gson().toJson(list(SocialAttributeFocus.values()).stream().map((SocialAttributeFocus t1) -> DisplayableSocialAttributeFocus.from(t1)).collect(Collectors.toList()));
			case "mentalattributefocuses":   return new Gson().toJson(list(MentalAttributeFocus.values()).stream().map((MentalAttributeFocus t1) -> DisplayableMentalAttributeFocus.from(t1)).collect(Collectors.toList()));
			case "merits":                   return new Gson().toJson(list(Merit.values()).stream().map((Merit t1) -> DisplayableMerit.from(t1)).collect(Collectors.toList()));
			case "flaws":                    return new Gson().toJson(list(Merit.values()).stream().map((Merit t1) -> DisplayableMerit.from(t1)).collect(Collectors.toList()));
		}
		return null;
	}
}
