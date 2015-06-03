package com.ydbaobao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static String password = "1111";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		if(session.getAttribute("sessionAdmin") == null) {
			mv.setViewName("admin/adminCheck");
			return mv;
		}
		mv.setViewName("admin/admin");
		return mv;
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView check() {
		ModelAndView mv = new ModelAndView("admin/adminCheck");
		return mv;
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ModelAndView checkForm(@RequestParam String adminPassword, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(adminPassword.equals(password)) {
			session.setAttribute("sessionAdmin", adminPassword);
			mv.setViewName("admin/admin");
			return mv;
		}
		mv.setViewName("admin/adminCheck");
		return mv;
	}
}
