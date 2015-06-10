package com.ydbaobao.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ydbaobao.model.Category;
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
	
	/**
	 * 관리자 페이지 접근을 위한 GET 요청을 응답.
	 * session 체크를 하여 sessionAdmin이 Null일 경우 관리자 번호 체크 페이지로 포워딩.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		if (session.getAttribute("sessionAdmin") == null) {
			mv.setViewName("admin/adminCheck");
			return mv;
		}
		mv.setViewName("admin/admin");
		return mv;
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView check() {
		ModelAndView mv = new ModelAndView("admin/adminCheck");
		return mv;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ModelAndView checkForm(@RequestParam String adminPassword, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (adminPassword.equals(password)) {
			session.setAttribute("sessionAdmin", adminPassword);
			mv.setViewName("admin/admin");
			return mv;
		}
		mv.setViewName("admin/adminCheck");
		return mv;
	}

	@RequestMapping(value = "/manage/member", method = RequestMethod.GET)
	public ModelAndView manageMember() {
		ModelAndView mv = new ModelAndView("admin/memberManager");
		mv.addObject("members", customerService.readCustomers());
		return mv;
	}
	
	/**
	 * 관리자 페이지에서 회원목록에서 회원상세보기 버튼 클릭시 동작
	 * @author jyb
	 * @param customerId
	 * @return WEB-INF/jsp/admin 폴더의 파일을 불러오기 위해 ModelAndView 타입 사용, 
	 * customerId로 검색한 결과를 customer 객체에 저장
	 */
	@RequestMapping(value = "/manage/member/{customerId}", method = RequestMethod.GET)
	public ModelAndView showDetailMember(@PathVariable String customerId) {
		logger.debug(customerId);
		ModelAndView mv = new ModelAndView("admin/memberDetail");
		mv.addObject("customer", customerService.readCustomerById(customerId));
		return mv;
	}

	@RequestMapping(value = "/manage/brand", method = RequestMethod.GET)
	public ModelAndView manageBrand() {
		ModelAndView mv = new ModelAndView("admin/brandManager");
		mv.addObject("brands", brandService.readBrands());
		return mv;
	}

	/**
	 * 관리자 페이지에서 카테고리 목록을 불러옴
	 * @author jyb
	 * @return WEB-INF/jsp/admin 폴더의 파일을 불러오기 위해 ModelAndView 타입 사용
	 */
	@RequestMapping(value = "/manage/category", method = RequestMethod.GET)
	public ModelAndView manageCategory() {
		ModelAndView mv = new ModelAndView("admin/categoryManager");
		List<Category> list = categoryService.read();
		mv.addObject("categories", list);
		return mv;
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
	public ModelAndView manageProduct() {
		ModelAndView mv = new ModelAndView("admin/productManager");
		mv.addObject("product", new Product());
		//mv.addObject("productList", productsService.readUnclassifiedProducts());
		mv.addObject("productList", productsService.readProducts());
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("categoryList", categoryService.read());
		return mv;
	}
	

	@RequestMapping(value = "/add/product", method = RequestMethod.GET)
	public ModelAndView addProduct() {
		ModelAndView mv = new ModelAndView("admin/productRegistration");
		mv.addObject("product", new Product());
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("unregisteredProductsCountByBrand", productsService.unregisteredProductsCountByBrand());
		return mv;
	}

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public ModelAndView config() {
		ModelAndView mv = new ModelAndView("admin/config");
		return mv;
	}
	
	@RequestMapping(value = "/manage/order", method = RequestMethod.GET)
	public ModelAndView manageOrder() {
		ModelAndView mv = new ModelAndView("admin/orderManager");
		return mv;
	}
}
