package com.dstevens.characters;

import java.util.Comparator;

public class DisplayableSpecializedCharacterTraitComparator implements Comparator<DisplayableSpecializedCharacterTrait>{

	@Override
	public int compare(DisplayableSpecializedCharacterTrait o1, DisplayableSpecializedCharacterTrait o2) {
		if(o1.ordinal() != o2.ordinal()) {
			return o1.ordinal() - o2.ordinal();
		}
		return o1.specialization().compareTo(o2.specialization());
	}

}
