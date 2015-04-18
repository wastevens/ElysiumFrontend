package com.dstevens.web.trait;

public interface DisplayableTraitSource {

	default DisplayableTrait toDisplayableTrait() {
		return new DisplayableTrait(display(), ordinal(), rating(), requiresSpecialization());
	}
	
	int ordinal();
	String display();
	default Integer rating() {
		return null;
	}
	
	default Boolean requiresSpecialization() {
		return null;
	}
	
}
