package com.ydbaobao.admincontroller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.dao.GradeDao;
import com.ydbaobao.model.AdminConfig;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.CustomerService;
import com.ydbaobao.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static String password = "1111";

	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;
	@Resource
	private CustomerService customerService;
	@Resource
	private ProductService productService;
	@Resource
	private AdminConfigService adminConfigService;
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
	
	@RequestMapping(value = "/manage/grade", method = RequestMethod.GET)
	public String manageGrade(Model model) {
		model.addAttribute("grades", gradeDao.readGrades());
		return "admin/gradeManager";
	}

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public String config(Model model) {
		model.addAttribute("adminConfig", adminConfigService.read());
		return "admin/config";
	}
	
	@RequestMapping(value = "/config/update", method = RequestMethod.PUT)
	public String configUpdate(Model model, @RequestParam AdminConfig adminConfig) {
		model.addAttribute("adminConfig", adminConfigService.update(adminConfig));
		return "/admin/config";
	}
}
