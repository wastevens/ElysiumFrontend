package com.dstevens.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.status.PlayerStatus;
import com.dstevens.troupe.Venue;

@SuppressWarnings("deprecation")
@Entity
@Table(name="PlayerCharacterOwnership")
public class PlayerCharacterOwnership {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "user", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacterOwnership_User_FK", inverseName="User_PlayerCharacterOwnership_FK")
	private final User user;
	
	@OneToOne
	@JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacterOwnership_PlayerCharacter_FK", inverseName="PlayerCharacter_PlayerCharacterOwnership_FK")
	private final PlayerCharacter character;
	
	@Column(name="venue")
	private final Venue venue;
	
	@Column(name="status")
	private final PlayerStatus status;
	
	//Hibernate only
    @Deprecated
	public PlayerCharacterOwnership() {
    	this(null, null, null, null, null);
		
	}
    
    public PlayerCharacterOwnership(User user, PlayerCharacter character, Venue venue, PlayerStatus status) {
    	this(null, user, character, venue, status);
    }
	
    private PlayerCharacterOwnership(Integer id, User user, PlayerCharacter character, Venue venue, PlayerStatus status) {
		this.id = id;
		this.user = user;
		this.character = character;
		this.venue = venue;
		this.status = status;
    }
    
	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public PlayerCharacter getCharacter() {
		return character;
	}

	public Venue getVenue() {
		return venue;
	}

	public PlayerStatus getStatus() {
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
	
}
