package com.dstevens.user;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

import static com.dstevens.collections.Lists.list;

import static com.dstevens.collections.Sets.set;

@Service
public class UserCreator {

	private static final Logger logger = Logger.getLogger(UserCreator.class);
	
	private final UserDao userDao;
	private final MailMessageFactory messageFactory;
	private final ClockSupplier clockSupplier;
	private final Supplier<PasswordEncoder> passwordEncoderSupplier;
	private final List<UserCreationGuard> guards;
	private final ExecutorService executorService;

	@Autowired
	public UserCreator(UserDao userDao, Supplier<PasswordEncoder> passwordEncoderSupplier, MailMessageFactory messageFactory, ClockSupplier clockSupplier, List<UserCreationGuard> guards) {
		this.userDao = userDao;
		this.passwordEncoderSupplier = passwordEncoderSupplier;
		this.messageFactory = messageFactory;
		this.clockSupplier = clockSupplier;
		this.guards = guards;
		this.executorService = Executors.newFixedThreadPool(10);
	}
	
	public User create(String email, String password, String firstName, String lastName) throws UserInvalidException {
		User user = new User(email, passwordEncoderSupplier.get().encode(password), set(Role.USER)).withFirstName(firstName).withLastName(lastName);
		
		for (UserCreationGuard guard : guards) {
			guard.validate(user);
		}
		
		return saveAndSendEmailsFor(user);
	}
	
	public User createUser(String email,
			               String password,
			               String firstName,
			               String lastName,
			               String originalUsername,
			               String paymentReceiptIdentifier) throws UserInvalidException {
		User user = create(email, password, firstName, lastName);
		if(!StringUtils.isBlank(originalUsername)) {
			Clock clock = clockSupplier.get();
			Patronage patronage = new Patronage(Year.now(clock).getValue(), Date.from(clock.instant()), null);
			patronage = patronage.withOriginalUsername(originalUsername.trim());
			if(!StringUtils.isBlank(paymentReceiptIdentifier)) {
				patronage = patronage.withPayment(new PatronagePaymentReceipt(PaymentType.PAYPAL, new BigDecimal("20.00"), paymentReceiptIdentifier, Date.from(clock.instant())));
			}
			user = user.withPatronage(patronage);
		}
		return saveAndSendEmailsFor(user);
	}

	public User create(String email, String patronageYear, String patronageExpiration) throws UserInvalidException {
		User user = create(email, "password", "", "");
		if(!StringUtils.isBlank(patronageYear) && !StringUtils.isBlank(patronageExpiration)) {
			user = userDao.save(user.withPatronage(new Patronage(Integer.parseInt(patronageYear), dateFrom(patronageExpiration), "")));
		}
		return saveAndSendEmailsFor(user);
	}
	
	private Date dateFrom(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			throw new IllegalStateException("Expiration date " + date + " was improperly formatted");
		}
	}
	
	private User saveAndSendEmailsFor(User user) {
		user = userDao.save(user);
		sendConfirmatoryEmailTo(user);
		sendAdminEmailFor(user);
		return user;
	}
	
	private void sendAdminEmailFor(User user) {
		List<String> lines = list(user.getEmail() + " has just created an account on the database.");
		if(user.getPatronage() != null) {
			lines.add("Their membership id is " + user.getPatronage().displayMembershipId());
			if(!StringUtils.isBlank(user.getPatronage().getOriginalUsername())) {
				lines.add("\nTheir original username in the old database is " + user.getPatronage().getOriginalUsername());
			}
			if(!user.getPatronage().getPayments().isEmpty()) {
				lines.add("\nTheir paypal receipt id for paying for patronage is " + user.getPatronage().getPayments().get(0).getPaymentReceiptIdentifier());
			}
		}
		send(messageFactory.message().
		     from("services@undergroundtheater.org", "UT Database").
		     to("services@undergroundtheater.org").
		     subject("A new Underground Theater User Account has been created: " + user.getEmail()).
		     body(StringUtils.join(lines, "\n")));
	}

	private void sendConfirmatoryEmailTo(User user) {
		List<String> lines = list("Thank you for creating an account with Underground Theater's character database, The Green Room!");
		if(user.getPatronage() != null) {
			lines.add("Your membership id is " + user.getPatronage().displayMembershipId());
		}
		send(messageFactory.message().
		     from("services@undergroundtheater.org", "UT Database").
		     to(user.getEmail()).
		     subject("Your Underground Theater User Account has been created").
		     body(StringUtils.join(lines, "\n")));
	}
	
	private void send(MailMessage mailMessage) {
		executorService.execute(() -> {
				try {
					mailMessage.send();
				} catch(Exception e) {
					logger.error("Failed to send " + mailMessage, e);
				}
			}
		);
	}
}
