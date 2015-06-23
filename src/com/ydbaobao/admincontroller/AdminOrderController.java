package com.ydbaobao.admincontroller;

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
import com.ydbaobao.model.Order;
import com.ydbaobao.model.Payment;
import com.ydbaobao.service.OrderService;
import com.ydbaobao.service.PaymentService;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private PaymentService paymentService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageOrder(Model model) {
		model.addAttribute("orders", orderService.readOrders());
		return "admin/orderManager";
	}
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Object> readOrder(@PathVariable int orderId) {
		return JSONResponseUtil.getJSONResponse(orderService.readOrder(orderId), HttpStatus.OK);
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateOrder(@PathVariable int orderId, @RequestParam String orderStatus) {
		if (orderService.readOrder(orderId).getOrderStatus().equals('C')) {
			return JSONResponseUtil.getJSONResponse("이미 취소된 주문입니다.", HttpStatus.OK);
		}
		orderService.updateOrder(orderId, orderStatus);
		return JSONResponseUtil.getJSONResponse("주문상태변경완료", HttpStatus.OK);
	}
}
