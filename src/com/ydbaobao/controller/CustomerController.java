package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
	public String form() {
		return "form";
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public String create(@RequestParam Customer customer) {
		customerService.join(customer);
		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login() {
		return "index";
	}
}
