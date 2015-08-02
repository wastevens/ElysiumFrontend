package com.dstevens.event;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.troupe.Troupe;
import com.dstevens.troupe.Venue;

import static com.dstevens.collections.Lists.last;
import static com.dstevens.collections.Lists.sort;

@SuppressWarnings("deprecation")
@Entity
@Table(name="Event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "event", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
    @Column(name="name")
    private String name;
    
    @Column(name="venue")
    private Venue venue;
    
    @ElementCollection
    @OrderColumn(name="order_by")
    @CollectionTable(name="EventStatusHistory", joinColumns=@JoinColumn(name="event_id"))
    @ForeignKey(name="Event_EventStatusHistory_FK", inverseName="EventStatusHistory_Event_FK")
    private List<EventStatusChange> eventStatusChanges;
    
    @OneToOne
    @JoinColumn(name="troupe_id", referencedColumnName="id")
    @ForeignKey(name="Event_Troupe_FK", inverseName="Troupe_Event_FK")
    private Troupe troupe;
    
    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Event_PlayerCharacters", joinColumns = @JoinColumn(name="event_id"), 
	           inverseJoinColumns = @JoinColumn(name="playerCharacter_id"))
	@ForeignKey(name="Event_PlayerCharacters_FK", inverseName="PlayerCharacters_Event_FK")
    private Set<PlayerCharacter> attendingCharacters;

    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private Event() {
    	this(null, null, null, null, null, null);
    }
    
	public Event(Integer id, String name, Venue venue, List<EventStatusChange> eventStatusChanges, Troupe troupe, Set<PlayerCharacter> attendingCharacters) {
		this.id = id;
		this.name = name;
		this.venue = venue;
		this.eventStatusChanges = eventStatusChanges;
		this.troupe = troupe;
		this.attendingCharacters = attendingCharacters;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Venue getVenue() {
		return venue;
	}

	public List<EventStatusChange> getEventStatusChanges() {
		return eventStatusChanges;
	}

	public Troupe getTroupe() {
		return troupe;
	}

	public Set<PlayerCharacter> getAttendingCharacters() {
		return attendingCharacters;
	}

	public EventStatus getCurrentStatus() {
		return last(sort(eventStatusChanges)).getStatus();
	}
}
