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

import com.google.gson.Gson;
import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ItemService;
import com.ydbaobao.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
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
	
	//TODO 주문상세 조회하기 
	@RequestMapping(value="/{orderId}")
	public ResponseEntity<Object> readOrder(@PathVariable int orderId){
		return JSONResponseUtil.getJSONResponse(orderService.readOrder(orderId), HttpStatus.OK);
	}
	//TODO 주문사항 변경하기
	//TODO 상품화면에서 주문하기 
	//TODO 장바구니에서 주문하기 
	//TODO 주문 취소하기
}
