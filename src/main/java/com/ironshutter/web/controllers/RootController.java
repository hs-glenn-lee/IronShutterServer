package com.ironshutter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

	@RequestMapping(value={""})
	public String root(Model model) {
		return "index.html";
	}
	
}
