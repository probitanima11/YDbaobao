package com.ydbaobao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydbaobao.model.TestThymeleaf;

@Controller
@RequestMapping("/thymeleaf")
public class TestController {
	
	@RequestMapping("")
	public String test(Model model) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		model.addAttribute("list", list);
		return "thymeleaf";
	}
	
	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("TestThymeleaf", new TestThymeleaf());
		return "thymeform";
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public void test (TestThymeleaf tt) {
		System.out.println(tt.getId());
		System.out.println(tt.getName());
	}
}
