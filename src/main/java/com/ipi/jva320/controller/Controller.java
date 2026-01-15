package com.ipi.jva320.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipi.jva320.service.SalarieAideADomicileService;

@org.springframework.stereotype.Controller
@RequestMapping (value = "")
public class Controller {
	@Autowired
	private SalarieAideADomicileService salarieAideADomicileService;
	@GetMapping(value = "/")
	public String home(final ModelMap model) {
		String nbSalaries = salarieAideADomicileService.countSalaries().toString();
		model.put("nbSalaries", nbSalaries);
		return "home";
	}
	
}
