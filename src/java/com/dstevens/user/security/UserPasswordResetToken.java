package com.dstevens.user.security;

import java.time.Clock;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="UserPasswordResetToken")
public class UserPasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "passwordResetToken", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
    
    @Column(name="email")
    private final String email;
    
    @Column(name="resetToken")
    private final String resetToken;

    @Column(name="expiresAt")
    private final Date expiresAt;
    
  //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
	private UserPasswordResetToken() {
    	this(null, null, null, null);
    }
    
    public UserPasswordResetToken(Integer id, String email, String resetToken, Date expiresAt) {
    	this.id = id;
		this.email = email;
		this.resetToken = resetToken;
		this.expiresAt = expiresAt;
    }
    
    public boolean isExpiredAsOf(Clock clock) {
    	return clock.instant().isBefore(expiresAt.toInstant());
    }
    
}
