package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.ServletRequestUtil;
import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.model.Customer;
import com.ydbaobao.model.SessionCustomer;
import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Resource
	private CustomerService customerService;
	@Resource
	private CustomerDao customerDao;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("customer", new Customer());
		return "form";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateForm(Model model,HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("isUpdate", true);
		model.addAttribute("customer", customerDao.findCustomerByCustomerId(customerId));
		return "form";
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public String create(Customer customer, @RequestParam String customerAgainPassword, Model model) throws ExceptionForMessage{
		//TODO VALIDATION CHECK
//	public String create(@Valid Customer customer, BindingResult result, @RequestParam String customerAgainPassword, Model model) throws ExceptionForMessage{
//		if(result.hasErrors()) {
//			throw new JoinValidationException(extractValidationMessages(result));
//        }
		if(!customer.getCustomerPassword().equals(customerAgainPassword)) {
			model.addAttribute("customer", new Customer());
			model.addAttribute("message", "아이디와 비밀번호가 일치하지 않습니다.");
			return "form";
		}
		customerService.join(customer);
		return "index";
	}
	
	@RequestMapping(value ="/update", method = RequestMethod.POST)
	public String update(Customer customer, @RequestParam String customerAgainPassword, Model model) throws ExceptionForMessage{
		//TODO VALIDATION CHECK
//	public String create(@Valid Customer customer, BindingResult result, @RequestParam String customerAgainPassword, Model model) throws ExceptionForMessage{
//		if(result.hasErrors()) {
//			throw new JoinValidationException(extractValidationMessages(result));
//        }
		if(!customer.getCustomerPassword().equals(customerAgainPassword)) {
			model.addAttribute("customer", new Customer());
			model.addAttribute("message", "아이디와 비밀번호가 일치하지 않습니다.");
			return "form";
		}
		customerService.update(customer);
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginView(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected String login(@RequestParam String customerId, @RequestParam String customerPassword, HttpSession session, Model model) throws ExceptionForMessage {
		SessionCustomer sessionCustomer = (customerService.login(customerId, customerPassword)).createSessionCustomer();
		session.setAttribute("sessionCustomer", sessionCustomer);
		return "index";
	}
	
	@RequestMapping("/logout")
	protected String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
