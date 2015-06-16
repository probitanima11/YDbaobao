package com.ydbaobao.admincontroller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydbaobao.model.Product;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
	@Resource
	private ProductService productService;
	@Resource
	private BrandService brandService;
	@Resource
	private CategoryService categoryService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("productList", productService.readProducts());
		model.addAttribute("brandList", brandService.readBrands());
		model.addAttribute("categoryList", categoryService.read());
		return "admin/productManager";
	}
}
