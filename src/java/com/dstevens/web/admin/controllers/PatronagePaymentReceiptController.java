package com.dstevens.web.admin.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dstevens.config.controllers.BadRequestException;
import com.dstevens.config.controllers.ResourceNotFoundException;
import com.dstevens.user.patronage.DisplayablePatronagePaymentReceipt;
import com.dstevens.user.patronage.Patronage;
import com.dstevens.user.patronage.PatronagePaymentReceipt;
import com.dstevens.user.patronage.PatronageRepository;
import com.dstevens.user.patronage.PaymentType;
import com.google.gson.Gson;

@Controller
public class PatronagePaymentReceiptController {

	private final PatronageRepository patronageRepository;

	@Autowired
	public PatronagePaymentReceiptController(PatronageRepository patronageRepository) {
		this.patronageRepository = patronageRepository;
	}
	
	@RequestMapping(value = "/admin/patronages/{id}/payments", method = RequestMethod.GET)
	public @ResponseBody String getPatronagePayments(@PathVariable String id) {
		return new Gson().toJson(findPatronage(id).getPayments().stream().map((PatronagePaymentReceipt t) -> DisplayablePatronagePaymentReceipt.from(t)).collect(Collectors.toList()));
	}
	
	private Patronage findPatronage(String id) {
		Patronage patronage = patronageRepository.findPatronageByMembershipId(id);
		if(patronage == null) {
			throw new ResourceNotFoundException("Did not find patronage with membership id " + id);
		}
		return patronage;
	}
	
	@RequestMapping(value = "/admin/patronages/{id}/payments/{paymentIndex}", method = RequestMethod.GET)
	public @ResponseBody String getPatronagePayment(@PathVariable String id, @PathVariable int paymentIndex) {
		return new Gson().toJson(DisplayablePatronagePaymentReceipt.from(findPatronagePaymentReceipt(id, paymentIndex)));
	}

	private PatronagePaymentReceipt findPatronagePaymentReceipt(String id, int paymentIndex) {
		Patronage patronage = findPatronage(id);
		if(patronage.getPayments().size() > paymentIndex && paymentIndex >= 0) {
			return patronage.getPayments().get(paymentIndex);
		}
		throw new ResourceNotFoundException("Did not find payment with index " + paymentIndex);
	}
	
	@RequestMapping(value = "/admin/patronages/{id}/payments", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody String createPatronagePayment(@RequestBody RawCreatePatronagePaymentReceiptBody requestBody, @PathVariable String id, HttpServletResponse response) {
		Patronage patronage = findPatronage(id);
		patronage.getPayments().add(requestBody.toPaymentReceipt());
		Patronage updatedPatronage = patronageRepository.save(patronage);
		
		List<PatronagePaymentReceipt> payments = updatedPatronage.getPayments();
		int lastIndex = payments.size()-1;
		
		addPatronageReceiptLocationHeader(response, updatedPatronage, lastIndex);
		return new Gson().toJson(DisplayablePatronagePaymentReceipt.from(payments.get(lastIndex)));
	}
	
	@RequestMapping(value = "/admin/patronages/{id}/payments/{paymentIndex}", method = RequestMethod.PUT)
	public @ResponseBody String updatePatronagePayment(@RequestBody RawCreatePatronagePaymentReceiptBody requestBody, @PathVariable String id, @PathVariable int paymentIndex, HttpServletResponse response) {
		findPatronagePaymentReceipt(id, paymentIndex);
		Patronage patronage = findPatronage(id);
		
		patronage.getPayments().set(paymentIndex, requestBody.toPaymentReceipt());
		Patronage updatedPatronage = patronageRepository.save(patronage);
		
		List<PatronagePaymentReceipt> payments = updatedPatronage.getPayments();
		
		addPatronageReceiptLocationHeader(response, updatedPatronage, paymentIndex);
		return new Gson().toJson(DisplayablePatronagePaymentReceipt.from(payments.get(paymentIndex)));
	}
	
	private void addPatronageReceiptLocationHeader(HttpServletResponse response, Patronage patronage, int paymentIndex) {
		response.addHeader("LOCATION", "/admin/patronages/" + patronage.displayMembershipId() + "/payments/" + paymentIndex);
	}
	
	private static class RawCreatePatronagePaymentReceiptBody {
		public Integer paymentType;
		public String paymentReceiptIdentifier;
		public String paymentDate;
		
		private PatronagePaymentReceipt toPaymentReceipt() {
			return new PatronagePaymentReceipt(PaymentType.values()[paymentType], paymentReceiptIdentifier, paymentDateAsDate());
		}
		
		private Date paymentDateAsDate() {
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(paymentDate);
			} catch (ParseException | NullPointerException e) {
				throw new BadRequestException("Could not parse " + paymentDate + "; please make sure expiration dates are in yyyy-MM-dd format");
			}
		}
	}
}
