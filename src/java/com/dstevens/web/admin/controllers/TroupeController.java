package com.dstevens.web.admin.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.players.Setting;
import com.dstevens.players.TroupeRepository;
import com.google.gson.Gson;

@Controller
public class TroupeController {

	private final TroupeRepository troupeRepository;

	@Autowired
	public TroupeController(TroupeRepository troupeRepository) {
		this.troupeRepository = troupeRepository;
	}
	
	@RequestMapping(value = "/admin/troupes", method = RequestMethod.GET)
	public ModelAndView getTroupesPage() {
		ModelAndView modelAndView = new ModelAndView("/admin/troupes");
		modelAndView.addObject("troupes", getTroupes());
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/troupes", method = RequestMethod.POST)
	public ModelAndView addTroupe(@RequestParam(value = "name") String name, @RequestParam(value = "setting") int setting) {
		troupeRepository.ensureExists(name, Setting.values()[setting]);
		return getTroupesPage();
	}
	
	private String getTroupes() {
		List<DisplayableTroupe> collect = StreamSupport.stream(troupeRepository.findAllUndeleted().spliterator(), false).
				             map(DisplayableTroupe.fromTroupes()).
				             sorted().
				             collect(Collectors.toList());
		String jsonized = new Gson().toJson(collect);
		System.out.println(collect);
		System.out.println(jsonized);
		return jsonized;
	}
	
}
