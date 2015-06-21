package com.ydbaobao.admincontroller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.support.JSONResponseUtil;
import com.ydbaobao.service.OrderService;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
	
	@Resource
	private OrderService orderService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageOrder(Model model) {
		model.addAttribute("orders", orderService.readOrders());
		return "admin/orderManager";
	}
	
	@RequestMapping(value = "/read/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Object> readOrder(@PathVariable int orderId) {
		return JSONResponseUtil.getJSONResponse(orderService.readOrder(orderId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/check/{orderId}", method = RequestMethod.POST)
	public ResponseEntity<Object> checkOrder(@PathVariable int orderId) {
		if (orderService.readOrder(orderId).getOrderStatus().equals('C')) {
			return JSONResponseUtil.getJSONResponse("이미 취소된 주문입니다.", HttpStatus.OK);
		}
		orderService.checkOrder(orderId);
		return JSONResponseUtil.getJSONResponse("주문승인완료", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/claim/{orderId}", method = RequestMethod.POST)
	public ResponseEntity<Object> claimOrder(@PathVariable int orderId) {
		if (orderService.readOrder(orderId).getOrderStatus().equals('C')) {
			return JSONResponseUtil.getJSONResponse("이미 취소된 주문입니다.", HttpStatus.OK);
		}
		orderService.claimOrder(orderId);
		return JSONResponseUtil.getJSONResponse("주문반려완료", HttpStatus.OK);
	}
}
