package com.dstevens.character.clan;

import java.util.List;
import java.util.stream.Collectors;

import com.dstevens.character.trait.power.discipline.Discipline;
import com.dstevens.character.trait.power.discipline.DisplayableDiscipline;

import static com.dstevens.collections.Lists.sort;

public class DisplayableBloodline implements Comparable<DisplayableBloodline> {

	public Integer id;
	public List<List<DisplayableDiscipline>> disciplines;

	@Deprecated
	//Jackson only
	public DisplayableBloodline() {
		this(null, null);
	}
	
	public DisplayableBloodline(Integer id, List<List<DisplayableDiscipline>> disciplines) {
		this.id = id;
		this.disciplines = disciplines;
	}
	
	public static DisplayableBloodline shortFrom(Bloodline t) {
		return new DisplayableBloodline(t.getId(), null);
	}

	public static DisplayableBloodline from(Bloodline t) {
		return new DisplayableBloodline(t.getId(), displayableDisciplinesFor(t));
	}

	private static List<List<DisplayableDiscipline>> displayableDisciplinesFor(Bloodline t) {
		return t.getDisciplines().stream().map((List<Discipline> disciplines) -> sort(disciplines.stream().map((Discipline d) -> DisplayableDiscipline.from(d)).collect(Collectors.toList()))).collect(Collectors.toList());
	}
	
	public Bloodline to() {
		return Bloodline.values()[id];
	}

	@Override
	public int compareTo(DisplayableBloodline that) {
		return this.id - that.id;
	}
	
}
