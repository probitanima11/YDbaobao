package com.ydbaobao.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.support.ServletRequestUtil;
import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.model.Item;
import com.ydbaobao.service.ItemService;
import com.ydbaobao.util.JSONResponseUtil;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource
	private ItemService itemService;
	
	@Resource
	private ItemDao itemDao;
	
	@RequestMapping(value="/cart", method=RequestMethod.GET)
	public String cartForm(HttpSession session, Model model) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("list", new Gson().toJson(itemDao.readCartList(customerId)));
		return "cart";
	}
	
	@RequestMapping(value="/cart/list", method=RequestMethod.GET)
	public @ResponseBody List<Item> readCartList(HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		List<Item> list = itemDao.readCartList(customerId);
		System.out.println(list);
		return list;
//		return ResponseEntity<list>;
		
//		return JSONResponseUtil.getJSONResponse(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add")
	public ResponseEntity<Object> addToCart(@RequestParam String productId, @RequestParam String size, @RequestParam int quantity, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		itemDao.addCart(customerId, productId, size, quantity);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{itemId}")
	public ResponseEntity<Object> addToCart(@PathVariable int itemId, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		itemService.deleteCartList(customerId, itemId);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
}
