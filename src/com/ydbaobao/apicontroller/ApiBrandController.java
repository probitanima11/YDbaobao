package com.ydbaobao.apicontroller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.service.BrandService;
import com.ydbaobao.util.JSONResponseUtil;

@Controller
@RequestMapping("/api/admin/manage/brand")
public class ApiBrandController {
	@Resource
	private BrandService brandService;
	
	@RequestMapping()
	public ResponseEntity<Object> show(Model model) {
		return JSONResponseUtil.getJSONResponse(brandService.readBrands(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestParam String brandName) {
		brandService.createBrand(brandName);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{brandId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestParam String brandId, @RequestParam String brandName) {
		brandService.updateBrand(brandId, brandName);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{brandId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestParam String brandId) {
		brandService.deleteBrand(brandId);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
}
