package com.dstevens.mail;

import javax.mail.Session;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailSessionFactory {

	@Value("${mail.enabled:false}") private boolean enabled;
	@Value("${mail.host:smtp.gmail.com}") private String smtpHost;
	@Value("${mail.port}") private Integer port;
	@Value("${mail.user:user@gmail.com}") private String user;
	@Value("${mail.password:password}") private String password;
	
	public Session createSession() {
		return new MailSessionBuilder().withSMTP(smtpHost).
								        withPort(port).
								        withSocketFactoryClass(SSLSocketFactory.class).
								        withUsername(user).
								        withPassword(password).
								        build().
								        get();
	}
	
}
