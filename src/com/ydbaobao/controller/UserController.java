package com.ydbaobao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String form() {
		return "form";
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public String create() {
		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login() {
		return "index";
	}
}
