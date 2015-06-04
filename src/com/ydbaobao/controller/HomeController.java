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
import org.springframework.web.context.request.WebRequest;

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
	public String home(Model model, WebRequest req) {
		int quantity = 16;
		
		List<Character> firstLetterList = new ArrayList<Character>();
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			firstLetterList.add(ch);	
		}
		model.addAttribute("firstLetterList", firstLetterList);
		
		int index = 0;
		int selectedIndex = 0;
		if (null != req.getParameter("index")) {
			index = Integer.parseInt(req.getParameter("index"));
			selectedIndex = index-1;
			index = (index-1)*quantity;
		}
		model.addAttribute("selectedIndex", selectedIndex);
		model.addAttribute("productList", productsService.readRange(index, quantity));
		
		int start = 0, end = 0;
		int productsCount = productsService.count();
		int range = productsCount/quantity;
		end = range;
		if (productsCount%quantity > 0) {
			range++;
		}
		if (range > 10) {
			start = index/quantity/10*10;
		}
		if (range > start+10) {
			end = start+10;
		}
		if (end == start) {
			end++;
		}
		if (end < range) {
			model.addAttribute("nextBtn", true);
		}
		model.addAttribute("range", IntStream.range(start, end).toArray());
		
		model.addAttribute("categories", categorySevice.read());
		model.addAttribute("brands", brandService.readBrands());
		return "index";
	}
}
