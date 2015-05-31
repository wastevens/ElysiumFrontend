package com.dstevens.troupe;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.user.DisplayableUser;
import com.dstevens.utilities.ObjectExtensions;

import static com.dstevens.collections.Sets.set;

public class DisplayableTroupe implements Comparable<DisplayableTroupe> {

	public Integer id;
	public String name;
	public DisplayableVenue venue;
	public Set<DisplayableUser> storytellers;
	public Set<DisplayablePlayerCharacter> characters;
	
	//Jackson only
    @Deprecated
	private DisplayableTroupe() {
		this(null, null, null, set(), set());
	}
	
	private DisplayableTroupe(Integer id, String name, DisplayableVenue venue, Set<DisplayableUser> storytellers, Set<DisplayablePlayerCharacter> characters) {
		this.id = id;
		this.name = name;
		this.venue = venue;
		this.storytellers = storytellers;
		this.characters = characters;
	}
	
	public static Function<Troupe, DisplayableTroupe> fromTroupes() {
		return (Troupe t) -> new DisplayableTroupe(t.getId(), t.getName(), DisplayableVenue.from(t.getVenue()),
				                                   t.getStorytellers().stream().map(DisplayableUser.fromUserOn(new Date())).collect(Collectors.toSet()),
				                                   t.getCharacters().stream().map(DisplayablePlayerCharacter.fromCharacter()).collect(Collectors.toSet()));
	}
	
	@Override
	public int compareTo(DisplayableTroupe that) {
		return Comparator.comparing((DisplayableTroupe t) -> t.name).
				          thenComparing((DisplayableTroupe t) -> t.id).
			              compare(this, that);
	}
	
	@Override
	public String toString() {
		return ObjectExtensions.toStringFor(this);
	}
}
