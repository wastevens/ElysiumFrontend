package com.dstevens.event;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import com.dstevens.troupe.Troupe;
import com.dstevens.user.PlayerCharacterOwnership;

@SuppressWarnings("deprecation")
@Entity
public class TroupeEvent extends Event {

	@OneToOne
	@JoinColumn(name="troupe_id", referencedColumnName="id")
    @ForeignKey(name="TroupeEvent_Troupe_FK", inverseName="Troupe_TroupeEvent_FK")
	private final Troupe troupe;

    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="TroupeEvent_Attendance", joinColumns = @JoinColumn(name="event_id"), 
	           inverseJoinColumns = @JoinColumn(name="playerCharacterOwnership_id"))
	@ForeignKey(name="TroupeEvent_PlayerCharacterOwnership_FK", inverseName="PlayerCharacterOwnership_TroupeEvent_FK")
    private final Set<PlayerCharacterOwnership> attendance;
	
    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private TroupeEvent() {
    	this(null, null, null, null, null, null);
    }
	
	public TroupeEvent(Integer id, String name, Date eventDate, EventStatus eventStatus, Troupe troupe, Set<PlayerCharacterOwnership> attendance) {
		super(id, name, eventDate, eventStatus);
		this.troupe = troupe;
		this.attendance = attendance;
	}

	public Troupe getTroupe() {
		return troupe;
	}
	
	public Set<PlayerCharacterOwnership> getAttendance() {
		return attendance;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
