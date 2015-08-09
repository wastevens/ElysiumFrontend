package com.dstevens.event;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.troupe.Venue;
import com.dstevens.user.PlayerCharacterOwnership;

@SuppressWarnings("deprecation")
@Entity
public class VenueEvent extends Event {

	@Column(name="venue")
	private final Venue venue;

    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="VenueEvent_Attendance", joinColumns = @JoinColumn(name="event_id"), 
	           inverseJoinColumns = @JoinColumn(name="playerCharacterOwnership_id"))
	@ForeignKey(name="VenueEvent_PlayerCharacterOwnership_FK", inverseName="PlayerCharacterOwnership_TroupeEvent_FK")
    private final Set<PlayerCharacterOwnership> attendance;
	
    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private VenueEvent() {
    	this(null, null, null, null, null, null);
    }
	
	public VenueEvent(Integer id, String name, Date eventDate, EventStatus eventStatus, Venue venue, Set<PlayerCharacterOwnership> attendance) {
		super(id, name, eventDate, eventStatus);
		this.venue = venue;
		this.attendance = attendance;
	}

	public Venue getVenue() {
		return venue;
	}
	
	public Set<PlayerCharacterOwnership> getAttendance() {
		return attendance;
	}

}
