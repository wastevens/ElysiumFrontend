package com.dstevens.users.patronages;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;

public class DisplayablePatronagePaymentReceipt implements Comparable<DisplayablePatronagePaymentReceipt> {

	public Integer paymentType;
	public String paymentReceiptIdentifier;
	public String paymentDate;
	
	public DisplayablePatronagePaymentReceipt() {
		this(null, null, null);
	}
	
	public DisplayablePatronagePaymentReceipt(Integer paymentType, String paymentReceiptIdentifier, String paymentDate) {
		this.paymentType = paymentType;
		this.paymentReceiptIdentifier = paymentReceiptIdentifier;
		this.paymentDate = paymentDate;
	}

	public static DisplayablePatronagePaymentReceipt from(PatronagePaymentReceipt payment) {
		PaymentType paymentType2 = payment.getPaymentType();
		int ordinal = paymentType2.ordinal();
		String paymentReceiptIdentifier2 = payment.getPaymentReceiptIdentifier();
		Date paymentDate2 = payment.getPaymentDate();
		return new DisplayablePatronagePaymentReceipt(ordinal, paymentReceiptIdentifier2, new SimpleDateFormat("yyyy-MM-dd").format(paymentDate2));
	}

	@Override
	public int compareTo(DisplayablePatronagePaymentReceipt that) {
		Function<DisplayablePatronagePaymentReceipt, String> byDate = ((DisplayablePatronagePaymentReceipt p) -> p.paymentDate);
		Function<DisplayablePatronagePaymentReceipt, String> byReceipt = ((DisplayablePatronagePaymentReceipt p) -> p.paymentReceiptIdentifier);
        return Comparator.nullsLast(Comparator.comparing(byDate)).thenComparing(Comparator.nullsLast(Comparator.comparing(byReceipt))).compare(this, that);
	}
	
}
