package com.ydbaobao.controller;

import java.io.File;
import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.support.CommonUtil;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.exception.JoinValidationException;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Customer;
import com.ydbaobao.model.SessionCustomer;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.CustomerService;
import com.ydbaobao.service.ProductService;

@Controller
public class HomeController {
	@Resource
	private CategoryService categoryService; 
	@Resource
	private CustomerService customerService;
	@Resource
	private BrandService brandService;
	@Resource
	private ProductService productService;
	@Resource
	private AdminConfigService adminConfigService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, WebRequest req) {
		int totalPage = CommonUtil.countTotalPage(productService.count(), CommonUtil.PRODUCTS_PER_PAGE);
		
		model.addAttribute("prev", CommonUtil.prevBlock(1));
		model.addAttribute("next", CommonUtil.nextBlock(1, totalPage));
		model.addAttribute("selectedIndex", 1);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(1), CommonUtil.endPage(1, totalPage)).toArray());
		model.addAttribute("url", "/index?page=");
		model.addAttribute("products", productService.readRange(1, adminConfigService.read().getAdminDisplayProducts()));
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		
		StringBuilder imgPath = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			String filePath = "index_0" + i + ".jpg";
			File f = new File("/home/baobao/index/"+filePath);
			if (f.isFile()) {
				imgPath.append(",/img/index/"+filePath);
			} 
		}
		model.addAttribute("imgPath", imgPath);
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String homeWithPage(Model model, @RequestParam int page) {
		int totalPage = CommonUtil.countTotalPage(productService.count(), CommonUtil.PRODUCTS_PER_PAGE);
		
		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		model.addAttribute("url", "/index?page=");
		model.addAttribute("products", productService.readRange(page, adminConfigService.read().getAdminDisplayProducts()));
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		
		StringBuilder imgPath = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			String filePath = "index_0" + i + ".jpg";
			File f = new File("/home/baobao/index/"+filePath);
			if (f.isFile()) {
				imgPath.append(",/img/index/"+filePath);
			} 
		}
		model.addAttribute("imgPath", imgPath);
		return "index";
	}
	
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginView(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected String login(@RequestParam String customerId, @RequestParam String customerPassword, HttpSession session, Model model) throws ExceptionForMessage {
		SessionCustomer sessionCustomer = (customerService.login(customerId, customerPassword)).createSessionCustomer();
		session.setAttribute("sessionCustomer", sessionCustomer);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		return "form";
	}
	
	@RequestMapping(value ="/join", method = RequestMethod.POST)
	public String create(@Valid Customer customer, BindingResult result, @RequestParam String customerAgainPassword, Model model) throws ExceptionForMessage{
		if(result.hasErrors()) {
			throw new JoinValidationException(CommonUtil.extractValidationMessages(result));
        }
		if(!customer.getCustomerPassword().equals(customerAgainPassword)) {
			model.addAttribute("customer", new Customer());
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "form";
		}
		customerService.join(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	protected String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
