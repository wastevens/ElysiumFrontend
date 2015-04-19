package com.dstevens.web.trait;

public class DisplayableTrait {

	public String name;
	public int ordinal;
	public Integer rating;
	public Boolean requiresSpecialization;
	public String category;
	
	public DisplayableTrait(String name, int ordinal, Integer rating, Boolean requiresSpecialization, String category) {
		this.name = name;
		this.ordinal = ordinal;
		this.rating = rating;
		this.requiresSpecialization = requiresSpecialization;
		this.category = category;
	}
	
}
