package com.ydbaobao.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String load(Model model, @RequestParam String category) {
		//service를 통해 category에 따른 products 리스트를 받아 model에 담는다.
		return "products";
	}
	
	@RequestMapping(value="imageUpload", method=RequestMethod.POST)
	public String imageUpload(Model model, @RequestParam String brand, @RequestParam MultipartFile imageFile) {

		//TODO service를 통해 데이터베이스에 저장한다.
		// 상품 이름은 브랜드 네임으로
		// 가격은 0원
		// description은 null
		// 카테고리아이디는 '0'(미분류)
		
		return "productRegistration";
	}
	
	
}
