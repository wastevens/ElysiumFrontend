package com.dstevens.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServerConfiguration {

	@Value("${server.host:http://localhost:8080}") private String host;
	
	public String getHost() {
		return host;
	}
	
}
