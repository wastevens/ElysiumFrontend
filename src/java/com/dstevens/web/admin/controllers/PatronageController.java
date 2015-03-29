package com.dstevens.web.admin.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.dstevens.config.exceptions.BadRequestException;
import com.dstevens.config.exceptions.ResourceNotFoundException;
import com.dstevens.users.User;
import com.dstevens.users.UserRepository;
import com.dstevens.users.patronages.DisplayablePatronage;
import com.dstevens.users.patronages.DisplayablePatronagePaymentReceipt;
import com.dstevens.users.patronages.Patronage;
import com.dstevens.users.patronages.PatronagePaymentReceipt;
import com.dstevens.users.patronages.PatronageRepository;
import com.dstevens.users.patronages.PaymentType;
import com.google.gson.Gson;

@Controller
public class PatronageController {

	private final PatronageRepository patronageRepository;
	private final UserRepository userRepository;

	@Autowired
	public PatronageController(PatronageRepository patronageRepository, UserRepository userRepository) {
		this.patronageRepository = patronageRepository;
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/admin/patronages", method = RequestMethod.GET)
	public @ResponseBody String getPatronages() {
		List<DisplayablePatronage> collect = StreamSupport.stream(patronageRepository.findAllUndeleted().spliterator(), false).
                map((Patronage t) -> DisplayablePatronage.from(t)).
                sorted().
                collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages/{id}", method = RequestMethod.GET)
	public @ResponseBody String getPatronage(@PathVariable String id) {
		return new Gson().toJson(DisplayablePatronage.from(findPatronage(id)));
	}
	
	private Patronage findPatronage(String id) {
		Patronage patronage = patronageRepository.findPatronageByMembershipId(id);
		if(patronage == null) {
			throw new ResourceNotFoundException("Did not find patronage with membership id " + id);
		}
		return patronage;
	}
	
	@RequestMapping(value = "/admin/patronages/unassigned", method = RequestMethod.GET)
	public @ResponseBody String getUnassignedPatronages() {
		List<DisplayablePatronage> collect = StreamSupport.stream(patronageRepository.findAllUndeleted().spliterator(), false).
				filter((Patronage t) -> t.getUser() == null).
				map((Patronage t) -> DisplayablePatronage.from(t)).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages/assigned", method = RequestMethod.GET)
	public @ResponseBody String getAssignedPatronages() {
		List<DisplayablePatronage> collect = StreamSupport.stream(patronageRepository.findAllUndeleted().spliterator(), false).
				filter((Patronage t) -> t.getUser() != null).
				map((Patronage t) -> DisplayablePatronage.from(t)).
				sorted().
				collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/admin/patronages/{id}/payments", method = RequestMethod.GET)
	public @ResponseBody String getPatronagePayments(@PathVariable String id) {
		return new Gson().toJson(findPatronage(id).getPayments().stream().map((PatronagePaymentReceipt t) -> DisplayablePatronagePaymentReceipt.from(t)).collect(Collectors.toList()));
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
	
	@RequestMapping(value = "/admin/patronages", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody String createPatronage(@RequestBody RawCreatePatronageBody requestBody, HttpServletResponse response) {
		User user = userRepository.findUser(requestBody.userId);
		if(user != null && user.getPatronage() != null) {
			throw new BadRequestException("User " + requestBody.userId + " already has a patronage.");
		}
		Patronage patronage = patronageRepository.save(requestBody.toPatronage());
		if(user != null) {
			patronage = patronage.forUser(user);
			user = user.withPatronage(patronage);
			patronage = patronageRepository.save(patronage);
		}
		addPatronageLocationHeader(response, patronage);
		return new Gson().toJson(DisplayablePatronage.from(patronage));
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
	
	@RequestMapping(value = "/admin/patronages/{id}", method = RequestMethod.PUT)
	public @ResponseBody String updatePatronage(@PathVariable String id, @RequestBody RawCreatePatronageBody requestBody, HttpServletResponse response) {
		Patronage patronage = findPatronage(id);
		if(patronage == null) {
			throw new ResourceNotFoundException("No patronage " + id + " found");
		}
		User user = userRepository.findUser(requestBody.userId);
		if(user != null && user.getPatronage() != null && !user.getPatronage().equals(patronage)) {
			throw new BadRequestException("Patronage " + user.getPatronage().displayMembershipId() + " is associated with user " + user.getId());
		}
		Patronage updatedPatronage = patronageRepository.save(requestBody.toPatronage().forUser(user));
		addPatronageLocationHeader(response, updatedPatronage);
		return new Gson().toJson(DisplayablePatronage.from(updatedPatronage));
	}

	private void addPatronageLocationHeader(HttpServletResponse response, Patronage patronage) {
		response.addHeader("LOCATION", "/admin/patronages/" + patronage.displayMembershipId());
	}
	
	private void addPatronageReceiptLocationHeader(HttpServletResponse response, Patronage patronage, int paymentIndex) {
		response.addHeader("LOCATION", "/admin/patronages/" + patronage.displayMembershipId() + "/payments/" + paymentIndex);
	}
	
	private static class RawCreatePatronageBody {
		public Integer year;
		public String expiration;
		public Integer userId;

		private Patronage toPatronage() {
			return new Patronage(year, expirationAsDate());
		}
		
		private Date expirationAsDate() {
			try {
				return new SimpleDateFormat("yyyy-MM").parse(expiration);
			} catch (ParseException e) {
				throw new BadRequestException("Could not parse " + expiration + "; please make sure expiration dates are in yyyy-MM format");
			}
		}
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
			} catch (ParseException e) {
				throw new BadRequestException("Could not parse " + paymentDate + "; please make sure expiration dates are in yyyy-MM-dd format");
			}
		}
	}
}
