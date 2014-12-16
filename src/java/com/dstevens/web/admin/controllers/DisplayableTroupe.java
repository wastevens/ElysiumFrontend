package com.dstevens.web.admin.controllers;

import java.util.Comparator;
import java.util.function.Function;

import com.dstevens.players.Troupe;
import com.dstevens.utilities.ObjectExtensions;

public class DisplayableTroupe implements Comparable<DisplayableTroupe> {

	public String id;
	public String name;
	public int setting;
	public int numberOfPlayers;

	private DisplayableTroupe(String id, String name, int setting, int numberOfPlayers) {
		this.id = id;
		this.name = name;
		this.setting = setting;
		this.numberOfPlayers = numberOfPlayers;
	}
	
	public static Function<Troupe, DisplayableTroupe> fromTroupes() {
		return (Troupe t) -> new DisplayableTroupe(t.getId(), t.getName(), t.getSetting().ordinal(), t.getPlayers().size());
	}
	
	@Override
	public int compareTo(DisplayableTroupe that) {
		return Comparator.comparing((DisplayableTroupe t) -> t.name).
			              thenComparing((DisplayableTroupe t) -> t.setting).
			              thenComparing((DisplayableTroupe t) -> t.numberOfPlayers).
			              thenComparing((DisplayableTroupe t) -> t.id).
			              compare(this, that);
	}
	
	@Override
	public String toString() {
		return ObjectExtensions.toStringFor(this);
	}
}
