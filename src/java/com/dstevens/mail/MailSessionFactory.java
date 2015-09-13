package com.dstevens.mail;

import java.util.function.Supplier;

import javax.mail.Session;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class MailSessionFactory implements Supplier<Session> {

	@Value("${mail.enabled:false}") private boolean enabled;
	@Value("${mail.host:smtp.gmail.com}") private String smtpHost;
	@Value("${mail.port}") private Integer port;
	@Value("${mail.user:user@gmail.com}") private String user;
	@Value("${mail.password:password}") private String password;
	
	private final Supplier<TextEncryptor> textEncryptor;
	
	@Autowired
	public MailSessionFactory(Supplier<TextEncryptor> textEncryptor) {
		this.textEncryptor = textEncryptor;
	}
	
	public Session get() {
		return new MailSessionBuilder().enabled(enabled).
				                        withSMTP(smtpHost).
								        withPort(port).
								        withSocketFactoryClass(SSLSocketFactory.class).
								        withUsername(user).
								        withPassword(textEncryptor.get().decrypt(password)).
								        build().
								        get();
	}
	
}
