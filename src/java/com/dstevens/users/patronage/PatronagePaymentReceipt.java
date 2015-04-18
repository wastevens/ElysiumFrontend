package com.dstevens.users.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class PatronagePaymentReceipt {

	@Column(name="payment_type")
	private final PaymentType paymentType;
	@Column(name="payment_receipt_identifier")
	private final String paymentReceiptIdentifier;
	@Column(name="payment_date")
	private final Date paymentDate;
	
	//Hibernate only
    @Deprecated
    public PatronagePaymentReceipt() {
    	this(null, null, null);
    }
	
	public PatronagePaymentReceipt(PaymentType paymentType, String paymentReceiptIdentifier, Date paymentDate) {
		this.paymentType = paymentType;
		this.paymentReceiptIdentifier = paymentReceiptIdentifier;
		this.paymentDate = paymentDate;
	}
	
	public PaymentType getPaymentType() {
		return paymentType;
	}

	public String getPaymentReceiptIdentifier() {
		return paymentReceiptIdentifier;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
