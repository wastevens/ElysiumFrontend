package com.dstevens.user.patronage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.dstevens.config.controllers.BadRequestException;


public class DisplayablePatronage implements Comparable<DisplayablePatronage> {

    public String membershipId;
	public String expiration;
	public Integer userId;
	public String originalUsername;
	public List<DisplayablePatronagePaymentReceipt> payments;
	public boolean activePatron;
	
	public DisplayablePatronage() {
		this(null, null, null, false, null, null);
	}
	
	public DisplayablePatronage(String membershipId, String expiration, Integer userId, boolean activePatron, String originalUsername, List<DisplayablePatronagePaymentReceipt> payments) {
		this.membershipId = membershipId;
		this.expiration = expiration;
		this.userId = userId;
		this.activePatron = activePatron;
		this.originalUsername = originalUsername;
		this.payments = payments;
	}

	public static DisplayablePatronage fromOn(Patronage patronage, Date date) {
		Integer id = null;
		if(patronage.getUser() != null) {
			id = patronage.getUser().getId();
		}
		boolean activePatronage = patronage.isActiveAsOf(date);
		Optional<Date> expirationDate = Optional.ofNullable(patronage.getExpiration());
		return new DisplayablePatronage(patronage.displayMembershipId(), expirationDate.map((Date d) -> new SimpleDateFormat("yyyy-MM-dd").format(d)).orElse(null), 
				                        id, activePatronage, patronage.getOriginalUsername(), 
				                        patronage.getPayments().stream().map((PatronagePaymentReceipt t) -> DisplayablePatronagePaymentReceipt.from(t)).collect(Collectors.toList()));
	}

	@Override
	public int compareTo(DisplayablePatronage that) {
		return Comparator.comparing((DisplayablePatronage t) -> t.expiration == null ? "" : t.expiration).
				thenComparing(Comparator.comparing((DisplayablePatronage t) -> t.membershipId)).
				compare(this, that);
	}

	public Patronage toPatronage(int year) {
		Patronage patronage = new Patronage(year, expirationAsDate(), originalUsername);
		List<PatronagePaymentReceipt> collect = payments.stream().map((DisplayablePatronagePaymentReceipt t) -> t.to()).collect(Collectors.toList());
		collect.forEach((PatronagePaymentReceipt t) -> patronage.withPayment(t));
		return patronage;
	}
	
	private Date expirationAsDate() {
		if(!StringUtils.isBlank(expiration)) {
			try {
				return Date.from(LocalDate.parse(expiration, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			} catch (DateTimeParseException e) {
				throw new BadRequestException("Could not parse " + expiration + "; please make sure expiration dates are in yyyy-MM-dd format");
			}
		}
		return null;
	}
}
