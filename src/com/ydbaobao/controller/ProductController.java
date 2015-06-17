package com.ydbaobao.controller;

import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.model.Brand;
import com.ydbaobao.model.PageConfigParam;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	private ProductService productService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;
	@Resource
	private AdminConfigService adminConfigService;

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public String read(@PathVariable int productId, Model model) {
		model.addAttribute("product", productService.read(productId));
		return "product";
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String loadAll(Model model, @RequestParam("page") String page) {
		logger.debug("page : {}", page);
		model.addAttribute("products", productService.readProducts());
		model.addAttribute("categories", categoryService.read());
		return "products";
	}
	
	@RequestMapping(value="/categories/{categoryId}", method=RequestMethod.GET)
	public String load(Model model, @RequestParam("page") String page, @PathVariable int categoryId) {
		PageConfigParam p = new PageConfigParam(adminConfigService.read().getAdminDisplayProducts(), page, categoryService.readByCategoryId(categoryId).getCategoryCount());

		if (p.getEnd() < p.getRange()) {
			model.addAttribute("nextBtn", true);
		}
		model.addAttribute("selectedIndex", p.getSelectedIndex());
		model.addAttribute("range", IntStream.range(p.getStart(), p.getEnd()).toArray());
		model.addAttribute("products", productService.readListByCategoryId(categoryId, p.getIndex(), p.getQuantity()));
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}
	
	@RequestMapping(value="/categories/{categoryId}/brands/{brandId}", method=RequestMethod.GET)
	public String load(Model model, @RequestParam("page") String page, @PathVariable int categoryId, @PathVariable int brandId) {

		//TODO paging 기능 추가
		
		model.addAttribute("products", productService.readByCategoryIdAndBrandId(categoryId, brandId));
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}
}
