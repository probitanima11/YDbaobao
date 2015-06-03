package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydbaobao.service.BrandService;

@Controller
@RequestMapping("admin/brandManager")
public class BrandController {
	@Resource
	private BrandService brandService;
}
