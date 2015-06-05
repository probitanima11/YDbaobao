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
import org.springframework.web.context.request.WebRequest;
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
	public String load(Model model, WebRequest req, @RequestParam int categoryId) {
		int quantity = 16;
		
		List<Character> firstLetterList = new ArrayList<Character>();
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			firstLetterList.add(ch);	
		}
		model.addAttribute("firstLetterList", firstLetterList);
		
		int index = 0;
		int selectedIndex = 0;
		if (null != req.getParameter("index")) {
			index = Integer.parseInt(req.getParameter("index"));
			selectedIndex = index-1;
			index = (index-1)*quantity;
		}
		model.addAttribute("selectedIndex", selectedIndex);
		model.addAttribute("productList", productsService.readListByCategoryId(categoryId, index, quantity));
		
		int start = 0, end = 0;
		int productsCount = productsService.countByCategoryId(categoryId);
		int range = productsCount/quantity;
		end = range;
		if (productsCount%quantity > 0) {
			range++;
		}
		if (range > 10) {
			start = index/quantity/10*10;
		}
		if (range > start+10) {
			end = start+10;
		}
		if (end == start) {
			end++;
		}
		if (end < range) {
			model.addAttribute("nextBtn", true);
		}
		model.addAttribute("range", IntStream.range(start, end).toArray());
		
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("categories", categoryService.read());
		return "products";
	}
	
	@RequestMapping(value="/imageUpload", method=RequestMethod.POST)
	public ModelAndView imageUpload(HttpSession session, Product product, @RequestParam("imageFile") MultipartFile... imageFile) {
		String savingPath = "/home/baobao/products/";
		logger.debug("savingPath: {}", savingPath);
		for(MultipartFile file:imageFile){
			String imageName = productsService.uplodeImage(product,savingPath, file);
			productsService.create(product.getBrand().getBrandId(), imageName);
		}
		
		// 아래부분 리펙토링 필요. (AdminController.java와 중복)
		ModelAndView mv = new ModelAndView("admin/productRegistration");
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("categoryList", categoryService.read());
		mv.addObject("productList", productsService.readUnclassifiedProducts());
		mv.addObject("product", new Product());
		return mv;
	}
}
