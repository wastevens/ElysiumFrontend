package com.dstevens.web.user.controllers;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dstevens.suppliers.ClockSupplier;
import com.dstevens.user.DisplayableUser;
import com.dstevens.user.User;
import com.dstevens.user.patronage.Patronage;
import com.dstevens.user.patronage.PatronagePaymentReceipt;
import com.dstevens.user.patronage.PatronageRepository;
import com.dstevens.user.patronage.PaymentType;
import com.dstevens.web.config.RequestingUserProvider;
import com.google.gson.Gson;

@Controller
public class PatronagePageController {
	
	private final PatronageRepository patronageRepository;
	private final RequestingUserProvider requestingUserProvider;
	private final ClockSupplier clockSupplier;

	@Autowired
	public PatronagePageController(RequestingUserProvider requestingUserProvider, ClockSupplier clockSupplier, PatronageRepository patronageRepository) {
		this.requestingUserProvider = requestingUserProvider;
		this.clockSupplier = clockSupplier;
		this.patronageRepository = patronageRepository;
		
	}
	
	@RequestMapping(value = "/user/page/patronage", method = RequestMethod.GET)
	public ModelAndView getPatronagePage() {
		ModelAndView modelAndView = new ModelAndView("/user/patronage");
		User user = requestingUserProvider.get();
		String json = new Gson().toJson(DisplayableUser.fromOn(user, Date.from(clockSupplier.get().instant())));
		modelAndView.addObject("user", json);
		return modelAndView;
	}
	
	@RequestMapping(value = "/user/patronage/payments/paypal", method = RequestMethod.POST)
	public RedirectView makePaypalPayment(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("Return url: /user/page/patronage/payments/paypal/confirm");
//		System.out.println("Cancel url: /user/main");
		return new RedirectView("http://www.paypal.com");
	}
	
	@RequestMapping(value = "/user/page/patronage/payments/paypal/confirm", method = RequestMethod.GET)
	public ModelAndView paypalPaymentConfirm(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/user/paypal_confirm");
	}
	
	@RequestMapping(value = "/user/patronage/payments/paypal/confirm", method = RequestMethod.POST)
	public ModelAndView paypalPaymentConfirmSuccess(HttpServletRequest request, HttpServletResponse response) {
		Patronage patronage = requestingUserProvider.get().getPatronage();
		patronage.getPayments().add(paypalPayment());
		patronageRepository.save(incrementExpirationDateOf(patronage));
		return getPatronagePage();
	}

	private Patronage incrementExpirationDateOf(Patronage patronage) {
		Instant instantToIncrement = getInstantToIncrement(patronage);
		ZonedDateTime ofInstant = ZonedDateTime.ofInstant(instantToIncrement, ZoneOffset.UTC);
		ZonedDateTime plus = ofInstant.plus(1, ChronoUnit.YEARS);
		Instant instant = plus.toInstant();
		Date from = Date.from(instant);
		return patronage.expiringOn(from);
	}

	private Instant getInstantToIncrement(Patronage patronage) {
		if(patronage.getExpiration().toInstant().isAfter(clockSupplier.get().instant()))
			return patronage.getExpiration().toInstant();
		return clockSupplier.get().instant();
	}

	private PatronagePaymentReceipt paypalPayment() {
		return new PatronagePaymentReceipt(PaymentType.PAYPAL, new BigDecimal(20), "paypal receipt identifier", Date.from(clockSupplier.get().instant()));
	}
}
