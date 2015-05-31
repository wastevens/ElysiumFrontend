package com.dstevens.character;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dstevens.troupe.Venue;
import com.dstevens.utilities.Identified;

@Entity
@Table(name="UTPlayerCharacter")
public class UTPlayerCharacter implements Identified<Integer> {

	@Column(name="id")
	private final Integer id;
	
	@Column(name="playerCharacter")
	private final PlayerCharacter character;
	
	@Column(name="venue")
	private final Venue venue;
	
	//Hibernate only
    @Deprecated
    public UTPlayerCharacter() {
    	this(null, null, null);
    }
    
	public UTPlayerCharacter(Integer id, PlayerCharacter character, Venue venue) {
		this.id = id;
		this.character = character;
		this.venue = venue;
    }
    
    public Integer getId() {
    	return id;
    }

	public Venue getVenue() {
		return venue;
	}
	
	public PlayerCharacter getCharacter() {
		return character;
	}
}
