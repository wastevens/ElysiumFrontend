package com.dstevens.event;


public class DisplayableEventStatus {

	public Integer id;
	
	@Deprecated
	//Jackson only
	public DisplayableEventStatus() {
		this(null);
	}
	
	private DisplayableEventStatus(Integer id) {
		this.id = id;
	}
	
	public static DisplayableEventStatus from(EventStatus t) {
		return new DisplayableEventStatus(t.getId());
	}
	
	public EventStatus to() {
		return EventStatus.from(id);
	}
	
}
