package com.ydbaobao.admincontroller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
