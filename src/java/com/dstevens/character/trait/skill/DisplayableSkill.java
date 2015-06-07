package com.dstevens.character.trait.skill;

import com.dstevens.character.trait.DisplayableDetailLevel;

public class DisplayableSkill implements Comparable<DisplayableSkill> {

	public Integer id;
	public DisplayableDetailLevel displayableDetailLevel;

	@Deprecated
	//Jackson only
	public DisplayableSkill() {
		this(null, null);
	}
	
	private DisplayableSkill(Integer id, DisplayableDetailLevel displayableDetailLevel) {
		this.id = id;
		this.displayableDetailLevel = displayableDetailLevel;
	}
	
	public static DisplayableSkill from(Skill t) {
		return new DisplayableSkill(t.getId(), DisplayableDetailLevel.from(t.detailLevel()));
	}
	
	public Skill to() {
		return Skill.values()[id];
	}

	@Override
	public int compareTo(DisplayableSkill that) {
		return this.id - that.id;
	}
	
}
