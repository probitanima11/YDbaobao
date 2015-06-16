package com.ydbaobao.admincontroller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/admin/customers")
public class AdminCustomerController {
	@Resource
	private CustomerService customerService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageMember(Model model) {
		model.addAttribute("customers", customerService.readCustomers());
		return "admin/customerManager";
	}
	
	/**
	 * 관리자 페이지에서 회원목록에서 회원상세보기 버튼 클릭시 동작
	 * @param customerId
	 * customerId로 검색한 결과를 customer 객체에 저장
	 */
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public String showDetailMember(Model model, @PathVariable String customerId) {
		model.addAttribute("customer", customerService.readCustomerById(customerId));
		return "admin/customerDetail";
	}
	
	/**
	 * 관리자 페이지에서 회원목록에서 회원 삭제
	 * @param customerId
	 */
	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	protected String delete(@PathVariable String customerId, HttpSession session, Model model) {
		if(session.getAttribute("sessionAdmin") == null) {
			model.addAttribute("errorMessage", "권한이 없습니다.");
			return "redirect:/admin/customers";
		}
		customerService.delete(customerId);
		return "redirect:/admin/customers";
	}
}