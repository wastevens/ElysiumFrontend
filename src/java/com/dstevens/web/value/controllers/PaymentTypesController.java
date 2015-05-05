package com.dstevens.web.value.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dstevens.suppliers.GsonSupplier;
import com.dstevens.user.patronage.PaymentType;
import com.google.gson.Gson;

import static com.dstevens.collections.Lists.list;

@Controller
public class PaymentTypesController {

	private final GsonSupplier gsonSupplier;

	@Autowired
	public PaymentTypesController(GsonSupplier gsonSupplier) {
		this.gsonSupplier = gsonSupplier;
	}
	
	@RequestMapping(value = "/values/payment-types", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getPaymentTypes() {
		List<PaymentType> list = list(PaymentType.values());
		Gson create = gsonSupplier.get();
		return create.toJson(list);
	}
}
