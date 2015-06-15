package com.ydbaobao.apicontroller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.JSONResponseUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.service.BrandService;

@Controller
@RequestMapping("/api/brands")
public class ApiBrandController {
	private static final Logger logger = LoggerFactory.getLogger(ApiBrandController.class);
	
	@Resource
	private BrandService brandService;
	
	@RequestMapping()
	public ResponseEntity<Object> show() {
		return JSONResponseUtil.getJSONResponse(brandService.readBrands(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestParam String brandName) {
		return JSONResponseUtil.getJSONResponse(brandService.createBrand(brandName), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{brandId}", method=RequestMethod.POST)
	public ResponseEntity<Object> update(@PathVariable int brandId, @RequestParam String brandName, @RequestParam int discount_1, @RequestParam int discount_2, @RequestParam int discount_3, @RequestParam int discount_4, @RequestParam int discount_5, @RequestParam String brandSize) {
		Brand brand = new Brand(brandId, brandName, 0, discount_1, discount_2, discount_3, discount_4, discount_5, brandSize);
		brandService.updateBrand(brand);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{brandId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String brandId) {
		brandService.deleteBrand(brandId);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
	@RequestMapping("/find")
	public ResponseEntity<Object> find(@RequestParam String searchValue) {
		return JSONResponseUtil.getJSONResponse(brandService.findBrands(searchValue), HttpStatus.OK);
	}
}
