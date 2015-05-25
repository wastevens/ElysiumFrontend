package com.dstevens.character;

import java.util.Collection;
import java.util.Comparator;

import org.apache.commons.collections.ComparatorUtils;

import com.dstevens.character.trait.DetailLevel;
import com.dstevens.character.trait.DisplayableDetailLevel;
import com.dstevens.character.trait.Trait;

import static com.dstevens.collections.Lists.list;

public class DisplayablePlayerCharacterTrait implements Comparable<DisplayablePlayerCharacterTrait> {

	public Integer id;
	public Integer rating;
	public String specialization;
	public Collection<String> focuses;
	public DisplayableDetailLevel displayableDetailLevel;

	@Deprecated
	//Jackson only
	public DisplayablePlayerCharacterTrait() {
		this(null, null, null, null, null);
	}
	
	public DisplayablePlayerCharacterTrait(Trait t) {
		this(t, null, null, null, null);
	}
	
	public DisplayablePlayerCharacterTrait(Trait t, Integer rating) {
		this(t, rating, null, null, null);
	}
	
	public DisplayablePlayerCharacterTrait(Trait t, DetailLevel detailLevel, String specialization) {
		this(t, null, detailLevel, specialization, null);
	}
	
	public DisplayablePlayerCharacterTrait(Trait t, Integer rating, DetailLevel detailLevel, String specialization, Collection<String> focuses) {
		this.id = t.getId();
		this.rating = rating;
		if(detailLevel != null) {
			this.displayableDetailLevel = DisplayableDetailLevel.from(detailLevel);
		} else {
			this.displayableDetailLevel = null;
		}
		this.specialization = specialization;
		this.focuses = focuses;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(DisplayablePlayerCharacterTrait that) {
		ComparatorUtils.chainedComparator(list(Comparator.comparingInt((DisplayablePlayerCharacterTrait t) -> t.id), Comparator.comparing((DisplayablePlayerCharacterTrait t) -> t.rating), Comparator.comparing((DisplayablePlayerCharacterTrait t) -> t.specialization)));
		return this.id.compareTo(that.id);
	}
}
