package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.powers.disciplines.CharacterDiscipline;
import com.google.gson.Gson;

public class DisplayableCharacterDiscipline implements DisplayableCharacterTrait {

	public final int ordinal;
	public final int rating;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableCharacterDiscipline() {
		this(-1, -1);
	}
	
	public DisplayableCharacterDiscipline(int ordinal, int rating) {
		this.ordinal = ordinal;
		this.rating = rating;
	}

	public static Function<CharacterDiscipline, DisplayableCharacterDiscipline> fromDiscipline() {
		return (CharacterDiscipline t) -> new DisplayableCharacterDiscipline(t.trait().ordinal(), t.rating());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
	@Override
	public int ordinal() {
		return ordinal;
	}
}
