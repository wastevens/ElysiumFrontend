package com.dstevens.user;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dstevens.mail.MailMessage;
import com.dstevens.mail.MailMessageFactory;
import com.dstevens.suppliers.ClockSupplier;
import com.dstevens.user.guards.UserCreationGuard;
import com.dstevens.user.guards.UserInvalidException;
import com.dstevens.user.patronage.Patronage;
import com.dstevens.user.patronage.PatronagePaymentReceipt;
import com.dstevens.user.patronage.PaymentType;

import static com.dstevens.collections.Sets.set;

@Service
public class UserCreator {

	private static final Logger logger = Logger.getLogger(UserCreator.class);
	
	private final UserDao userDao;
	private final MailMessageFactory messageFactory;
	private final ClockSupplier clockSupplier;
	private final Supplier<PasswordEncoder> passwordEncoderSupplier;
	private final List<UserCreationGuard> guards;

	@Autowired
	public UserCreator(UserDao userDao, Supplier<PasswordEncoder> passwordEncoderSupplier, MailMessageFactory messageFactory, ClockSupplier clockSupplier, List<UserCreationGuard> guards) {
		this.userDao = userDao;
		this.passwordEncoderSupplier = passwordEncoderSupplier;
		this.messageFactory = messageFactory;
		this.clockSupplier = clockSupplier;
		this.guards = guards;
	}
	
	public User create(String email, String password, String firstName, String lastName) throws UserInvalidException {
		User user = new User(email, passwordEncoderSupplier.get().encode(password), set(Role.USER)).withFirstName(firstName).withLastName(lastName);
		
		for (UserCreationGuard guard : guards) {
			guard.validate(user);
		}
		
		user = userDao.save(user);
		return user;
	}
	
	public User createUser(String email,
			               String password,
			               String firstName,
			               String lastName,
			               String originalUsername,
			               String paymentReceiptIdentifier) throws UserInvalidException {
		User user = create(email, password, firstName, lastName);
		Instant now = clockSupplier.get().instant();
		if(!StringUtils.isBlank(originalUsername)) {
			Patronage patronage = new Patronage(Year.now(clockSupplier.get()).getValue(), Date.from(now), null);
			patronage = patronage.withOriginalUsername(originalUsername.trim());
			if(!StringUtils.isBlank(paymentReceiptIdentifier)) {
				patronage = patronage.withPayment(new PatronagePaymentReceipt(PaymentType.PAYPAL, new BigDecimal("20.00"), paymentReceiptIdentifier, Date.from(now)));
			}
			user = user.withPatronage(patronage);
		}
		return userDao.save(user);
	}
	
	private void sendAdminEmailFor(User user) {
		StringBuilder body = new StringBuilder();
		body.append(user.getEmail() + " has just created an account on the database.");
		if(user.getPatronage() != null) {
			body.append("\nTheir original username in the old database is " + user.getPatronage().getOriginalUsername());
			if(!user.getPatronage().getPayments().isEmpty()) {
				body.append("\nTheir paypal receipt id for paying for patronage is " + user.getPatronage().getPayments().get(0).getPaymentReceiptIdentifier());
			}
		}
		send(messageFactory.message().
		     from("services@undergroundtheater.org", "UT Database").
		     to("services@undergroundtheater.org").
		     subject("A new Underground Theater User Account has been created: " + user.getEmail()).
		     body(body.toString()));
	}

	private void sendConfirmatoryEmailTo(User user) {
		send(messageFactory.message().
		     from("services@undergroundtheater.org", "UT Database").
		     to(user.getEmail()).
		     subject("Your Underground Theater User Account has been created").
		     body("Thank you for creating an account with Underground Theater's character database, The Green Room!"));
	}
	
	private void send(MailMessage mailMessage) {
		try {
			mailMessage.send();
		} catch(Exception e) {
			logger.error("Failed to send " + mailMessage, e);
		}
	}

	public User create(String email, String patronageYear, String patronageExpiration) throws UserInvalidException {
		User user = create(email, "password", "", "");
		sendConfirmatoryEmailTo(user);
		sendAdminEmailFor(user);
		if(!StringUtils.isBlank(patronageYear) && !StringUtils.isBlank(patronageExpiration)) {
			return userDao.save(user.withPatronage(new Patronage(Integer.parseInt(patronageYear), dateFrom(patronageExpiration), "")));
		}
		return user;
	}
	
	private Date dateFrom(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			throw new IllegalStateException("Expiration date " + date + " was improperly formatted");
		}
	}
}
