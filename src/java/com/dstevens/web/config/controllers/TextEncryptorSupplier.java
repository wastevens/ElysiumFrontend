package com.dstevens.web.config.controllers;

import java.util.function.Supplier;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class TextEncryptorSupplier implements Supplier<TextEncryptor> {

	@Override
	public TextEncryptor get() {
		return Encryptors.noOpText();
	}

}
