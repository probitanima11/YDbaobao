package com.ydbaobao.admincontroller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminConfigService adminConfigService; 
	@Resource
	private CustomerService customerService;
	/**
	 * 관리자 페이지 접근을 위한 GET 요청을 응답. session 체크를 하여 sessionAdmin이 Null일 경우 관리자 번호
	 * 체크 페이지로 포워딩.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(HttpSession session) {
		if (session.getAttribute("sessionAdmin") == null) {
			return "admin/adminCheck";
		}
		return "admin/admin";
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String check() {
		return "admin/adminCheck";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String checkForm(@RequestParam String adminPassword, HttpSession session, Model model) {
		if (adminPassword.equals(adminConfigService.read().getAdminPassword())) {
			session.setAttribute("sessionAdmin", adminPassword);
			model.addAttribute("customers", customerService.readCustomers());
			return "admin/customerManager";
		}
		return "admin/adminCheck";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (null == session.getAttribute("sessionAdmin")) {
			return "admin/adminCheck";
		}
		session.removeAttribute("sessionAdmin");
		return "redirect:/";
	}
}
