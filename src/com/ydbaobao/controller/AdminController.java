package com.ydbaobao.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ydbaobao.model.Category;
import com.ydbaobao.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static String password = "1111";
	
	@Resource
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		if(session.getAttribute("sessionAdmin") == null) {
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
		if(adminPassword.equals(password)) {
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
		return mv;
	}
	
	@RequestMapping(value = "/manage/category", method = RequestMethod.GET)
	public ModelAndView manageCategory() {
		ModelAndView mv = new ModelAndView("admin/categoryManager");
		List<Category> list = categoryService.read();
		mv.addObject("categories", list);
		return mv;
	}
	
	@RequestMapping(value = "/manage/product", method = RequestMethod.GET)
	public ModelAndView manageProduct() {
		ModelAndView mv = new ModelAndView("admin/productManager");
		return mv;
	}
	
	@RequestMapping(value = "/add/product", method = RequestMethod.GET)
	public ModelAndView addProduct() {
		ModelAndView mv = new ModelAndView("admin/productRegistration");
		return mv;
	}
}
