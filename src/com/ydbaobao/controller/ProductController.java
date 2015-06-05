package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.dao.StockDao;
import com.ydbaobao.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Resource
	private ProductService productService;
	@Resource
	private StockDao stockDao;

	@RequestMapping()
	public String read(@RequestParam int productId, Model model) {
		model.addAttribute("stockOfProduct", stockDao.readListByProductId(productId));
		model.addAttribute("product", productService.read(productId));
		return "product";
	}
}
