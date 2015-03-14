package com.dstevens.characters;

import java.util.Comparator;

public class DisplayableCharacterTraitComparator implements Comparator<DisplayableCharacterTrait>{

	@Override
	public int compare(DisplayableCharacterTrait o1, DisplayableCharacterTrait o2) {
		return o1.ordinal() - o2.ordinal();
	}

}
