package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.google.gson.Gson;

public class DisplayableTechnique implements DisplayableCharacterTrait {

	public final int ordinal;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableTechnique() {
		this(-1);
	}
	
	public DisplayableTechnique(int ordinal) {
		this.ordinal = ordinal;
	}

	public static Function<Technique, DisplayableTechnique> fromTechnique() {
		return (Technique t) -> new DisplayableTechnique(t.ordinal());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}

	@Override
	public int ordinal() {
		return ordinal;
	}
}
