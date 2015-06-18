package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.CommonUtil;
import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.exception.JoinValidationException;
import com.ydbaobao.model.Customer;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	@Resource
	private CustomerService customerService;
	@Resource
	private CategoryService categoryService;
	
	@RequestMapping(value = "/updateForm", method = RequestMethod.GET)
	public String updateForm(Model model, HttpSession session) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("isUpdate", true);
		model.addAttribute("customer", customerService.readCustomer(customerId));
		return "form";
	}
	
	//TODO form을 put으로 넘기는 방법 찾기!
	@RequestMapping(value ="/update", method = RequestMethod.POST)
	public String update(@Valid Customer customer, BindingResult result, @RequestParam String customerAgainPassword, Model model) throws ExceptionForMessage{
		if(result.hasErrors()) {
			throw new JoinValidationException(CommonUtil.extractValidationMessages(result));
        }
		if(!customer.getCustomerPassword().equals(customerAgainPassword)) {
			model.addAttribute("customer", new Customer());
			model.addAttribute("message", "아이디와 비밀번호가 일치하지 않습니다.");
			return "form";
		}
		customerService.update(customer);
		return "index";
	}
	
	@RequestMapping(value = "/{customerId}/grade/{grade}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable String customerId, @PathVariable String grade) {
		customerService.updateGrade(customerId, grade);
		return JSONResponseUtil.getJSONResponse("등급 변경 완료.", HttpStatus.OK);
	}
}
