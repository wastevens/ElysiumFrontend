package com.dstevens.event;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;

@Controller
public class EventController {

	private final EventRepository eventRepository;

	@Autowired
	public EventController(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public @ResponseBody String getEvents() {
		List<DisplayableEvent> collect = StreamSupport.stream(eventRepository.findAll().spliterator(), false).
				             map((Event t) -> DisplayableEvent.listable(t)).
				             sorted().
				             collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	public @ResponseBody String getEvent(@PathVariable Integer id) {
		Event event = eventRepository.findWithId(id);
		DisplayableEvent listable = DisplayableEvent.from(event);
		return new Gson().toJson(listable);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public @ResponseBody void  addEvent(@RequestBody final DisplayableEvent event) {
		throw new RuntimeException("NYI");
	}
}
