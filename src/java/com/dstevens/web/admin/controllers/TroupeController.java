package com.dstevens.web.admin.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.players.Setting;
import com.dstevens.players.Troupe;
import com.dstevens.players.TroupeRepository;
import com.google.gson.Gson;

@Controller
public class TroupeController {

	private final TroupeRepository troupeRepository;

	@Autowired
	public TroupeController(TroupeRepository troupeRepository) {
		this.troupeRepository = troupeRepository;
	}
	
	@RequestMapping(value = "/admin/page/troupes", method = RequestMethod.GET)
	public ModelAndView getTroupesPage() {
		return new ModelAndView("/admin/troupes");
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(value = "/admin/troupes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void  addTroupe(@RequestBody final RawTroupe troupe) {
		if(troupe.name != null && troupe.setting != null) {
			troupeRepository.ensureExists(troupe.name, Setting.values()[troupe.setting]);
		}
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/admin/troupes/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteTroupe(@PathVariable String id) {
		Troupe troupeToDelete = troupeRepository.findWithId(id);
		if(troupeToDelete != null) {
			troupeRepository.delete(troupeToDelete);
		}
	}
	
	@RequestMapping(value = "/admin/troupes", method = RequestMethod.GET)
	public @ResponseBody String getTroupes() {
		List<DisplayableTroupe> collect = StreamSupport.stream(troupeRepository.findAllUndeleted().spliterator(), false).
				             map(DisplayableTroupe.fromTroupes()).
				             sorted().
				             collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	private static class RawTroupe {

		public String name;
		public Integer setting;
		
	}
}
