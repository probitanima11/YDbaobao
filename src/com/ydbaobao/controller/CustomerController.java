package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.model.Customer;
import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Resource
	private CustomerService customerService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("customer", new Customer());
		return "form";
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public String create(@RequestParam String customerId, @RequestParam String customerName, @RequestParam String customerPassword, @RequestParam String customerAgainPassword, @RequestParam String customerPhone, @RequestParam String customerEmail, @RequestParam String customerAddress) {
		customerService.join(new Customer(customerId, customerName, customerPassword, customerPhone, customerEmail, customerAddress));
		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login() {
		return "index";
	}
}
