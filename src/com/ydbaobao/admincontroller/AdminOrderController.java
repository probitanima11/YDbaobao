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

	/**
	 * 관리자 화면에서 주문 목록 리스트만 받아오기
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageOrder(Model model) {
		model.addAttribute("orders", orderService.readOrdersListOnly());
		return "admin/orderManager";
	}

	/**
	 * 관지자 화면에서 주문 내역에 대한 상태 변경(승인, 반려)
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateOrder(@PathVariable int orderId, @RequestParam String orderStatus) {
		Order order = orderService.readOrder(orderId);
		if (order.getOrderStatus().equals('C')) {
			return JSONResponseUtil.getJSONResponse("이미 취소된 주문입니다.", HttpStatus.OK);
		}
		if(orderStatus.equals("S")) paymentService.createPayment(new Payment(order.getCustomer(), "P", order.getRealPrice()));
		orderService.updateOrder(orderId, orderStatus);
		return JSONResponseUtil.getJSONResponse("주문상태변경완료", HttpStatus.OK);
	}
}
