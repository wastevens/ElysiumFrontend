package com.dstevens.web.admin.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/admin/troupes/{id}", method = RequestMethod.DELETE)
	public void deleteTroupe(@PathVariable String id) {
		System.out.println("Troupe id to delete: " + id);
		Troupe troupeToDelete = troupeRepository.findWithId(id);
		System.out.println("Troupe to delete: " + troupeToDelete);
		if(troupeToDelete != null) {
			troupeRepository.delete(troupeToDelete);
			System.out.println("deleted");
		}
	}
	
	private String getTroupes() {
		List<DisplayableTroupe> collect = StreamSupport.stream(troupeRepository.findAllUndeleted().spliterator(), false).
				             map(DisplayableTroupe.fromTroupes()).
				             sorted().
				             collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
}
