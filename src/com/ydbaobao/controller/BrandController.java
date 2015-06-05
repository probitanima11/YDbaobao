package com.ydbaobao.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.support.JSONResponseUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductsService;

@Controller
@RequestMapping("/brand")
public class BrandController {
	private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
	
	@Resource
	private BrandService brandService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductsService productsService;
	
	/**
	 * index.jsp에서 브랜드 메뉴 검색하는 곳에 사용
	 * @author jyb
	 * @param 검색에 사용될 브랜드 이름의 첫글자(A~Z)
	 * @return 검색된 결과를 JSON 데이타로 전달
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<Object> search(@RequestParam String firstLetter) {
		logger.debug("search");
		return JSONResponseUtil.getJSONResponse(brandService.search(firstLetter), HttpStatus.OK);
	}
	
	/**
	 * TODO ProductsController load와 중복
	 * 
	 * 브랜드 클릭시 해당 브랜드의 상품만 검색
	 * @author jyb
	 * @param 검색에 사용될 브랜드 Id
	 * @return products.jsp
	 */
	@RequestMapping(value="/products/{brandId}", method=RequestMethod.GET)
	public String load(Model model, @PathVariable int brandId) {
		List<Character> firstLetterList = new ArrayList<Character>();
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			firstLetterList.add(ch);	
		}
		List<Brand> brands = brandService.readBrands();
		
		
		//클릭한 브랜드를 DB가 아닌 brandList에서 검색
		Brand brand = null;
		for(int i = 0; i < brands.size(); i++) {
			if(brands.get(i).getBrandByBrandId(brandId) != null) {
				brand = brands.get(i).getBrandByBrandId(brandId);
				break;
			}
		}
		
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("brands", brands);
		model.addAttribute("brand", brand);
		model.addAttribute("firstLetterList", firstLetterList);
		model.addAttribute("productList", productsService.readListByBrandId(brandId));
		return "products";
	}
}
