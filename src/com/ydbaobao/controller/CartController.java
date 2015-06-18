package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ItemService;

@Controller
@RequestMapping("/carts")
public class CartController {
	@Resource
	ItemService itemService;
	@Resource
	CategoryService categoryService;
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.POST)
	public ResponseEntity<Object> createItem(@PathVariable String customerId, @RequestParam String productId, @RequestParam String size, @RequestParam String quantity) throws IOException {
		// TODO 본인 확인
		itemService.createItems(customerId, size, quantity, productId);
		return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String cartForm(Model model, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("items", itemService.readCartItems(customerId));
		model.addAttribute("categories", categoryService.read());
		return "cart";
	}
	
	@RequestMapping(value = "/{customerId}/items/{itemId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFromCart(@PathVariable String customerId, @PathVariable int itemId) throws IOException {
		// TODO 본인 확인 
		itemService.deleteCartList(customerId, itemId);
		return JSONResponseUtil.getJSONResponse("" + itemId, HttpStatus.OK);
	}
}
