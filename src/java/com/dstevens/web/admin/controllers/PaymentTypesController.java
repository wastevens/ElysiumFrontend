package com.dstevens.web.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import static com.dstevens.collections.Lists.list;

@Controller
public class PaymentTypesController {

	@RequestMapping(value = "/admin/payment-types", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getPaymentTypes() {
		return new Gson().toJson(list(
				new DisplayablePaymentType("Paypal", 1),
				new DisplayablePaymentType("Award", 2),
				new DisplayablePaymentType("Gift", 3),
				new DisplayablePaymentType("Other", 0)
				));
	}
	
	private static class DisplayablePaymentType {
		public String name;
		public int ordinal;
		
		public DisplayablePaymentType(String name, int ordinal) {
			this.name = name;
			this.ordinal = ordinal;
		}
	}
	
}
