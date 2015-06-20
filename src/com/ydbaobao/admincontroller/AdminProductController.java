package com.ydbaobao.admincontroller;

import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.support.CommonUtil;
import com.support.JSONResponseUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
	private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);
	
	@Resource
	private ProductService productService;
	@Resource
	private BrandService brandService;
	@Resource
	private CategoryService categoryService;
	
	/**
	 * 상품 이미지 업로드 페이지 호출
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registProducts(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("brandList", brandService.readBrands());
		return "admin/productRegistration";
	}
	
	/**
	 * 상품 관리 페이지 호출
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model, @RequestParam int page) {
		int selectedCategoryId = -1;
		List<Category> categories = categoryService.read();
		categories.add(0, new Category(selectedCategoryId, "전체보기", productService.count()));
		
		int totalPage = CommonUtil.countTotalPage(productService.count(), 16);
		
		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		model.addAttribute("url", "/admin/products?page=");
		model.addAttribute("product", new Product());
		model.addAttribute("products", productService.readProducts(page, 16));
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("selectedCategoryId", selectedCategoryId);
		model.addAttribute("categories", categories);
		return "admin/productManager";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String readByCategoryId(Model model, @RequestParam int categoryId, @RequestParam int page) {
		model.addAttribute("product", new Product());
		List<Product> products;
		int totalPage;
		if(categoryId == -1){
			products = productService.readProducts(page, 16);
			totalPage = CommonUtil.countTotalPage(productService.count(), 16);
		} else{
			products = productService.readListByCategoryId(categoryId, page, 16);
			totalPage = CommonUtil.countTotalPage(productService.readListByCategoryId(categoryId).size(), 16);
		}
		
		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		model.addAttribute("url", "/admin/products/category?categoryId=" + categoryId + "&page=");
	
		model.addAttribute("products", products);
		model.addAttribute("brands", brandService.readBrands());
		List<Category> categories = categoryService.read();
		categories.add(0, new Category(-1, "전체보기", 0));
		model.addAttribute("selectedCategoryId", categoryId);
		model.addAttribute("categories", categories);
		return "admin/productManager";
	}
	
	/**
	 * 상품 이미지 등록(상품 등록)
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public String imageUpload(Model model, Product product, @RequestParam("imageFile") MultipartFile... imageFile) {
		for(MultipartFile file:imageFile) {
			int productId = productService.create(product.getBrand().getBrandId());
			product.setProductId(productId);
			String imageName = productService.uploadImage(product, file);
			productService.updateProductImage(product, imageName);
		}
		model.addAttribute("brandList", brandService.readBrands());
		return "admin/productRegistration";
	}
	
	/**
	 * 저장 된 상품 전체 삭제
	 * @return success or fail
	 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAll() {
		if(productService.deleteAll()) {
			return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
		}
		return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
	}
	
	/**
	 * 특정 상품 삭제
	 * @param 상품Id(productId)
	 * @return success or fail
	 */
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable int productId) {
		if(productService.delete(productId)) {
			return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
		}
		return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
	}

	/**
	 * 특정 상품 정보 수정
	 * @param productId, productName, categoryId, brandId, productPrice, productSize, productDescription
	 * @return success or fail
	 */
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable int productId, Product product, Category category, Brand brand){
		product.setCategory(category);
		product.setBrand(brand);
		if(productService.update(product)){
			return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
		}
		return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
	}
}
