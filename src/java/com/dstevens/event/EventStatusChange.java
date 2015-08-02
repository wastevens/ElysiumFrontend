package com.dstevens.event;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class EventStatusChange implements Comparable<EventStatusChange> {

	@Column(name="statusChanged")
	private final Date statusChanged;
	
    @Column(name="status")
    private final EventStatus status;
    
    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private EventStatusChange() {
    	this(null, null);
    }
    
	public EventStatusChange(Date statusChanged, EventStatus status) {
		this.statusChanged = statusChanged;
		this.status = status;
	}

	public Date getStatusChanged() {
		return statusChanged;
	}

	public EventStatus getStatus() {
		return status;
	}

    @Override
    public boolean equals(Object that) {
    	return EqualsBuilder.reflectionEquals(this, that);
    }
    
    @Override
    public int hashCode() {
    	return HashCodeBuilder.reflectionHashCode(this);
    }
    
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

	@Override
	public int compareTo(EventStatusChange that) {
		return this.statusChanged.compareTo(that.statusChanged);
	}
    
}
