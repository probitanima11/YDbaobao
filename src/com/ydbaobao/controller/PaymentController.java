package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.PaymentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {
	@Resource
	private CategoryService categoryService;
	@Resource
	private PaymentService paymentService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String readPage(HttpSession session, Model model) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		return "payment";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public ResponseEntity<Object> readPayments(HttpSession session) throws IOException {
		return JSONResponseUtil.getJSONResponse(paymentService.readPaymentsByCustomerId(ServletRequestUtil.getCustomerIdFromSession(session)), HttpStatus.OK);
	}
}
