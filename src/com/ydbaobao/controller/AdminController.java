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

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private static String password = "1111";

	@Resource
	private CategoryService categoryService;
	
	@Resource
	private BrandService brandService;
	
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
		return mv;
	}

	@RequestMapping(value = "/manage/brand", method = RequestMethod.GET)
	public ModelAndView manageBrand() {
		ModelAndView mv = new ModelAndView("admin/brandManager");
		mv.addObject("brands", brandService.readBrands());
		return mv;
	}

	@RequestMapping(value = "/manage/category", method = RequestMethod.GET)
	public ModelAndView manageCategory() {
		ModelAndView mv = new ModelAndView("admin/categoryManager");
		List<Category> list = categoryService.read();
		mv.addObject("categories", list);
		return mv;
	}

	@RequestMapping(value = "/manage/category/{categoryId}/{categoryName}", method = RequestMethod.PUT)
	public @ResponseBody String updateCategory(@PathVariable long categoryId, @PathVariable String categoryName) {
		logger.debug("Update CategoryId : {}, CategoryName : {}", categoryId, categoryName);
		if(categoryService.update(categoryId, categoryName)) {
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping(value = "/manage/category/{categoryId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCategory(@PathVariable long categoryId) {
		logger.debug("Delete CategoryId : {}", categoryId);
		if(categoryService.delete(categoryId)) {
			return "success";
		}
		return "fail";
	}
	
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
		return mv;
	}
	

	@RequestMapping(value = "/add/product", method = RequestMethod.GET)
	public ModelAndView addProduct() {
		ModelAndView mv = new ModelAndView("admin/productRegistration");
		mv.addObject("brandList", brandService.readBrands());
		mv.addObject("product", new Product());
		return mv;
	}

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public ModelAndView config() {
		ModelAndView mv = new ModelAndView("admin/config");
		return mv;
	}
}
