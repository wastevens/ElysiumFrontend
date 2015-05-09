package com.dstevens.web.value.controllers;

import java.util.stream.Collectors;

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

	@RequestMapping(value = "/values/payment-types", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getPaymentTypes() {
		return new Gson().toJson(list(PaymentType.values()).stream().map((PaymentType t) -> DisplayablePaymentType.from(t)).collect(Collectors.toList()));
	}
}
