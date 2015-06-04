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
		List<Character> firstLetterList = new ArrayList<Character>();
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			firstLetterList.add(ch);	
		}
		
		int index = 0;
		if (null != req.getParameter("index")) {
			index = Integer.parseInt(req.getParameter("index"));
			index = (index-1)*16;
		}
		
		model.addAttribute("categories", categorySevice.read());
		model.addAttribute("firstLetterList", firstLetterList);
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("productList", productsService.readRange(index, 16));
		int productsCount = productsService.count();
		int range = productsCount/16;
		if (productsCount%16 > 0) {
			range++;
		}
		model.addAttribute("range", IntStream.range(0, range).toArray());
		return "index";
	}
}
