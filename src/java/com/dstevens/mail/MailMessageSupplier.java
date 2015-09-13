package com.dstevens.mail;

import java.util.function.Supplier;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailMessageSupplier implements Supplier<MailMessage> {

	private final Supplier<Session> sessionSupplier;

	@Autowired
	public MailMessageSupplier(Supplier<Session> sessionSupplier) {
		this.sessionSupplier = sessionSupplier;
	}
	
	public MailMessage get() {
		return new MailMessage(sessionSupplier.get(), MailingExecutorServiceSupplier.CACHED_THREADS.get());
	}
	
}
