package com.ironshutter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController {

	@RequestMapping(value={""})
	public String root(Model model) {
		return "index.html";
	}
	
}
