package com.dstevens.event;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacter;
import com.dstevens.troupe.DisplayableTroupe;
import com.dstevens.troupe.DisplayableVenue;
import com.dstevens.troupe.Troupe;

public class DisplayableEvent implements Comparable<DisplayableEvent> {

    public Integer id;
    public String name;
    public DisplayableVenue venue;
    public DisplayableEventStatus eventStatus;
    public List<DisplayableEventStatusChange> eventStatusChanges;
    public DisplayableTroupe troupe;
    public Set<DisplayablePlayerCharacter> attendingCharacters;
	

	public static DisplayableEvent from(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableVenue venue = DisplayableVenue.listable(t.getVenue());
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getCurrentStatus());
		List<DisplayableEventStatusChange> eventStatusChanges = t.getEventStatusChanges().stream().map((EventStatusChange foo) -> DisplayableEventStatusChange.from(foo)).sorted().collect(Collectors.toList());
		DisplayableTroupe troupe = Optional.ofNullable(t.getTroupe()).map((Troupe c) -> DisplayableTroupe.idAndName(c)).orElse(null);
		Set<DisplayablePlayerCharacter> attendingCharacters = t.getAttendingCharacters().stream().map((PlayerCharacter pc) -> DisplayablePlayerCharacter.listable(pc)).collect(Collectors.toSet()); 
		return new DisplayableEvent(id, name, venue, eventStatus, eventStatusChanges, troupe, attendingCharacters);
	}
    
	public static DisplayableEvent listable(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableVenue venue = DisplayableVenue.listable(t.getVenue());
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getCurrentStatus());
		List<DisplayableEventStatusChange> eventStatusChanges = null;
		DisplayableTroupe troupe = Optional.ofNullable(t.getTroupe()).map((Troupe c) -> DisplayableTroupe.idAndName(c)).orElse(null);
		Set<DisplayablePlayerCharacter> attendingCharacters = null; 
		return new DisplayableEvent(id, name, venue, eventStatus, eventStatusChanges, troupe, attendingCharacters);
	}
	
	@Deprecated
	//Jackson only
	public DisplayableEvent() {
		this(null, null, null, null, null, null, null);
	}

	public DisplayableEvent(Integer id, String name, DisplayableVenue venue, DisplayableEventStatus eventStatus, List<DisplayableEventStatusChange> eventStatusChanges, 
			                DisplayableTroupe troupe, Set<DisplayablePlayerCharacter> attendingCharacters) {
		this.id = id;
		this.name = name;
		this.venue = venue;
		this.eventStatus = eventStatus;
		this.eventStatusChanges = eventStatusChanges;
		this.troupe = troupe;
		this.attendingCharacters = attendingCharacters;
	}

	@Override
	public int compareTo(DisplayableEvent that) {
		return this.id - that.id;
	}
}
