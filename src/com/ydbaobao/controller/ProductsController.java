package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("productList", productsService.readListByCategory(categoryId));
		return "products";
	}
	
	@RequestMapping(value="/imageUpload", method=RequestMethod.POST)
	public String imageUpload(Product product, @RequestParam("imageFile") MultipartFile... imageFile) {
		
		logger.debug("상품 : {}", product.toString());
		logger.debug("파일이름 : {}", imageFile[0].getOriginalFilename());
		logger.debug("파일이름 : {}", imageFile[1].getOriginalFilename());
		logger.debug("파일이름 : {}", imageFile[2].getOriginalFilename());
		
		String savingPath = "/img/products/unclassified/";
		
		for(MultipartFile file:imageFile){
			// 이미지 저장(장소에);
			
			productsService.uplodeImage(product,savingPath, file);
			productsService.create(product.getBrand().getBrandId(), product.getProductImage());
			
			
		}
		
		
		//TODO service를 통해 데이터베이스에 저장한다.
		// 상품 이름은 브랜드 네임으로
		// 가격은 0원
		// description은 null
		// 카테고리아이디는 '0'(미분류)
		
		return "productRegistration";
	}
	
	
}
