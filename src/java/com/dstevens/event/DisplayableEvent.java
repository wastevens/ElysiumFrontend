package com.dstevens.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.dstevens.troupe.DisplayableTroupe;
import com.dstevens.troupe.DisplayableVenue;
import com.dstevens.troupe.TroupeRepository;
import com.dstevens.user.DisplayablePlayerCharacterOwnership;
import com.dstevens.user.PlayerCharacterOwnership;

import static com.dstevens.collections.Sets.set;

public class DisplayableEvent implements Comparable<DisplayableEvent> {

    public Integer id;
    public String name;
    public DisplayableVenue venue;
    public DisplayableTroupe troupe;
    public String eventDate;
    public DisplayableEventStatus eventStatus;
    public Set<DisplayablePlayerCharacterOwnership> attendees;

	public static DisplayableEvent from(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getEventStatus());
		Date eventDate = t.getEventDate();
		if(VenueEvent.class.isAssignableFrom(t.getClass())) {
			VenueEvent ve = (VenueEvent) t;
			DisplayableVenue venue = DisplayableVenue.from(ve.getVenue());
			Set<DisplayablePlayerCharacterOwnership> attendees = 
					ve.getAttendance().stream().map((PlayerCharacterOwnership pco) -> DisplayablePlayerCharacterOwnership.from(pco)).collect(Collectors.toSet());
			return new DisplayableEvent(id, name, eventStatus, displayableDate(eventDate), attendees, venue, null);
		}
		if(TroupeEvent.class.isAssignableFrom(t.getClass())) {
			TroupeEvent te = (TroupeEvent) t;
			DisplayableTroupe troupe = DisplayableTroupe.from(te.getTroupe());
			Set<DisplayablePlayerCharacterOwnership> attendingCharacters = 
					te.getAttendance().stream().map((PlayerCharacterOwnership pco) -> DisplayablePlayerCharacterOwnership.from(pco)).collect(Collectors.toSet());
			return new DisplayableEvent(id, name, eventStatus, displayableDate(eventDate), attendingCharacters, null, troupe);
		}
		return null;
	}
    
	public static DisplayableEvent reference(Event t) {
		Integer id = t.getId();
		String name = t.getName();
		DisplayableEventStatus eventStatus = DisplayableEventStatus.from(t.getEventStatus());
		Date eventDate = t.getEventDate();
		DisplayableVenue venue = (t instanceof VenueEvent ? DisplayableVenue.listable(((VenueEvent)t).getVenue()) : null);
		DisplayableTroupe troupe = (t instanceof TroupeEvent ? DisplayableTroupe.listable(((TroupeEvent)t).getTroupe()) : null);
		return new DisplayableEvent(id, name, eventStatus, displayableDate(eventDate), null, venue, troupe);
	}
	
	public Event to(TroupeRepository troupeRepository) {
		if(troupe != null) {
			return toTroupeEvent(troupeRepository);
		}
		if(venue != null) {
			return toVenueEvent();
		}
		throw new IllegalArgumentException("Unknown event type for " + this);
	}
	
	private Event toTroupeEvent(TroupeRepository troupeRepository) {
		return new TroupeEvent(id, name, eventDate(), eventStatus.to(), troupeRepository.findWithId(troupe.id), set());
	}
	
	private Event toVenueEvent() {
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
		this(null, null, null, null, null, null, null);
	}	

	public DisplayableEvent(Integer id, String name, DisplayableEventStatus eventStatus, 
			                String eventDate, Set<DisplayablePlayerCharacterOwnership> attendees,
			                DisplayableVenue venue, DisplayableTroupe troupe) {
		this.id = id;
		this.name = name;
		this.eventStatus = eventStatus;
		this.eventDate = eventDate;
		this.attendees = attendees;
		this.venue = venue;
		this.troupe = troupe;
	}

	@Override
	public int compareTo(DisplayableEvent that) {
		return this.id - that.id;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
