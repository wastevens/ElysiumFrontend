package com.dstevens.web.admin.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		ModelAndView modelAndView = new ModelAndView("/admin/troupes");
		return modelAndView;
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(value = "/admin/troupes", method = RequestMethod.POST)
	public @ResponseBody void  addTroupe(@RequestParam(value = "name", required=false) String name, @RequestParam(value = "setting", required=false) Integer setting) {
		System.out.println("setting is int, name: " + name +", " + "setting: " + setting);
		if(name != null && setting != null) {
			troupeRepository.ensureExists(name, Setting.values()[setting]);
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
		System.out.println("Getting troupes");
		List<DisplayableTroupe> collect = StreamSupport.stream(troupeRepository.findAllUndeleted().spliterator(), false).
				             map(DisplayableTroupe.fromTroupes()).
				             sorted().
				             collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
}
