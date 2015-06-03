package com.ydbaobao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
	@RequestMapping(method = RequestMethod.GET)
	public String form(@RequestParam String productId, Model model) {
		return "product";
	}
}
