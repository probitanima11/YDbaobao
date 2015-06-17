package com.ydbaobao.controller;

import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.support.JSONResponseUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.PageConfigParam;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;

@Controller
public class ProductController {
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
	
	@RequestMapping(value="/categories/{categoryId}", method=RequestMethod.GET)
	public String load(Model model, @RequestParam("page") String page, @PathVariable int categoryId) {
		PageConfigParam p = new PageConfigParam(adminConfigService.read().getAdminDisplayProducts(), page, categoryService.readByCategoryId(categoryId).getCategoryCount());

		if (p.getEnd() < p.getRange()) {
			model.addAttribute("nextBtn", true);
		}
		model.addAttribute("selectedIndex", p.getSelectedIndex());
		model.addAttribute("range", IntStream.range(p.getStart(), p.getEnd()).toArray());
		model.addAttribute("productList", productService.readListByCategoryId(categoryId, p.getIndex(), p.getQuantity()));
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}
}
