package com.dstevens.web.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dstevens.troupe.DisplayableTroupe;
import com.dstevens.troupe.Troupe;
import com.dstevens.troupe.TroupeRepository;
import com.google.gson.Gson;

@Controller
public class TroupePageController {

	private final TroupeRepository troupeRepository;

	@Autowired
	public TroupePageController(TroupeRepository troupeRepository) {
		this.troupeRepository = troupeRepository;
	}
	
	@RequestMapping(value = "/admin/page/troupes", method = RequestMethod.GET)
	public ModelAndView getTroupesPage() {
		return new ModelAndView("/admin/troupe/troupes");
	}
	
	@RequestMapping(value = "/admin/page/troupes/{id}", method = RequestMethod.GET)
	public ModelAndView getManageTroupePage(@PathVariable Integer id) {
		Troupe troupe = troupeRepository.findWithId(id);
		ModelAndView modelAndView = new ModelAndView("/admin/troupe/manage");
		modelAndView.addObject("troupe", new Gson().toJson(DisplayableTroupe.from(troupe)));
		return modelAndView;
	}
}
