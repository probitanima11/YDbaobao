package com.ydbaobao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.support.ServletRequestUtil;
import com.ydbaobao.service.CategoryService;

@Controller
@RequestMapping("/payments")
public class PaymentController {
	@Resource
	private CategoryService categoryService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String readOrder(HttpSession session, Model model) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		return "payment";
	}

}
