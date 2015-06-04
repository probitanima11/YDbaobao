package com.ydbaobao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductsService;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource
	private CategoryService categorySevice; 
	@Resource
	private BrandService brandService;
	@Resource
	private ProductsService productsService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Character> firstLetterList = new ArrayList<Character>();
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			firstLetterList.add(ch);	
		}
		
		model.addAttribute("categories", categorySevice.read());
		model.addAttribute("firstLetterList", firstLetterList);
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("productList", productsService.readRange(0, 16));
		model.addAttribute("range", IntStream.range(0, productsService.count()).toArray());
		return "index";
	}
}
