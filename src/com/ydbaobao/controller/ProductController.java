package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ydbaobao.dao.StockDao;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;
import com.ydbaobao.service.ProductsService;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Resource
	private ProductService productService;
	@Resource
	private ProductsService productsService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;
	@Resource
	private StockDao stockDao;

	@RequestMapping()
	public String read(@RequestParam int productId, Model model) {
		model.addAttribute("product", productService.read(productId));
		return "product";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Product product) {
		productService.update(product);
		
		logger.debug("재고량 : {}", product.getStockList().toString());
		
		//TODO AJAX로 변경 예정.
		ModelAndView mv = new ModelAndView("admin/productManager");
		mv.addObject("product", new Product());
		//mv.addObject("productList", productsService.readUnclassifiedProducts());
		mv.addObject("productList", productsService.readProducts());
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("categoryList", categoryService.read());
		return mv;
	}
}