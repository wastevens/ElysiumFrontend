package com.dstevens.user.patronage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;

public class DisplayablePatronagePaymentReceipt implements Comparable<DisplayablePatronagePaymentReceipt> {

	public DisplayablePaymentType paymentType;
	public String paymentReceiptIdentifier;
	public String paymentDate;
	
	public DisplayablePatronagePaymentReceipt() {
		this(null, null, null);
	}
	
	public DisplayablePatronagePaymentReceipt(DisplayablePaymentType paymentType, String paymentReceiptIdentifier, String paymentDate) {
		this.paymentType = paymentType;
		this.paymentReceiptIdentifier = paymentReceiptIdentifier;
		this.paymentDate = paymentDate;
	}

	public static DisplayablePatronagePaymentReceipt from(PatronagePaymentReceipt payment) {
		return new DisplayablePatronagePaymentReceipt(DisplayablePaymentType.from(payment.getPaymentType()), 
				                                      payment.getPaymentReceiptIdentifier(), 
				                                      new SimpleDateFormat("yyyy-MM-dd").format(payment.getPaymentDate()));
	}

	public PatronagePaymentReceipt toReceipt() {
		return new PatronagePaymentReceipt(paymentType.to(), paymentReceiptIdentifier, paymentDate());
	}

	private Date paymentDate() {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(paymentDate);
		} catch(ParseException e) {
		}
		return date;
	}
	
	@Override
	public int compareTo(DisplayablePatronagePaymentReceipt that) {
		Function<DisplayablePatronagePaymentReceipt, String> byDate = ((DisplayablePatronagePaymentReceipt p) -> p.paymentDate);
		Function<DisplayablePatronagePaymentReceipt, String> byReceipt = ((DisplayablePatronagePaymentReceipt p) -> p.paymentReceiptIdentifier);
        return Comparator.nullsLast(Comparator.comparing(byDate)).thenComparing(Comparator.nullsLast(Comparator.comparing(byReceipt))).compare(this, that);
	}
	
}
