package com.dstevens.users.security;

import java.time.Clock;
import java.util.Date;

import com.dstevens.suppliers.IdSupplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserPasswordResetToken")
public class UserPasswordResetToken {

	@Id
    private final String id;
    
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
    	this(null, null, null);
    }
    
    public UserPasswordResetToken(String email, String resetToken, Date expiresAt) {
    	this.id = new IdSupplier().get();
		this.email = email;
		this.resetToken = resetToken;
		this.expiresAt = expiresAt;
    }
    
    public boolean isExpiredAsOf(Clock clock) {
    	return clock.instant().isBefore(expiresAt.toInstant());
    }
    
}
