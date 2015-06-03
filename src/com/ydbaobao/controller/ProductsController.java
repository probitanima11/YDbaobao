package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductsService;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@Resource
	private CategoryService categoryService;
	
	@Resource
	private ProductsService productsService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String load(Model model, @RequestParam int categoryId) {
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("productList", productsService.readListByCategory(categoryId));
		return "products";
	}
}
