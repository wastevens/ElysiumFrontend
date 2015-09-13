package com.dstevens.user;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
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
import com.dstevens.user.guards.UserCreationGuard;
import com.dstevens.user.guards.UserInvalidException;
import com.dstevens.user.patronage.Patronage;
import com.dstevens.user.patronage.PatronagePaymentReceipt;
import com.dstevens.user.patronage.PaymentType;

import static com.dstevens.collections.Lists.list;

@Service
public class UserCreator {

	private static final Logger logger = Logger.getLogger(UserCreator.class);
	
	private final UserRepository userRepository;
	private final Supplier<MailMessage> messageFactory;
	private final Supplier<Clock> clockSupplier;

	@Autowired
	public UserCreator(UserRepository userRepository, Supplier<PasswordEncoder> passwordEncoderSupplier, Supplier<MailMessage> mailMessageSupplier, Supplier<Clock> clockSupplier, List<UserCreationGuard> guards) {
		this.userRepository = userRepository;
		this.messageFactory = mailMessageSupplier;
		this.clockSupplier = clockSupplier;
	}
	
	public User create(String email, String password, String firstName, String lastName) throws UserInvalidException {
		logger.debug(MessageFormat.format("Creating user {0}, {1}, {2}, {3}", email, "<password goes here>", firstName, lastName));
		User user = userRepository.create(email, password, firstName, lastName);
		return sendEmailsFor(user);
	}
	
	public User createUser(String email, String password, String firstName, String lastName, String originalUsername, String paymentReceiptIdentifier) throws UserInvalidException {
		logger.debug(MessageFormat.format("Creating user {0}, {1}, {2}, {3}, {4}, {5}", email, "<password goes here>", firstName, lastName, originalUsername, paymentReceiptIdentifier));
		User user = userRepository.create(email, password, firstName, lastName);
		user = addOptionalPatronageFor(user, originalUsername, paymentReceiptIdentifier);
		return sendEmailsFor(user);
	}

	private User addOptionalPatronageFor(User user, String originalUsername, String paymentReceiptIdentifier) {
		if(!StringUtils.isBlank(originalUsername)) {
			Clock clock = clockSupplier.get();
			Patronage patronage = new Patronage(Year.now(clock).getValue(), Date.from(clock.instant()), null);
			patronage = patronage.withOriginalUsername(originalUsername.trim());
			if(!StringUtils.isBlank(paymentReceiptIdentifier)) {
				patronage = patronage.withPayment(new PatronagePaymentReceipt(PaymentType.PAYPAL, new BigDecimal("20.00"), paymentReceiptIdentifier, Date.from(clock.instant())));
			}
			return userRepository.save(user.withPatronage(patronage));
		}
		return user;
	}

	public User create(String email, String patronageYear, String patronageExpiration) throws UserInvalidException {
		logger.debug(MessageFormat.format("Creating user {0}, {1}, {2}", email, patronageYear, patronageExpiration));
		User user = userRepository.create(email, "password", "", "");
		if(!StringUtils.isBlank(patronageYear) && !StringUtils.isBlank(patronageExpiration)) {
			user = userRepository.save(user.withPatronage(new Patronage(Integer.parseInt(patronageYear), dateFrom(patronageExpiration), "")));
		}
		return sendEmailsFor(user);
	}
	
	private Date dateFrom(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			throw new IllegalStateException("Expiration date " + date + " was improperly formatted");
		}
	}
	
	private User sendEmailsFor(User user) {
		sendConfirmatoryEmailTo(user);
		sendAdminEmailFor(user);
		return user;
	}
	
	private void sendAdminEmailFor(User user) {
		List<String> lines = list(user.getEmail() + " has just created an account on the database.");
		if(user.getPatronage() != null) {
			lines.add("Their membership id is " + user.getPatronage().displayMembershipId());
			if(!StringUtils.isBlank(user.getPatronage().getOriginalUsername())) {
				lines.add("Their original username in the old database is " + user.getPatronage().getOriginalUsername());
			}
			if(!user.getPatronage().getPayments().isEmpty()) {
				lines.add("Their paypal receipt id for paying for patronage is " + user.getPatronage().getPayments().get(0).getPaymentReceiptIdentifier());
			}
		}
		messageFactory.get().
		               from("services@undergroundtheater.org", "UT Database").
		               to("services@undergroundtheater.org").
		               subject("A new Underground Theater User Account has been created: " + user.getEmail()).
		               body(StringUtils.join(lines, "\n")).
		               send();
	}

	private void sendConfirmatoryEmailTo(User user) {
		List<String> lines = list("Thank you for creating an account with Underground Theater's character database, The Green Room!");
		if(user.getPatronage() != null) {
			lines.add("Your membership id is " + user.getPatronage().displayMembershipId());
		}
		messageFactory.get().
		               from("services@undergroundtheater.org", "UT Database").
		               to(user.getEmail()).
		               subject("Your Underground Theater User Account has been created").
		               body(StringUtils.join(lines, "\n")).
		               send();
	}
}
