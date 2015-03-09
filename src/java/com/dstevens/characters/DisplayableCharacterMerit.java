package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.distinctions.merits.CharacterMerit;
import com.google.gson.Gson;

public class DisplayableCharacterMerit {

	public final int ordinal;
	public final String specialization;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableCharacterMerit() {
		this(-1, null);
	}
	
	public DisplayableCharacterMerit(int ordinal, String specialization) {
		this.ordinal = ordinal;
		this.specialization = specialization;
	}

	public static Function<CharacterMerit, DisplayableCharacterMerit> fromMerit() {
		return (CharacterMerit t) -> new DisplayableCharacterMerit(t.trait().ordinal(), t.getSpecialization());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
}
