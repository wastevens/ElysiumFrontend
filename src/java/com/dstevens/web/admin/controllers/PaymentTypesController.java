package com.dstevens.web.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dstevens.user.patronage.DisplayablePaymentType;
import com.dstevens.user.patronage.PaymentType;
import com.google.gson.Gson;

import static com.dstevens.collections.Lists.list;

@Controller
public class PaymentTypesController {

	@RequestMapping(value = "/admin/payment-types", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getPaymentTypes() {
		return new Gson().toJson(list(
				new DisplayablePaymentType("Paypal", PaymentType.PAYPAL.ordinal()),
				new DisplayablePaymentType("Award",  PaymentType.AWARD.ordinal()),
				new DisplayablePaymentType("Gift",   PaymentType.GIFT.ordinal()),
				new DisplayablePaymentType("Other",  PaymentType.OTHER.ordinal())
				));
	}
}
