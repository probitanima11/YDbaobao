package com.ydbaobao.admincontroller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.model.AdminConfig;
import com.ydbaobao.service.AdminConfigService;

@Controller
@RequestMapping("/admin/config")
public class AdminConfigController {
	@Resource
	private AdminConfigService adminConfigService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String config(Model model) {
		model.addAttribute("adminConfig", adminConfigService.read());
		return "admin/config";
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String configUpdate(Model model, @RequestParam AdminConfig adminConfig) {
		model.addAttribute("adminConfig", adminConfigService.update(adminConfig));
		return "/admin/config";
	}
}
