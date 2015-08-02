package com.dstevens.event;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacter;
import com.dstevens.troupe.DisplayableTroupe;
import com.dstevens.troupe.DisplayableVenue;

public class DisplayableEvent implements Comparable<DisplayableEvent> {

    public Integer id;
    public String name;
    public DisplayableVenue venue;
    public DisplayableTroupe troupe;
    public Date eventDate;
    public DisplayableEventStatus eventStatus;
    public Set<DisplayablePlayerCharacter> attendingCharacters;

	public static DisplayableEvent from(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getEventStatus());
		Date eventDate = t.getEventDate();
		Set<DisplayablePlayerCharacter> attendingCharacters = t.getAttendingCharacters().stream().map((PlayerCharacter pc) -> DisplayablePlayerCharacter.listable(pc)).collect(Collectors.toSet()); 
		return new DisplayableEvent(id, name, eventStatus, eventDate, attendingCharacters);
	}
    
	public static DisplayableEvent reference(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getEventStatus());
		Date eventDate = t.getEventDate();
		Set<DisplayablePlayerCharacter> attendingCharacters = null; 
		return new DisplayableEvent(id, name, eventStatus, eventDate, attendingCharacters);
	}
	
	@Deprecated
	//Jackson only
	public DisplayableEvent() {
		this(null, null, null, null, null);
	}

	public DisplayableEvent(Integer id, String name, DisplayableEventStatus eventStatus, 
			                Date eventDate, Set<DisplayablePlayerCharacter> attendingCharacters) {
		this.id = id;
		this.name = name;
		this.eventStatus = eventStatus;
		this.eventDate = eventDate;
		this.attendingCharacters = attendingCharacters;
	}

	@Override
	public int compareTo(DisplayableEvent that) {
		return this.id - that.id;
	}
}
