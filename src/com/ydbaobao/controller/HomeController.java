package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;

@Controller
public class HomeController {
	
	@Resource
	private CategoryService categorySevice; 
	@Resource
	private BrandService brandService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("categories", categorySevice.read());
		model.addAttribute("brands", brandService.readBrands());
		return "index";
	}
}
