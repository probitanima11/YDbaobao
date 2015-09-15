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
import org.springframework.web.bind.annotation.ResponseBody;

import com.support.JSONResponseUtil;
import com.ydbaobao.domain.Customer;
import com.ydbaobao.domain.Payment;
import com.ydbaobao.service.PaymentService;

@Controller
@RequestMapping("/admin/payment")
public class AdminPaymentController {
	
	@Resource
	private PaymentService paymentService;

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public String manageOrder(@PathVariable String customerId, Model model) {
		model.addAttribute("payments", paymentService.readPaymentsByCustomerId(customerId));
		return "admin/paymentManager";
	}
	
	@RequestMapping(value = "/read/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<Object> readOrder(@PathVariable String customerId) {
		return JSONResponseUtil.getJSONResponse(paymentService.readPaymentsByCustomerId(customerId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestParam String customerId, @RequestParam int amount, @RequestParam String paymentDate) {
		if(paymentService.createPayment(new Payment(new Customer(customerId), "I", amount, paymentDate))) {
			return "success";
		}
		return "fail";
	}
}
