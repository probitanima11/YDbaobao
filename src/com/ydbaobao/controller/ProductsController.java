package com.ydbaobao.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ydbaobao.model.Brand;
import com.ydbaobao.model.PageConfigParam;
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
		PageConfigParam p = new PageConfigParam(16, req.getParameter("index"), productsService.countByCategoryId(categoryId));

		if (p.getEnd() < p.getRange()) {
			model.addAttribute("nextBtn", true);
		}
		model.addAttribute("selectedIndex", p.getSelectedIndex());
		model.addAttribute("range", IntStream.range(p.getStart(), p.getEnd()).toArray());
		model.addAttribute("productList", productsService.readListByCategoryId(categoryId, p.getIndex(), p.getQuantity()));
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}
	
	@RequestMapping(value="/imageUpload", method=RequestMethod.POST)
	public ModelAndView imageUpload(Product product, @RequestParam("imageFile") MultipartFile... imageFile) {
		String savingPath = "/home/baobao/products/";
		for(MultipartFile file:imageFile){
			String imageName = productsService.uplodeImage(product,savingPath, file);
			productsService.create(product.getBrand().getBrandId(), imageName);
		}
		
		// 아래부분 리펙토링 필요. (AdminController.java와 중복)
		ModelAndView mv = new ModelAndView("admin/productRegistration");
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("unregisteredProductsCountByBrand", productsService.unregisteredProductsCountByBrand());
		mv.addObject("product", new Product());
		return mv;
	}
}
