package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.service.BrandService;
import com.ydbaobao.util.JSONResponseUtil;

@Controller
@RequestMapping("/brand")
public class BrandController {
	private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
	
	@Resource
	private BrandService brandService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<Object> search(@RequestParam String firstLetter) {
		logger.debug("search");
		return JSONResponseUtil.getJSONResponse(brandService.search(firstLetter), HttpStatus.OK);
	}
}
