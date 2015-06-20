package com.dstevens.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.status.PlayerCharacterStatus;
import com.dstevens.character.status.PlayerStatusChange;
import com.dstevens.troupe.Venue;

import static com.dstevens.collections.Lists.last;
import static com.dstevens.collections.Lists.list;
import static com.dstevens.collections.Lists.sort;

@SuppressWarnings("deprecation")
@Entity
@Table(name="PlayerCharacterOwnership")
public class PlayerCharacterOwnership {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "user", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
	@OneToOne
	@JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacterOwnership_PlayerCharacter_FK", inverseName="PlayerCharacter_PlayerCharacterOwnership_FK")
	private final PlayerCharacter character;
	
	@Column(name="venue")
	private final Venue venue;
	
	@ElementCollection
	@JoinColumn(name="playerCharacterOwnership_id", referencedColumnName="id")
    @OrderColumn(name="order_by")
	private final List<PlayerStatusChange> statusChanges;
	
	//Hibernate only
    @Deprecated
	public PlayerCharacterOwnership() {
    	this(null, null, null);
		
	}
    
    public PlayerCharacterOwnership(PlayerCharacter character, Venue venue, PlayerStatusChange statusChange) {
    	this(null, character, venue, list(statusChange));
    }
	
    private PlayerCharacterOwnership(Integer id, PlayerCharacter character, Venue venue, List<PlayerStatusChange> statusChanges) {
		this.id = id;
		this.character = character;
		this.venue = venue;
		this.statusChanges = statusChanges;
    }
    
	public Integer getId() {
		return id;
	}

	public PlayerCharacter getCharacter() {
		return character;
	}

	public Venue getVenue() {
		return venue;
	}

	public PlayerCharacterStatus getStatus() {
		return last(sort(statusChanges)).status();
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
	
}
