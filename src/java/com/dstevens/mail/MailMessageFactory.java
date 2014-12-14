package com.dstevens.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailMessageFactory {

	private final MailSessionFactory sessionFactory;

	@Autowired
	public MailMessageFactory(MailSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public MailMessage message() {
		return new MailMessage(sessionFactory.session());
	}
	
}
