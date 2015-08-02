package com.dstevens.event;

import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.troupe.Troupe;

@SuppressWarnings("deprecation")
@Entity
@DiscriminatorValue("TROUPE")
public class TroupeEvent extends Event {

	@OneToOne
	@JoinColumn(name="troupe_id", referencedColumnName="id")
    @ForeignKey(name="TroupeEvent_Troupe_FK", inverseName="Troupe_TroupeEvent_FK")
	private final Troupe troupe;

    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private TroupeEvent() {
    	this(null, null, null, null, null, null);
    }
	
	public TroupeEvent(Integer id, String name, Date eventDate, EventStatus eventStatus, Set<PlayerCharacter> attendingCharacters, Troupe troupe) {
		super(id, name, eventDate, eventStatus, attendingCharacters);
		this.troupe = troupe;
	}

	public Troupe getTroupe() {
		return troupe;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
