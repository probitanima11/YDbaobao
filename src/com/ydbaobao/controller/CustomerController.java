package com.ydbaobao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.exception.JoinValidationException;
import com.ydbaobao.model.Customer;
import com.ydbaobao.model.SessionCustomer;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	@Resource
	private CustomerService customerService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private CustomerDao customerDao;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("categories", categoryService.read());
		return "form";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateForm(Model model, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("isUpdate", true);
		model.addAttribute("customer", customerDao.findCustomerByCustomerId(customerId));
		return "form";
	}
	
	@RequestMapping(value = "/{customerId}/grade/{grade}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable String customerId, @PathVariable String grade) {
		customerService.updateGrade(customerId, grade);
		return JSONResponseUtil.getJSONResponse("등급 변경 완료.", HttpStatus.OK);
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public String create(@Valid Customer customer, BindingResult result, @RequestParam String customerAgainPassword, Model model) throws ExceptionForMessage{
		if(result.hasErrors()) {
			throw new JoinValidationException(extractValidationMessages(result));
        }
		if(!customer.getCustomerPassword().equals(customerAgainPassword)) {
			model.addAttribute("customer", new Customer());
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "form";
		}
		customerService.join(customer);
		return "redirect:/";
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
		model.addAttribute("categories", categoryService.read());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected String login(@RequestParam String customerId, @RequestParam String customerPassword, HttpSession session, Model model) throws ExceptionForMessage {
		SessionCustomer sessionCustomer = (customerService.login(customerId, customerPassword)).createSessionCustomer();
		session.setAttribute("sessionCustomer", sessionCustomer);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	protected String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	private List<String> extractValidationMessages(BindingResult result) {
		List<ObjectError> list = result.getAllErrors();
		List<String> messageList = new ArrayList<>();
		System.out.println(list);
		for (ObjectError e : list) {
			messageList.add(e.getDefaultMessage());
		}
		return messageList;
	}
}
