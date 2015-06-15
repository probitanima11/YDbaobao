package com.ydbaobao.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.ydbaobao.model.PageConfigParam;
import com.ydbaobao.model.Product;
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String search(Model model, WebRequest req, @RequestParam String terms, @RequestParam String select) {
		model.addAttribute("categories", categoryService.read());
		
		Pattern pt = Pattern.compile("^\\s{1,}|\\s{1,}$");
		Matcher m = pt.matcher(terms);
		String query = m.replaceAll("").replaceAll(" ", "|");
		if (query.length() <1) {
			model.addAttribute("count", 0);
			return "search";
		}
		int count = 0;
		String selectOption = "상품명";
		List<Product> products= null;
		PageConfigParam p = null;
		if (select.equals("product-name")) {
			count = productService.countBySearchProductName(query);
			p = new PageConfigParam(16, req.getParameter("index"), count);
			products = productService.readByProductName(query, p.getIndex(), p.getQuantity());
		}
		if (select.equals("brand-name")) {
			selectOption="브랜드명";
			count = productService.countBySearchBrandName(query);
			p = new PageConfigParam(16, req.getParameter("index"), count);
			products = productService.readByBrandName(query, p.getIndex(), p.getQuantity());
		}
		if (p.getEnd() < p.getRange()) {
			model.addAttribute("nextBtn", true);
		}
		model.addAttribute("productList", products);
		model.addAttribute("count", count);
		model.addAttribute("selectedIndex", p.getSelectedIndex());
		model.addAttribute("range", IntStream.range(p.getStart(), p.getEnd()).toArray());
		model.addAttribute("selected", selectOption);
		model.addAttribute("select", select);
		model.addAttribute("terms", terms);
		model.addAttribute("query", query);
		return "search";
	}

}
