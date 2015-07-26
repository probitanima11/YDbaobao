package com.ydbaobao.admincontroller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.support.CommonUtil;
import com.ydbaobao.model.Customer;
import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/admin/customers")
public class AdminCustomerController {
	@Resource
	private CustomerService customerService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageMember(Model model, @RequestParam int page) {
		int customersPerPage = 10;
		model.addAttribute("gradeId", "-1");
		model.addAttribute("customers", customerService.readCustomers(page, customersPerPage));
		int count = customerService.countCustomers();
		int totalPage = CommonUtil.countTotalPage(count, customersPerPage);
		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, page));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("url", "/admin/customers?page=");
		model.addAttribute("range", CommonUtil.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		return "customerManager";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String readByCustomerName(HttpSession session, Model model, @RequestParam String customerName, @RequestParam int page) {
		String terms = customerService.preprocessingTerms(customerName);
		int customersPerPage = 10;
		List<Customer> customers = customerService.readByCustomerName(terms, page, customersPerPage);
		int count = customerService.countBySearchCustomerName(terms);
		model.addAttribute("searchMessage", "이름 \'"+terms+"\'에 대한 검색 결과가 "+count+" 건 있습니다.");
		int totalPage = CommonUtil.countTotalPage(count, customersPerPage);
		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, page));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("url", "/admin/customers/search?customerName=" + terms + "&page=");
		model.addAttribute("range", CommonUtil.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		model.addAttribute("customers", customers);
		return "customerManager";
	}
	
	@RequestMapping(value = "/search/{customerId}", method = RequestMethod.GET)
	public String readCustomerById(Model model, @PathVariable String customerId, @RequestParam int page) {
		String terms = customerService.preprocessingTerms(customerId);
		int count = customerService.countBySearchCustomerId(terms);
		int customersPerPage = 10;
		List<Customer> customers = customerService.readBySerchCustomerId(terms, page, customersPerPage);
		model.addAttribute("searchMessage", "ID \'"+terms+"\'에 대한 검색 결과가 "+count+" 건 있습니다.");
		int totalPage = CommonUtil.countTotalPage(count, customersPerPage);
		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, page));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("url", "/admin/customers/search/" + terms + "?page=");
		model.addAttribute("range", CommonUtil.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		model.addAttribute("customers", customers);
		return "customerManager";
	}
	
	
	/**
	 * 관리자 페이지에서 회원목록에서 회원상세보기 버튼 클릭시 동작
	 * @param customerId
	 * customerId로 검색한 결과를 customer 객체에 저장
	 */
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public String showDetailMember(Model model, @PathVariable String customerId) {
		model.addAttribute("customer", customerService.readCustomerById(customerId));
		return "customerDetail";
	}
	
	/**
	 * 회원 상세보기에서 회원 등급 수정 
	 * @param customerId, grade
	 * @return success or fail
	 */
	@RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
	public @ResponseBody String changeCustomerGrade(@PathVariable String customerId, @RequestParam String grade) {
		if(customerService.updateGrade(customerId, grade)) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 관리자 페이지에서 회원목록에서 회원계정 비활성화
	 * 회원의 등급이 0이 되고 장바구니와 주문을 삭제 (결제 내역은 남김)
	 * @param customerId
	 * @return success or fail
	 */
	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	protected @ResponseBody String delete(@PathVariable String customerId, HttpSession session, Model model) {
		if(session.getAttribute("sessionAdmin") == null) {
			model.addAttribute("errorMessage", "권한이 없습니다.");
			return "fail";
		}
		customerService.delete(customerId);
		return "success";
	}
}
