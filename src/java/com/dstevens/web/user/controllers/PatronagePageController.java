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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.dstevens.web.user.payment.DoExpressCheckoutPaymentResponse;
import com.dstevens.web.user.payment.GetExpressCheckoutDetailsResponse;
import com.dstevens.web.user.payment.PaypalExpressCheckout;
import com.google.gson.Gson;

@Controller
public class PatronagePageController {
	
	@Value("${paypal.url:https://www.sandbox.paypal.com}") private String paypalHost;
	@Value("${patronage.donation:20.00}") private String patronageDonationAmount;
	
	private final PatronageRepository patronageRepository;
	private final RequestingUserProvider requestingUserProvider;
	private final ClockSupplier clockSupplier;
	private final PaypalExpressCheckout paypalExpressCheckout;

	@Autowired
	public PatronagePageController(RequestingUserProvider requestingUserProvider, ClockSupplier clockSupplier, PatronageRepository patronageRepository, PaypalExpressCheckout paypalExpressCheckout) {
		this.requestingUserProvider = requestingUserProvider;
		this.clockSupplier = clockSupplier;
		this.patronageRepository = patronageRepository;
		this.paypalExpressCheckout = paypalExpressCheckout;
		
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
		return new RedirectView(paypalHost + "/cgi-bin/webscr?cmd=_express-checkout&token=" + paypalExpressCheckout.setExpressCheckout(patronageDonationAmount));
	}
	
	@RequestMapping(value = "/user/page/patronage/payments/paypal/confirm", method = RequestMethod.GET)
	public ModelAndView paypalPaymentConfirm(HttpServletRequest request, HttpServletResponse response, 
			                                 @RequestParam String token) {
		GetExpressCheckoutDetailsResponse expressCheckoutResponse = paypalExpressCheckout.getExpressCheckoutDetails(token);
		ModelAndView confirmPage = new ModelAndView("/user/paypal_confirm");
		confirmPage.addObject("GetExpressCheckoutDetailsResponse", expressCheckoutResponse);
		return confirmPage;
	}
	
	@RequestMapping(value = "/user/patronage/payments/paypal/confirm", method = RequestMethod.POST)
	public ModelAndView paypalPaymentConfirmSuccess(HttpServletRequest request, HttpServletResponse response,  
			                                        @RequestParam String token, @RequestParam String payerId, @RequestParam String amount) {
		DoExpressCheckoutPaymentResponse expressCheckoutPayment = paypalExpressCheckout.doExpressCheckoutPayment(token, payerId, amount);
		User user = requestingUserProvider.get();
		Patronage patronage = user.getPatronage();
		
		if(patronage == null) {
			patronage = createPatronageFor(user);
		}
		patronage.getPayments().add(paypalPayment(expressCheckoutPayment));
		patronageRepository.save(incrementExpirationDateOf(patronage));
		return getPatronagePage();
	}

	private Patronage createPatronageFor(User user) {
		Instant instant = clockSupplier.get().instant();
		int year = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC).getYear();
		Patronage patronage = patronageRepository.save(new Patronage(year, Date.from(instant), ""));
		Patronage patronageWithUser = patronageRepository.save(patronage.forUser(user));
		return patronageRepository.save(patronageWithUser);
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
		if(patronage != null && patronage.getExpiration() != null && patronage.getExpiration().toInstant().isAfter(clockSupplier.get().instant()))
			return patronage.getExpiration().toInstant();
		return clockSupplier.get().instant();
	}

	private PatronagePaymentReceipt paypalPayment(DoExpressCheckoutPaymentResponse expressCheckoutPayment) {
		return new PatronagePaymentReceipt(PaymentType.PAYPAL, new BigDecimal(expressCheckoutPayment.getAmount()), expressCheckoutPayment.getPaypalIdentifier(), Date.from(clockSupplier.get().instant()));
	}
}
