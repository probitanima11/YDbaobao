package com.ydbaobao.controller;

import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.CommonUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.SessionCustomer;
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
	public String read(@PathVariable int productId, Model model, HttpSession session) {
		model.addAttribute("customer", (SessionCustomer)session.getAttribute("sessionCustomer"));
		model.addAttribute("product", productService.read(productId));
		return "product";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String loadAll(Model model, @RequestParam("page") String page) {
		logger.debug("page : {}", page);
		model.addAttribute("products", productService.readProducts());
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		return "products";
	}
	
	@RequestMapping(value="/categories/{categoryId}/products", method=RequestMethod.GET)
	public String load(Model model, @RequestParam("page") int page, @PathVariable int categoryId) {
		Category category = categoryService.readByCategoryId(categoryId);
		
		int totalPage = category.getCategoryCount() / CommonUtil.PRODUCTS_PER_PAGE + 1;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("products", productService.readListByCategoryId(categoryId, page, CommonUtil.PRODUCTS_PER_PAGE));
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("category", category);
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}
	
	@RequestMapping(value="/categories/{categoryId}/brands/{brandId}/products", method=RequestMethod.GET)
	public String load(Model model, @RequestParam("page") String page, @PathVariable int categoryId, @PathVariable int brandId) {

		//TODO paging 기능 추가
		
		model.addAttribute("products", productService.readByCategoryIdAndBrandId(categoryId, brandId));
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}
}
