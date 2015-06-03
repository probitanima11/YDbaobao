package com.ydbaobao.controller;

import java.util.ArrayList;
import java.util.List;

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
		List<Character> firstLetterList = new ArrayList<Character>();
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			firstLetterList.add(ch);	
		}
		model.addAttribute("categories", categorySevice.read());
		model.addAttribute("firstLetterList", firstLetterList);
		model.addAttribute("brands", brandService.readBrands());
		return "index";
	}
}
