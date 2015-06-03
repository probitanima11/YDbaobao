package com.ydbaobao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String load(Model model, @RequestParam String category) {
		//service를 통해 category에 따른 products 리스트를 받아 model에 담는다.
		return "products";
	}
}
