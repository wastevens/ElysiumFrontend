package com.dstevens.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElysiumMessageFactory {

	private final MailSessionFactory sessionFactory;

	@Autowired
	public ElysiumMessageFactory(MailSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ElysiumMessage message() {
		return new ElysiumMessage(sessionFactory.session());
	}
	
}
