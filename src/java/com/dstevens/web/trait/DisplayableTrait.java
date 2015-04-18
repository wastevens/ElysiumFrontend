package com.dstevens.web.trait;

public class DisplayableTrait {

	public String name;
	public int ordinal;
	public Integer rating;
	public Boolean requiresSpecialization;
	
	public DisplayableTrait(String name, int ordinal, Integer rating, Boolean requiresSpecialization) {
		this.name = name;
		this.ordinal = ordinal;
		this.rating = rating;
		this.requiresSpecialization = requiresSpecialization;
	}
	
}
