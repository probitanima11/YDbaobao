package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ItemService;
import com.ydbaobao.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Resource
	private OrderService orderService;
	@Resource
	private ItemService itemService;
	@Resource
	private CategoryService categoryService;

	//TODO 주문목록 조회하기 
	@RequestMapping()
	public String readOrders(HttpSession session, Model model) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("order_list", new Gson().toJson(orderService.readOrders(customerId)));
		model.addAttribute("categories", categoryService.read());
		return "order";
	}

	//TODO 장바구니에서 주문하기 
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createOrder(@RequestParam int[] itemList, HttpSession session) throws IOException{
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		orderService.createOrder(customerId, itemList);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
	//TODO 상품화면에서 주문하기
	//TODO 주문사항 변경하기
	//TODO 주문 취소하기
}
