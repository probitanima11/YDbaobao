package com.ydbaobao.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydbaobao.model.Customer;
import com.ydbaobao.model.SessionCustomer;
import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Resource
	private CustomerService customerService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected @ResponseBody boolean login(@RequestParam String customerId, @RequestParam String customerPassword, HttpSession session) {
		SessionCustomer sessionCustomer = (customerService.login(customerId, customerPassword)).createSessionCustomer();
		session.setAttribute("sessionCustomer", sessionCustomer);
		return true;
	}
	
	@RequestMapping("/logout")
	protected String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
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
