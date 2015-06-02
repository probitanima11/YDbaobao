package com.ydbaobao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static String password = "1111";
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(HttpSession session) {
		if(session.getAttribute("sessionAdmin") == null) return "adminCheck";
		return "admin";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String check() {
		return "adminCheck";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String checkForm(@RequestParam String adminPassword, HttpSession session) {
		if(adminPassword.equals(password)) {
			session.setAttribute("sessionAdmin", adminPassword);
			return "admin";
		}
		return "adminCheck";
	}
}
