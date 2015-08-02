package com.dstevens.troupe;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacter;
import com.dstevens.user.DisplayableUser;
import com.dstevens.user.User;
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
	
	public static DisplayableTroupe listable(Troupe t) {
		return new DisplayableTroupe(t.getId(), t.getName(), DisplayableVenue.listable(t.getVenue()), null, null);
	}

	public static DisplayableTroupe idAndName(Troupe t) {
		return new DisplayableTroupe(t.getId(), t.getName(), null, null, null);
	}
	
	public static DisplayableTroupe from(Troupe t) {
		return new DisplayableTroupe(t.getId(), t.getName(), DisplayableVenue.from(t.getVenue()), 
				                     t.getStorytellers().stream().map((User u) -> DisplayableUser.fromOn(u, new Date())).collect(Collectors.toSet()), 
				                     t.getCharacters().stream().map((PlayerCharacter pc) -> DisplayablePlayerCharacter.from(pc)).collect(Collectors.toSet()));
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
