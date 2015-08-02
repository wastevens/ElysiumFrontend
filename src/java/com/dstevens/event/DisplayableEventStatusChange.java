package com.dstevens.event;

import java.util.Date;

public class DisplayableEventStatusChange implements Comparable<DisplayableEventStatusChange> {

	public Date statusChanged;
    public DisplayableEventStatus status;
    
	@Deprecated
	//Jackson only
	public DisplayableEventStatusChange() {
		this(null, null);
	}
	
	private DisplayableEventStatusChange(Date statusChanged, DisplayableEventStatus status) {
		this.statusChanged = statusChanged;
		this.status = status;
	}
	
	public static DisplayableEventStatusChange from(EventStatusChange t) {
		return new DisplayableEventStatusChange(t.getStatusChanged(), DisplayableEventStatus.from(t.getStatus()));
	}
	
	public EventStatusChange to() {
		return new EventStatusChange(statusChanged, status.to());
	}
    
	@Override
	public int compareTo(DisplayableEventStatusChange that) {
		return this.statusChanged.compareTo(that.statusChanged);
	}
}
