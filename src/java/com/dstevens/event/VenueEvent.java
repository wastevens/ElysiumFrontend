package com.dstevens.event;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.troupe.Venue;

@Entity
@DiscriminatorValue("VENUE")
public class VenueEvent extends Event {

	@Column(name="venue")
	private final Venue venue;

    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private VenueEvent() {
    	this(null, null, null, null, null, null);
    }
	
	public VenueEvent(Integer id, String name, Date eventDate, EventStatus eventStatus, Set<PlayerCharacter> attendingCharacters, Venue venue) {
		super(id, name, eventDate, eventStatus, attendingCharacters);
		this.venue = venue;
	}

	public Venue getVenue() {
		return venue;
	}

}
