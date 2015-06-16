package com.ydbaobao.controller;

import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.support.JSONResponseUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
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
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAllProducts() {
		if(productService.deleteAll()) {
			return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
		}
		return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("productList", productService.readProducts());
		model.addAttribute("brandList", brandService.readBrands());
		model.addAttribute("categoryList", categoryService.read());
		return "admin/productManager";
	}
	
	@RequestMapping(value = "/{productId}/{productName}/{categoryId}/{brandId}/{productPrice}/{productSize}/{productDescription}", method = RequestMethod.PUT)
	public @ResponseBody String update(@PathVariable int productId, @PathVariable String productName, @PathVariable int categoryId, @PathVariable int brandId, @PathVariable int productPrice, @PathVariable String productSize, @PathVariable String productDescription){
		Product product = new Product(productId, productName,new Category(categoryId), new Brand(brandId), productPrice, productDescription, productSize);
		if(productService.update(product)){
			return "success";
		}
		return "fail";
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
	public String imageUpload(Model model, Product product, @RequestParam("imageFile") MultipartFile... imageFile) {
		for(MultipartFile file:imageFile){
			int productId = productService.create(product.getBrand().getBrandId());
			product.setProductId(productId);
			String imageName = productService.uploadImage(product, file);
			productService.updateProductImage(product, imageName);
		}

		model.addAttribute("brandList", brandService.readBrands());
		model.addAttribute("unregisteredProductsCountByBrand", productService.unregisteredProductsCountByBrand());
		return "admin/productRegistration";
	}
}