package com.ydbaobao.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.support.JSONResponseUtil;
import com.ydbaobao.dao.GradeDao;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.CustomerService;
import com.ydbaobao.service.ProductsService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private static String password = "1111";

	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;
	@Resource
	private CustomerService customerService;
	@Resource
	private ProductsService productsService;
	@Resource
	private GradeDao gradeDao;
	
	/**
	 * 관리자 페이지 접근을 위한 GET 요청을 응답.
	 * session 체크를 하여 sessionAdmin이 Null일 경우 관리자 번호 체크 페이지로 포워딩.
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
	public String checkForm(@RequestParam String adminPassword, HttpSession session) {
		if (adminPassword.equals(password)) {
			session.setAttribute("sessionAdmin", adminPassword);
			return "admin/admin";
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

	@RequestMapping(value = "/manage/member", method = RequestMethod.GET)
	public String manageMember(Model model) {
		model.addAttribute("members", customerService.readCustomers());
		return "admin/memberManager";
	}
	
	/**
	 * 관리자 페이지에서 회원목록에서 회원상세보기 버튼 클릭시 동작
	 * @author jyb
	 * @param customerId
	 * customerId로 검색한 결과를 customer 객체에 저장
	 */
	@RequestMapping(value = "/manage/member/{customerId}", method = RequestMethod.GET)
	public String showDetailMember(Model model, @PathVariable String customerId) {
		model.addAttribute("customer", customerService.readCustomerById(customerId));
		return "admin/memberDetail";
	}

	@RequestMapping(value = "/manage/brand", method = RequestMethod.GET)
	public String manageBrand(Model model) {
		model.addAttribute("brands", brandService.readBrands());
		return "admin/brandManager";
	}
	
	@RequestMapping(value = "/manage/grade", method = RequestMethod.GET)
	public String manageGrade(Model model) {
		model.addAttribute("grades", gradeDao.readGrades());
		return "admin/gradeManager";
	}

	/**
	 * 관리자 페이지에서 카테고리 목록을 불러옴
	 * @author jyb
	 */
	@RequestMapping(value = "/manage/category", method = RequestMethod.GET)
	public String manageCategory(Model model) {
		model.addAttribute("categories", categoryService.read());
		return "admin/categoryManager";
	}

	/**
	 * 관리자 페이지에서 카테고리 이름 변경 기능
	 * @author jyb
	 * @param DB에 저장된 categoryId와 변경할 categoryName
	 * @return 성공/실패 여부만 전달하기 위해 success, fail string 전달
	 */
	@RequestMapping(value = "/manage/category/{categoryId}/{categoryName}", method = RequestMethod.PUT)
	public @ResponseBody String updateCategory(@PathVariable long categoryId, @PathVariable String categoryName) {
		logger.debug("Update CategoryId : {}, CategoryName : {}", categoryId, categoryName);
		if(categoryService.update(categoryId, categoryName)) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 관리자 페이지에서 카테고리 삭제 기능
	 * @author jyb
	 * @param DB에 저장된 categoryId
	 * @return 성공/실패 여부만 전달하기 위해 success, fail string 전달
	 */
	@RequestMapping(value = "/manage/category/{categoryId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCategory(@PathVariable long categoryId) {
		logger.debug("Delete CategoryId : {}", categoryId);
		if(categoryService.delete(categoryId)) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 관리자 페이지에서 카테고리 추가 기능
	 * @author jyb
	 * @return 성공/실패 여부만 전달하기 위해 success, fail string 전달
	 */
	@RequestMapping(value = "/manage/category", method = RequestMethod.POST)
	public @ResponseBody String createCategory(@RequestParam String categoryName) {
		logger.debug("Create CategoryName : {}", categoryName);
		if(categoryService.create(categoryName)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping(value = "/manage/product", method = RequestMethod.GET)
	public String manageProduct(Model model) {
		model.addAttribute("product", new Product());
		//model.addAttribute("productList", productsService.readUnclassifiedProducts());
		model.addAttribute("productList", productsService.readProducts());
		model.addAttribute("brandList", brandService.readBrands());
		model.addAttribute("categoryList", categoryService.read());
		return "admin/productManager";
	}
	
	
	@RequestMapping(value = "/manage/product", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAllProducts() {
		if(productsService.deleteAll()) {
			return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
		}
		return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
	}

	@RequestMapping(value = "/add/product", method = RequestMethod.GET)
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("brandList", brandService.readBrands());
		model.addAttribute("unregisteredProductsCountByBrand", productsService.unregisteredProductsCountByBrand());
		return "admin/productRegistration";
	}

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public String config() {
		return "admin/config";
	}
	
	@RequestMapping(value = "/manage/order", method = RequestMethod.GET)
	public String manageOrder() {
		return "admin/orderManager";
	}
}
