package com.dstevens.troupe;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.user.User;
import com.dstevens.utilities.ObjectExtensions;

import static com.dstevens.collections.Sets.set;
import static com.dstevens.collections.Sets.setWith;
import static com.dstevens.collections.Sets.setWithout;

@SuppressWarnings("deprecation")
@Entity
@Table(name="Troupe")
public class Troupe implements Comparable<Troupe> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "troupe", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="venue")
    private Venue venue;
    
    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Troupe_PlayerCharacters", joinColumns = @JoinColumn(name="troupe_id"), 
	           inverseJoinColumns = @JoinColumn(name="playerCharacter_id"))
	@ForeignKey(name="Troupe_PlayerCharacters_FK", inverseName="PlayerCharacters_Troupe_FK")
    private Set<PlayerCharacter> characters;
    
    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Troupe_StorytellerUsers", 
	           joinColumns = @JoinColumn(name="troupe_id"), 
	           inverseJoinColumns = @JoinColumn(name="user_id"))
	@ForeignKey(name="Troupe_StorytellerUsers_FK", inverseName="StorytellerUsers_Troupe_FK")
    private Set<User> storytellers;

    @Column(name="deleted_at")
    private final Date deleteTimestamp;
    
    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private Troupe() {
        this(null, null, null, set(), set(), null);
    }
    
    Troupe(Integer id, String name, Venue venue) {
        this(id, name, venue, set(), set(), null);
    }
    
    private Troupe(Integer id, String name, Venue venue, Set<PlayerCharacter> characters, Set<User> storytellers, Date deleteTimestamp) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.characters = characters;
		this.storytellers = storytellers;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public Integer getId() {
        return id;
    }

    public Troupe withName(String name) {
        this.name = name;
        return this;
    }
    
    public String getName() {
        return name;
    }

    public Troupe withVenue(Venue venue) {
        this.venue = venue;
        return this;
    }
    
    public Venue getVenue() {
        return venue;
    }

    public Troupe withCharacter(PlayerCharacter character) {
        return new Troupe(id, name, venue, setWith(characters, character), storytellers, deleteTimestamp);
    }
    
    public Troupe withoutCharacter(PlayerCharacter character) {
        return new Troupe(id, name, venue, setWithout(characters, character), storytellers, deleteTimestamp);
    }
    
	public Troupe withoutCharacters() {
		this.characters = set();
		return this;
	}
    
    public Set<PlayerCharacter> getCharacters() {
        return characters;
    }
    
    public Troupe withStorytellers(Set<User> storytellers) {
    	return new Troupe(id, name, venue, characters, storytellers, deleteTimestamp);
    }
    
    public Troupe withStoryteller(User storyteller) {
    	return new Troupe(id, name, venue, characters, setWith(storytellers, storyteller), deleteTimestamp);
    }
    
    public Troupe withoutStoryteller(User storyteller) {
    	return new Troupe(id, name, venue, characters, setWithout(storytellers, storyteller), deleteTimestamp);
    }
    
    public Troupe withoutStorytellers() {
    	this.storytellers = set();
    	return this;
    }
    
    public Set<User> getStorytellers() {
    	return storytellers;
    }

    public Troupe delete(Date deleteTimestamp) {
        return new Troupe(id, name, venue, characters, storytellers, deleteTimestamp);
    }
    
    public Troupe undelete() {
        return new Troupe(id, name, venue, characters, storytellers, null);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Troupe) {
            Troupe that = Troupe.class.cast(obj);
            return this.id.equals(that.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(Troupe that) {
        Function<Troupe, Date>  byDeletedTimestamp = ((Troupe t) -> t.deleteTimestamp);
        Function<Troupe, String> byName = ((Troupe t) -> t.name);
        return Comparator.comparing(byDeletedTimestamp, Comparator.nullsFirst(Comparator.naturalOrder())).
                      thenComparing(Comparator.comparing(byName)).
                      compare(this, that);
    }
}
