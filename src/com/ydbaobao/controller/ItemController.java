package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.ServletRequestUtil;
import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.service.ItemService;
import com.ydbaobao.util.JSONResponseUtil;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource
	private ItemService itemService;
	
	@Resource
	private ItemDao itemDao;
	
	@RequestMapping(value="/add")
	public ResponseEntity<Object> addToCart(@RequestParam String productId, @RequestParam String size, @RequestParam int quantity, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		itemDao.addCart(customerId, productId, size, quantity);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
}
