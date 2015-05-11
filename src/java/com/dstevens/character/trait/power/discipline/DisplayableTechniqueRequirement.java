package com.dstevens.character.trait.power.discipline;

public class DisplayableTechniqueRequirement {

	public DisplayableDiscipline discipline;
	public Integer rating;

	@Deprecated
	//Jackson only
	public DisplayableTechniqueRequirement() {
		this(null, null);
	}
	
	public DisplayableTechniqueRequirement(DisplayableDiscipline discipline, Integer rating) {
		this.discipline = discipline;
		this.rating = rating;
	}

	public static DisplayableTechniqueRequirement from(TechniqueRequirement t) {
		return new DisplayableTechniqueRequirement(DisplayableDiscipline.from(t.getPower()), t.getRating());
	}
	
	public TechniqueRequirement to() {
		return TechniqueRequirement.required(discipline.to(), rating);
	}
	
}
