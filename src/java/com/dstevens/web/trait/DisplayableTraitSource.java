package com.dstevens.web.trait;

public interface DisplayableTraitSource {

	default DisplayableTrait toDisplayableTrait() {
		return new DisplayableTrait(display(), ordinal(), rating(), requiresSpecialization(), subcategory(), displayableTrait());
	}

	int ordinal();
	String display();
	
	default Integer rating() {
		return null;
	}
	
	default Boolean requiresSpecialization() {
		return null;
	}

	default String type() {
		return null;
	}
	
	default String subcategory() {
		return null;
	}
	
	default DisplayableTrait displayableTrait() {
		return null;
	}
}
