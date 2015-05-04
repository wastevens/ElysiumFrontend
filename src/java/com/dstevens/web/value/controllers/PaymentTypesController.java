package com.dstevens.web.value.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dstevens.user.patronage.PaymentType;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import static com.dstevens.collections.Lists.list;

@Controller
public class PaymentTypesController {

	@RequestMapping(value = "/values/payment-types", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getPaymentTypes() {
		List<PaymentType> list = list(PaymentType.values());
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(PaymentType.class, new EnumSerializer());
		return builder.create().toJson(list);
	}
	
	private static class EnumSerializer implements JsonSerializer<Enum<?>> {

		@Override
		public JsonElement serialize(Enum<?> p, Type arg1, JsonSerializationContext arg2) {
			return new JsonPrimitive(p.ordinal());
		}
		
	}
}
