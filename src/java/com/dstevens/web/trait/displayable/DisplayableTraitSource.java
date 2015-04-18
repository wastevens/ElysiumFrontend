package com.dstevens.web.trait.displayable;

public interface DisplayableTraitSource {

	default DisplayableTrait toDisplayableTrait() {
		return new DisplayableTrait(display(), ordinal(), rating());
	}
	
	int rating();
	int ordinal();
	String display();
	
}
