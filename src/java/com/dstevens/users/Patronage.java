package com.dstevens.users;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

@SuppressWarnings("deprecation")
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
	
    @OneToOne(cascade={CascadeType.ALL}, optional=true)
    @JoinTable(name="User_Patronage", 
    joinColumns = @JoinColumn(name="patronage_id", nullable=true), 
    inverseJoinColumns = @JoinColumn(name="user_id", nullable=true),
    uniqueConstraints={@UniqueConstraint(columnNames={"patronage_id", "user_id"}, name="User_Patronage_UC")})
    @ForeignKey(name="Patronage_User_FK", inverseName="User_Patronage_FK")
	private final User user;
	
	//Hibernate only
    @Deprecated
    public Patronage() {
    	this(null, null, null, null);
    }
    
    public Patronage(Integer year, Date expiration) {
    	this(null, year, expiration, null);
    }
    
	public Patronage(Integer id, Integer year, Date expiration, User user) {
		this.id = id;
		this.year = year;
		this.expiration = expiration;
		this.user = user;
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
	
	public Patronage expiringOn(Date expiration) {
		return new Patronage(id, year, expiration, user);
	}
	
	public Patronage forUser(User user) {
		return new Patronage(id, year, expiration, user);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
