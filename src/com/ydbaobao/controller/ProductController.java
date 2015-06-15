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
import com.ydbaobao.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Resource
	private ProductService productService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;


	@RequestMapping()
	public String read(@RequestParam int productId, Model model) {
		model.addAttribute("product", productService.read(productId));
		return "product";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Product product) {
		productService.update(product);
		
		//logger.debug("재고량 : {}", product.getStockList().toString());
		
		//TODO AJAX로 변경 예정.
		ModelAndView mv = new ModelAndView("admin/productManager");
		mv.addObject("product", new Product());
		mv.addObject("productList", productService.readProducts());
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("categoryList", categoryService.read());
		return mv;
	}
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String load(Model model, WebRequest req, @RequestParam int categoryId) {
		PageConfigParam p = new PageConfigParam(16, req.getParameter("index"), categoryService.readByCategoryId(categoryId).getCategoryCount());

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
	
	@RequestMapping(value="/imageUpload", method=RequestMethod.POST)
	public ModelAndView imageUpload(Product product, @RequestParam("imageFile") MultipartFile... imageFile) {
		for(MultipartFile file:imageFile){
			int productId = productService.create(product.getBrand().getBrandId());
			product.setProductId(productId);
			String imageName = productService.uploadImage(product, file);
			productService.updateProductImage(product, imageName);
		}

		ModelAndView mv = new ModelAndView("admin/productRegistration");
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("unregisteredProductsCountByBrand", productService.unregisteredProductsCountByBrand());
		return mv;
	}
}