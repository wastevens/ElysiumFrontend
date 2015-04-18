package com.dstevens.characters;

import java.util.Collection;
import java.util.Comparator;

import org.apache.commons.lang3.ObjectUtils;

import com.google.gson.Gson;

public class DisplayableTrait implements Comparable<DisplayableTrait> {

	private final int ordinal;
	private final Integer rating;
	private final String specialization;
	private final Collection<String> focuses;

	public DisplayableTrait(int ordinal) {
		this(ordinal, null, null, null);
	}
	
	public DisplayableTrait(int ordinal, Integer rating) {
		this(ordinal, rating, null, null);
	}
	
	public DisplayableTrait(int ordinal, String specialization) {
		this(ordinal, null, specialization, null);
	}
	
	public DisplayableTrait(int ordinal, Integer rating, String specialization, Collection<String> focuses) {
		this.ordinal = ordinal;
		this.rating = rating;
		this.specialization = specialization;
		this.focuses = focuses;
	}
	
	public int ordinal() {
		return ordinal;
	}
	
	public Integer rating() {
		return rating;
	}
	
	public String getSpecialization() {
		return specialization;
	}
	
	public Collection<String> getFocuses() {
		return focuses;
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}

	@Override
	public int compareTo(DisplayableTrait that) {
		return DisplayTraitComparator.ORDINAL.thenComparing(DisplayTraitComparator.RATING).thenComparing(DisplayTraitComparator.SPECIALIZATOIN).compare(this, that);
	}
	
	private static enum DisplayTraitComparator implements Comparator<DisplayableTrait> {
		
		ORDINAL {
			@Override
			public int compare(DisplayableTrait o1, DisplayableTrait o2) {
				return ObjectUtils.compare(o1.ordinal, o2.ordinal, false);
			}
		},
		RATING {
			@Override
			public int compare(DisplayableTrait o1, DisplayableTrait o2) {
				return ObjectUtils.compare(o1.rating, o2.rating, false);
			}
		},
		SPECIALIZATOIN {
			@Override
			public int compare(DisplayableTrait o1, DisplayableTrait o2) {
				return ObjectUtils.compare(o1.specialization, o2.specialization, false);
			}
		}
		;
	}
}
