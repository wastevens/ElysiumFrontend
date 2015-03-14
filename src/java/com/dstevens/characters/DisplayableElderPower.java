package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.google.gson.Gson;

public class DisplayableElderPower implements DisplayableCharacterTrait {

	public final int ordinal;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableElderPower() {
		this(-1);
	}
	
	public DisplayableElderPower(int ordinal) {
		this.ordinal = ordinal;
	}

	public static Function<ElderPower, DisplayableElderPower> fromElderPower() {
		return (ElderPower t) -> new DisplayableElderPower(t.ordinal());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}

	@Override
	public int ordinal() {
		return ordinal;
	}
}
