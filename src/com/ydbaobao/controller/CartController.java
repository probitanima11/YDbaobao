package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.JSONResponseUtil;
import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.model.Customer;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ItemService;

@Controller
@RequestMapping("/carts")
public class CartController {
	@Resource
	ItemDao itemDao;
	@Resource
	CustomerDao customerDao;
	@Resource
	ItemService itemService;
	@Resource
	CategoryService categoryService;
	
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.POST)
	public ResponseEntity<Object> addToCart(@PathVariable String customerId, @RequestParam String productId, @RequestParam String size,
			@RequestParam String quantity) throws IOException {
		String[] sizeArray = size.split("-");
		String[] quantityArray = quantity.split("-");
		for(int i=0; i< quantityArray.length; i++){
			itemDao.addCart(customerId, productId, sizeArray[i], Integer.parseInt(quantityArray[i]));
		}
		return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public String cartForm(Model model, @PathVariable String customerId) throws IOException {
		model.addAttribute("items", itemDao.readCartList(customerId));
		model.addAttribute("categories", categoryService.read());
		Customer customer = customerDao.readCustomerById(customerId);
		model.addAttribute("customer", customer);
		return "cart";
	}
	
	@RequestMapping(value = "/{customerId}/items/{itemId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFromCart(@PathVariable String customerId, @PathVariable int itemId) throws IOException {
		itemService.deleteCartList(customerId, itemId);
		return JSONResponseUtil.getJSONResponse("" + itemId, HttpStatus.OK);
	}
}
