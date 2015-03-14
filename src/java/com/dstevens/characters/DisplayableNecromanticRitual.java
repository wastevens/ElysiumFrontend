package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.powers.magic.necromancy.NecromanticRitual;
import com.google.gson.Gson;

public class DisplayableNecromanticRitual implements DisplayableCharacterTrait {

	public final int ordinal;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableNecromanticRitual() {
		this(-1);
	}
	
	public DisplayableNecromanticRitual(int ordinal) {
		this.ordinal = ordinal;
	}

	public static Function<NecromanticRitual, DisplayableNecromanticRitual> fromNecromanticRitual() {
		return (NecromanticRitual t) -> new DisplayableNecromanticRitual(t.ordinal());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}

	@Override
	public int ordinal() {
		return ordinal;
	}
}
