package com.ydbaobao.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageOrder() {
		return "admin/orderManager";
	}
}
