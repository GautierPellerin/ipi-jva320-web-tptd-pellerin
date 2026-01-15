package com.ipi.jva320.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipi.jva320.service.SalarieAideADomicileService;
@org.springframework.stereotype.Controller
public class listController {

	@Autowired
	private SalarieAideADomicileService salarieAideADomicileService;
	//@GetMapping(value = "/salaries")
	//public String list(final ModelMap model) {
	//	model.put("salaries", salarieAideADomicileService.getSalaries());
	//	return "list";
	//}
}
