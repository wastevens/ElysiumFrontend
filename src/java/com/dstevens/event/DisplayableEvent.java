package com.dstevens.event;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.PlayerCharacterRepository;
import com.dstevens.troupe.DisplayableTroupe;
import com.dstevens.troupe.DisplayableVenue;
import com.dstevens.troupe.TroupeRepository;

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
		DisplayableVenue venue = (t instanceof VenueEvent ? DisplayableVenue.listable(((VenueEvent)t).getVenue()) : null);
		DisplayableTroupe troupe = (t instanceof TroupeEvent ? DisplayableTroupe.listable(((TroupeEvent)t).getTroupe()) : null);
		return new DisplayableEvent(id, name, eventStatus, eventDate, attendingCharacters, venue, troupe);
	}
    
	public static DisplayableEvent reference(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getEventStatus());
		Date eventDate = t.getEventDate();
		Set<DisplayablePlayerCharacter> attendingCharacters = null; 
		DisplayableVenue venue = (t instanceof VenueEvent ? DisplayableVenue.listable(((VenueEvent)t).getVenue()) : null);
		DisplayableTroupe troupe = (t instanceof TroupeEvent ? DisplayableTroupe.listable(((TroupeEvent)t).getTroupe()) : null);
		return new DisplayableEvent(id, name, eventStatus, eventDate, attendingCharacters, venue, troupe);
	}
	
	public Event to(TroupeRepository troupeRepository, PlayerCharacterRepository characterRepository) {
		if(troupe != null) {
			return toTroupeEvent(troupeRepository, characterRepository);
		}
		if(venue != null) {
			return toVenueEvent(characterRepository);
		}
		throw new IllegalArgumentException("Unknown event type for " + this);
	}
	
	private Event toTroupeEvent(TroupeRepository troupeRepository, PlayerCharacterRepository characterRepository) {
		Set<PlayerCharacter> attendance = attendingCharacters.stream().map((DisplayablePlayerCharacter pc) -> characterRepository.findWithId(pc.id)).collect(Collectors.toSet());
		return new TroupeEvent(id, name, eventDate, eventStatus.to(), attendance, troupeRepository.findWithId(troupe.id));
	}
	
	private Event toVenueEvent(PlayerCharacterRepository characterRepository) {
		Set<PlayerCharacter> attendance = attendingCharacters.stream().map((DisplayablePlayerCharacter pc) -> characterRepository.findWithId(pc.id)).collect(Collectors.toSet());
		return new VenueEvent(id, name, eventDate, eventStatus.to(), attendance, venue.to());
	}
	
	@Deprecated
	//Jackson only
	public DisplayableEvent() {
		this(null, null, null, null, null, null, null);
	}

	public DisplayableEvent(Integer id, String name, DisplayableEventStatus eventStatus, 
			                Date eventDate, Set<DisplayablePlayerCharacter> attendingCharacters,
			                DisplayableVenue venue, DisplayableTroupe troupe) {
		this.id = id;
		this.name = name;
		this.eventStatus = eventStatus;
		this.eventDate = eventDate;
		this.attendingCharacters = (attendingCharacters == null ? new HashSet<DisplayablePlayerCharacter>() : attendingCharacters);
		this.venue = venue;
		this.troupe = troupe;
	}

	@Override
	public int compareTo(DisplayableEvent that) {
		return this.id - that.id;
	}
}
