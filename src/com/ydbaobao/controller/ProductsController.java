package com.ydbaobao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ydbaobao.model.Product;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductsService;

@Controller
@RequestMapping("/products")
public class ProductsController {
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;
	@Resource
	private ProductsService productsService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String load(Model model, @RequestParam int categoryId) {
		List<Character> firstLetterList = new ArrayList<Character>();
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			firstLetterList.add(ch);	
		}
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("productList", productsService.readListByCategoryId(categoryId));
		model.addAttribute("firstLetterList", firstLetterList);
		model.addAttribute("count", productsService.countByCategoryId(categoryId));
		int productsCount = productsService.countByCategoryId(categoryId);
		int range = productsCount/16;
		if (productsCount%16 > 0) {
			range++;
		}
		model.addAttribute("range", IntStream.range(0, range).toArray());
		return "products";
	}
	
	@RequestMapping(value="/imageUpload", method=RequestMethod.POST)
	public ModelAndView imageUpload(HttpSession session, Product product, @RequestParam("imageFile") MultipartFile... imageFile) {
		String savingPath = session.getServletContext().getRealPath("/") + "/img/products/";
		for(MultipartFile file:imageFile){
			String imageName = productsService.uplodeImage(product,savingPath, file);
			productsService.create(product.getBrand().getBrandId(), imageName);
		}
		
		// 아래부분 리펙토링 필요. (AdminController.java와 중복)
		ModelAndView mv = new ModelAndView("admin/productRegistration");
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("product", new Product());
		return mv;
	}
}
