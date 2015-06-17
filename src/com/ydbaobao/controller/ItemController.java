package com.ydbaobao.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.model.Item;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource
	private ItemService itemService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private CustomerDao customerDao;

	@Resource
	private ItemDao itemDao;

	@RequestMapping(value = "/cart/list", method = RequestMethod.GET)
	public @ResponseBody List<Item> readCartList(HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		List<Item> list = itemDao.readCartList(customerId);
		System.out.println(list);
		return list;
	}

	@RequestMapping(value = "/order")
	public ResponseEntity<Object> orderDirect(@RequestParam String productId, @RequestParam String size,
			@RequestParam String quantity, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		if (customerId == null) {
			return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
		}
		String[] sizeArray = size.split("-");
		String[] quantityArray = quantity.split("-");
		for(int i=0; i< quantityArray.length; i++){
			itemService.orderDirect(customerId, productId, sizeArray[i], Integer.parseInt(quantityArray[i]));
		}
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
}
