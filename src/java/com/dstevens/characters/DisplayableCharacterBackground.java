package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.google.gson.Gson;

public class DisplayableCharacterBackground {

	public final int ordinal;
	public final int rating;
	public final String specialization;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableCharacterBackground() {
		this(-1, -1, null);
	}
	
	public DisplayableCharacterBackground(int ordinal, int rating, String specialization) {
		this.ordinal = ordinal;
		this.rating = rating;
		this.specialization = specialization;
	}

	public static Function<CharacterBackground, DisplayableCharacterBackground> fromBackground() {
		return (CharacterBackground t) -> new DisplayableCharacterBackground(t.trait().ordinal(), t.rating(), t.getSpecialization());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
}
