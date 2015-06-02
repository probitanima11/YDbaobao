package com.ydbaobao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.exception.ExceptionForMessage;
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
	public String create(Customer customer, @RequestParam String customerAgainPassword) throws ExceptionForMessage{
		if(!customer.getCustomerPassword().equals(customerAgainPassword))
			return "index";
		customerService.join(customer);
		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login() {
		return "index";
	}
}
