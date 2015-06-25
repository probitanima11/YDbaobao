package com.ydbaobao.admincontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.support.CommonUtil;
import com.ydbaobao.model.Customer;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.CustomerService;

@Controller
@RequestMapping("/admin/customers")
public class AdminCustomerController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCustomerController.class);
	
	@Resource
	private CustomerService customerService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageMember(Model model) {
		model.addAttribute("gradeId", "-1");
		model.addAttribute("customers", customerService.readCustomers());
		return "admin/customerManager";
	}
	
	@RequestMapping(value = "/grade", method = RequestMethod.GET)
	public String gradeMember(Model model, @RequestParam int gradeId) {
		if(gradeId==-1){
			model.addAttribute("customers", customerService.readCustomers());
		}
		else {
			model.addAttribute("customers", customerService.readCustomersByGrade(gradeId));
		}
		model.addAttribute("gradeId", gradeId);
		return "admin/customerManager";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String readByCustomerName(HttpSession session, Model model, @RequestParam String customerName, @RequestParam int page) {
		String terms = customerService.preprocessingTerms(customerName);
		model.addAttribute("product", new Product());
		List<Customer> customers = customerService.readByCustomerName(terms, page, 10);
		int count = customerService.countBySearchCustomerName(terms);
		model.addAttribute("searchMessage", "고객명 \'"+customerName+"\'에 대한 검색 결과가 "+count+" 건 있습니다.");
		int totalPage = CommonUtil.countTotalPage(count, CommonUtil.productsPerPage);
		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, page));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("url", "/admin/customers/search?customerName=" + terms + "&page=");
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		model.addAttribute("customers", customers);
		return "admin/customerManager";
	}
	
	@RequestMapping(value = "/search/{customerId}", method = RequestMethod.GET)
	public String readCustomerById(Model model, @PathVariable String customerId) {
		
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customerService.readCustomerById(customerId));
		model.addAttribute("customers", customers);
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
	 * 관리자 페이지에서 회원목록에서 회원 삭제
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
