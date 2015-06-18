package com.ydbaobao.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.CommonUtil;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;

@Controller
@RequestMapping("/search")
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;
	@Resource
	private AdminConfigService adminConfigService;

	@RequestMapping(value = "/brands", method = RequestMethod.GET)
	public String searchBrand(Model model, @RequestParam String brandName, @RequestParam int page) {
		Pattern pt = Pattern.compile("^\\s{1,}|\\s{1,}$");
		Matcher m = pt.matcher(brandName);
		String query = m.replaceAll("").replaceAll(" ", "|");
		
		if (query.length() < 1) {
			model.addAttribute("count", 0);
			return "search";
		}
		int count = productService.countBySearchBrandName(query);
		List<Product> products= productService.readByBrandName(query, page, CommonUtil.PRODUCTS_PER_PAGE);

		model.addAttribute("totalPage", CommonUtil.countTotalPage(count));
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("products", products);
		model.addAttribute("count", count);
		model.addAttribute("terms", brandName);
		model.addAttribute("query", query);
		return "search";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String search(Model model, @RequestParam String productName, @RequestParam int page) {
		Pattern pt = Pattern.compile("^\\s{1,}|\\s{1,}$");
		Matcher m = pt.matcher(productName);
		String query = m.replaceAll("").replaceAll(" ", "|");
		
		if (query.length() < 1) {
			model.addAttribute("count", 0);
			return "search";
		}
		int count = productService.countBySearchProductName(query);
		List<Product> products= productService.readByProductName(query, page, CommonUtil.PRODUCTS_PER_PAGE);

		model.addAttribute("totalPage", CommonUtil.countTotalPage(count));
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("products", products);
		model.addAttribute("count", count);
		model.addAttribute("terms", productName);
		model.addAttribute("query", query);
		return "search";
	}

}
