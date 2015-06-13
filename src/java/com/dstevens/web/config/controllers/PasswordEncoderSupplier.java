package com.dstevens.web.config.controllers;

import java.util.function.Supplier;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderSupplier implements Supplier<PasswordEncoder> {

	@Override
	public PasswordEncoder get() {
		return new StandardPasswordEncoder();
	}

}
