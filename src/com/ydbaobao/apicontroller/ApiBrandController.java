package com.ydbaobao.apicontroller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.JSONResponseUtil;
import com.ydbaobao.service.BrandService;

@Controller
@RequestMapping("/api/brands")
public class ApiBrandController {
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
	
	@RequestMapping(value="/{brandId}/{brandName}", method=RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable String brandId, @PathVariable String brandName) {
		brandService.updateBrand(brandId, brandName);
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
