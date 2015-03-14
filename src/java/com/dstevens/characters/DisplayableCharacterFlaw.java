package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.distinctions.flaws.CharacterFlaw;
import com.google.gson.Gson;

public class DisplayableCharacterFlaw implements DisplayableSpecializedCharacterTrait {

	public final int ordinal;
	public final String specialization;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableCharacterFlaw() {
		this(-1, null);
	}
	
	public DisplayableCharacterFlaw(int ordinal, String specialization) {
		this.ordinal = ordinal;
		this.specialization = specialization;
	}

	public static Function<CharacterFlaw, DisplayableCharacterFlaw> fromFlaw() {
		return (CharacterFlaw t) -> new DisplayableCharacterFlaw(t.trait().ordinal(), t.getSpecialization());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}

	@Override
	public int ordinal() {
		return ordinal;
	}

	@Override
	public String specialization() {
		return specialization;
	}
	
}
