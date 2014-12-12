package com.dstevens.mail;

import java.util.Properties;

import org.springframework.stereotype.Service;

import javax.mail.Session;

@Service
public class MailSessionFactory {

	public Session session() {
		return Session.getDefaultInstance(new Properties());
	}
	
}
