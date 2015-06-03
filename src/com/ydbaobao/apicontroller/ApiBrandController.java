package com.ydbaobao.apicontroller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.service.BrandService;
import com.ydbaobao.util.JSONResponseUtil;

@Controller
@RequestMapping("/api/admin/manage/brand")
public class ApiBrandController {
	@Resource
	private BrandService brandService;
	
	@RequestMapping()
	public ResponseEntity<Object> show(Model model) {
		return JSONResponseUtil.getJSONResponse(brandService.readBrands(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestParam String brandName) {
		brandService.createBrand(brandName);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
//	@RequestMapping(value ="/create", method = RequestMethod.POST)
//	public String create(@RequestParam String customerId, @RequestParam String customerName, @RequestParam String customerPassword, @RequestParam String customerAgainPassword, @RequestParam String customerPhone, @RequestParam String customerEmail, @RequestParam String customerAddress) {
//		customerService.join(new Customer(customerId, customerName, customerPassword, customerPhone, customerEmail, customerAddress));
//		return "index";
//	}
//	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(Model model) {
//		model.addAttribute("customer", new Customer());
//		return "login";
//	}
//	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	protected String login(@RequestParam String customerId, @RequestParam String customerPassword, HttpSession session) {
//		SessionCustomer sessionCustomer = (customerService.login(customerId, customerPassword)).createSessionCustomer();
//		session.setAttribute("sessionCustomer", sessionCustomer);
//		return "index";
//	}
//	
//	@RequestMapping("/logout")
//	protected String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
}
