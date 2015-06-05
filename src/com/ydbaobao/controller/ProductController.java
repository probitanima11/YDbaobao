package com.ydbaobao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.dao.StockDao;
import com.ydbaobao.model.Stock;
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
		List<Stock> stockOfProduct = stockDao.readListByProductId(productId);
		model.addAttribute("stockOfProduct", stockOfProduct);
		model.addAttribute("product", productService.read(productId));
		return "product";
	}
}
