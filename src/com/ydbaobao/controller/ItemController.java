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
import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.model.Customer;
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

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cartForm(HttpSession session, Model model) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("list", new Gson().toJson(itemDao.readCartList(customerId)));
		model.addAttribute("categories", categoryService.read());
		Customer customer = customerDao.readCustomerById(customerId);
		model.addAttribute("customerGrade", customer.getCustomerGrade());
		return "cart";
	}

	@RequestMapping(value = "/cart/list", method = RequestMethod.GET)
	public @ResponseBody List<Item> readCartList(HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		List<Item> list = itemDao.readCartList(customerId);
		System.out.println(list);
		return list;
	}

	@RequestMapping(value = "/add")
	public ResponseEntity<Object> addToCart(@RequestParam String productId, @RequestParam String size,
			@RequestParam String quantity, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		if (customerId == null) {
			return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
		}
		String[] sizeArray = size.split("-");
		String[] quantityArray = quantity.split("-");
		for(int i=0; i< quantityArray.length; i++){
			itemDao.addCart(customerId, productId, sizeArray[i], Integer.parseInt(quantityArray[i]));
		}
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
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

	@RequestMapping(value = "/delete/{itemId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFromCart(@PathVariable int itemId, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		itemService.deleteCartList(customerId, itemId);
		return JSONResponseUtil.getJSONResponse("" + itemId, HttpStatus.OK);
	}

}
