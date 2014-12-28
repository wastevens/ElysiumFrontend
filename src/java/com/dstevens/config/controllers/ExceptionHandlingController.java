package com.dstevens.config.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dstevens.utilities.ObjectExtensions;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlingController {

	@ExceptionHandler(Exception.class)
	public void handleError(HttpServletRequest req, Exception exception) {
		System.out.println("Request: " + req.getRequestURL() + " raised " + exception);
		System.out.println(ObjectExtensions.toStringFor(req));
	}
	
}
