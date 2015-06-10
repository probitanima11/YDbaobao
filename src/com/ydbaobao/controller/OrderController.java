package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	@Resource
	private OrderService orderService;
	
	//TODO 주문목록 조회하기 
	@RequestMapping()
	public ResponseEntity<Object> readOrders(HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		return JSONResponseUtil.getJSONResponse(orderService.readOrders(customerId), HttpStatus.OK);
	}
	
	//TODO 주문상세 조회하기 
	@RequestMapping()
	public ResponseEntity<Object> readOrder(HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		return JSONResponseUtil.getJSONResponse(orderService.readOrder(customerId), HttpStatus.OK);
	}

	//TODO 주문사항 변경하기
	//TODO 상품화면에서 주문하기 
	//TODO 장바구니에서 주문하기 
	//TODO 주문 취소하기
}
