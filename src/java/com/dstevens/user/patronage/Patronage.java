package com.dstevens.user.patronage;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import com.dstevens.user.User;

import static com.dstevens.collections.Lists.list;

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
	
    @ElementCollection
    @JoinColumn(name="patronage_id", referencedColumnName="id")
    @OrderColumn(name="order_by")
    @ForeignKey(name="Patronage_PatronagePaymentReceipt_FK")
    private final List<PatronagePaymentReceipt> payments = list();
    
    @Column(name="original_username")
    private final String originalUsername;
    
	//Hibernate only
    @Deprecated
    public Patronage() {
    	this(null, null, null, null, null, list());
    }
    
    public Patronage(Integer year, Date expiration, String originalUsername) {
    	this(null, year, expiration, null, originalUsername, list());
    }
    
	public Patronage(Integer id, Integer year, Date expiration, User user, String originalUsername, List<PatronagePaymentReceipt> payments) {
		this.id = id;
		this.year = year;
		this.expiration = expiration;
		this.user = user;
		this.originalUsername = originalUsername;
		this.payments.addAll(payments);
	}

	public String displayMembershipId() {
		return year + String.format("%06d", id);
	}
	
	public Integer getId() {
		return id;
	}
	
	public Date getExpiration() {
		return expiration;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getOriginalUsername() {
		return originalUsername;
	}
	
	public List<PatronagePaymentReceipt> getPayments() {
		return payments;
	}
	

	public Patronage updateTo(Patronage patronage) {
		Patronage updatedPatronage = this.withOriginalUsername(patronage.getOriginalUsername()).
		                                  expiringOn(patronage.getExpiration()).
		                                  forUser(patronage.getUser());
		updatedPatronage.payments.clear();
		updatedPatronage.payments.addAll(patronage.getPayments());
		return updatedPatronage;
	}
	
	public boolean matchingUser(User user) {
		if(this.user == null || user == null) {
			return this.user == user;
		}
		return (this.user.getId() == user.getId());
	}
	
	public boolean isActiveAsOf(Date date) {
		return expiration == null ? false : expiration.after(date);
	}
	
	public Patronage expiringOn(Date expiration) {
		return new Patronage(id, year, expiration, user, originalUsername, payments);
	}
	
	public Patronage forUser(User user) {
		return new Patronage(id, year, expiration, user, originalUsername, payments);
	}
	
	public Patronage withPayment(PatronagePaymentReceipt payment) {
		payments.add(payment);
		return new Patronage(id, year, expiration, user, originalUsername, payments);
	}

	public Patronage withOriginalUsername(String originalUsername) {
		return new Patronage(id, year, expiration, user, originalUsername, payments);
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
