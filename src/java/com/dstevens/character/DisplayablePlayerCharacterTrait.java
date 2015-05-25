package com.dstevens.character;

import java.util.Collection;

public class DisplayablePlayerCharacterTrait implements Comparable<DisplayablePlayerCharacterTrait> {

	public Integer id;
	public Integer rating;
	public String specialization;
	public Collection<String> focuses;

	@Deprecated
	//Jackson only
	public DisplayablePlayerCharacterTrait() {
		this(null, null, null, null);
	}
	
	public DisplayablePlayerCharacterTrait(Integer id) {
		this(id, null, null, null);
	}
	
	public DisplayablePlayerCharacterTrait(Integer id, Integer rating) {
		this(id, rating, null, null);
	}
	
	public DisplayablePlayerCharacterTrait(Integer id, String specialization) {
		this(id, null, specialization, null);
	}
	
	public DisplayablePlayerCharacterTrait(Integer id, Integer rating, String specialization, Collection<String> focuses) {
		this.id = id;
		this.rating = rating;
		this.specialization = specialization;
		this.focuses = focuses;
	}
	
	@Override
	public int compareTo(DisplayablePlayerCharacterTrait that) {
		return this.id.compareTo(that.id);
	}
}
