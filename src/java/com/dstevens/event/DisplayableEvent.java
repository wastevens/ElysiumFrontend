package com.dstevens.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.PlayerCharacterRepository;
import com.dstevens.troupe.DisplayableTroupe;
import com.dstevens.troupe.DisplayableVenue;
import com.dstevens.troupe.TroupeRepository;

import static com.dstevens.collections.Sets.set;

public class DisplayableEvent implements Comparable<DisplayableEvent> {

    public Integer id;
    public String name;
    public DisplayableVenue venue;
    public DisplayableTroupe troupe;
    public String eventDate;
    public DisplayableEventStatus eventStatus;
    public Set<DisplayablePlayerCharacter> attendingCharacters;

	public static DisplayableEvent from(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getEventStatus());
		Date eventDate = t.getEventDate();
		DisplayableVenue venue = (t instanceof VenueEvent ? DisplayableVenue.listable(((VenueEvent)t).getVenue()) : null);
		DisplayableTroupe troupe = (t instanceof TroupeEvent ? DisplayableTroupe.listable(((TroupeEvent)t).getTroupe()) : null);
		return new DisplayableEvent(id, name, eventStatus, displayableDate(eventDate), venue, troupe);
	}
    
	public static DisplayableEvent reference(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getEventStatus());
		Date eventDate = t.getEventDate();
		DisplayableVenue venue = (t instanceof VenueEvent ? DisplayableVenue.listable(((VenueEvent)t).getVenue()) : null);
		DisplayableTroupe troupe = (t instanceof TroupeEvent ? DisplayableTroupe.listable(((TroupeEvent)t).getTroupe()) : null);
		return new DisplayableEvent(id, name, eventStatus, displayableDate(eventDate), venue, troupe);
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
		return new TroupeEvent(id, name, eventDate(), eventStatus.to(), troupeRepository.findWithId(troupe.id), set());
	}
	
	private Event toVenueEvent(PlayerCharacterRepository characterRepository) {
		return new VenueEvent(id, name, eventDate(), eventStatus.to(), venue.to(), set());
	}
	
	private static String displayableDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	private Date eventDate() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
		} catch (ParseException e) {
			throw new IllegalStateException("Event date cannot be parsed!", e);
		}
	}
	
	@Deprecated
	//Jackson only
	public DisplayableEvent() {
		this(null, null, null, null, null, null);
	}	

	public DisplayableEvent(Integer id, String name, DisplayableEventStatus eventStatus, 
			                String eventDate, DisplayableVenue venue,
			                DisplayableTroupe troupe) {
		this.id = id;
		this.name = name;
		this.eventStatus = eventStatus;
		this.eventDate = eventDate;
		this.venue = venue;
		this.troupe = troupe;
	}

	@Override
	public int compareTo(DisplayableEvent that) {
		return this.id - that.id;
	}
}
