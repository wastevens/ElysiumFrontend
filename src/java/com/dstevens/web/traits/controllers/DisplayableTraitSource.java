package com.dstevens.web.traits.controllers;

public interface DisplayableTraitSource {

	default DisplayableTrait toDisplayableTrait() {
		return new DisplayableTrait(display(), ordinal(), rating());
	}
	
	int rating();
	int ordinal();
	String display();
	
}
