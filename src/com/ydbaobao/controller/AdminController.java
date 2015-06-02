package com.ydbaobao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "admin";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String checkForm() {
		return "adminCheck";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check() {
		return "admin";
	}
}
