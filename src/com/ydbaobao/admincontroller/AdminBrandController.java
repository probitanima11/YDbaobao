package com.ydbaobao.admincontroller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.JSONResponseUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.service.BrandService;

@Controller
@RequestMapping("/admin/brands")
public class AdminBrandController {
	private static final Logger logger = LoggerFactory.getLogger(AdminBrandController.class);
	@Resource
	private BrandService brandService;

	/**
	 * 브랜드 추가
	 * 
	 * @param brandName
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestParam String brandName, @RequestParam int discount_1,
			@RequestParam int discount_2, @RequestParam int discount_3, @RequestParam int discount_4,
			@RequestParam int discount_5, @RequestParam String brandSize) {
		Brand brand = new Brand(brandName, 0, discount_1, discount_2, discount_3, discount_4, discount_5, brandSize);
		if (brandService.createBrand(brand) < 0) {
			return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
		}
		return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		model.addAttribute("brands", brandService.readBrands());
		return "brandManager";
	}

	@RequestMapping("/find")
	public ResponseEntity<Object> find(@RequestParam String searchValue) {
		return JSONResponseUtil.getJSONResponse(brandService.findBrands(searchValue), HttpStatus.OK);
	}

	// TODO POST -> PUT
	@RequestMapping(value = "/{brandId}", method = RequestMethod.POST)
	public ResponseEntity<Object> update(@Valid Brand brand, BindingResult result) {
		if (result.hasErrors()) {
			return JSONResponseUtil.getJSONResponse(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		brandService.updateBrand(brand);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}

	@RequestMapping(value = "/{brandId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String brandId) {
		brandService.deleteBrand(brandId);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}

}
