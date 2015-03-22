package com.dstevens.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Patronage")
public class Patronage {


	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "patronage", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
	@Column(name="year")
	private final Integer year;
	
	@Column(name="expiration")
	private final Date expiration;
	
	@OneToOne(mappedBy="patronage", optional=true)
	private User user;
	
	//Hibernate only
    @Deprecated
    public Patronage() {
    	this(null, null, null);
    }
    
    public Patronage(Integer year, Date expiration) {
    	this(null, year, expiration);
    }
    
	public Patronage(Integer id, Integer year, Date expiration) {
		this.id = id;
		this.year = year;
		this.expiration = expiration;
	}

	public String displayMembershipId() {
		return year + String.format("%06d", id);
	}
	
	public Date getExpiration() {
		return expiration;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean isActiveAsOf(Date date) {
		return expiration.after(date);
	}
	
	public Patronage expiringOn(Date date) {
		return new Patronage(id, year, date);
	}
	
}
