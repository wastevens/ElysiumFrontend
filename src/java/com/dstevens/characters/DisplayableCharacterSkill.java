package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.skills.CharacterSkill;
import com.google.gson.Gson;

public class DisplayableCharacterSkill {

	public final int ordinal;
	public final int rating;
	public final String specialization;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableCharacterSkill() {
		this(-1, -1, null);
	}
	
	public DisplayableCharacterSkill(int ordinal, int rating, String specialization) {
		this.ordinal = ordinal;
		this.rating = rating;
		this.specialization = specialization;
	}

	public static Function<CharacterSkill, DisplayableCharacterSkill> fromSkill() {
		return (CharacterSkill t) -> new DisplayableCharacterSkill(t.trait().ordinal(), t.rating(), t.getSpecialization());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
}
