package com.dstevens.mail;

import org.springframework.stereotype.Service;

@Service
public class MailMessageFactory {

	private final MailSessionFactory sessionFactory;

	public MailMessageFactory(MailSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public MailMessage message() {
		return new MailMessage(sessionFactory.createSession());
	}
	
}
