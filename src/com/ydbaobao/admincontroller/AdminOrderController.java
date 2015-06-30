package com.ydbaobao.admincontroller;

import javax.annotation.Resource;

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

import com.support.JSONResponseUtil;
import com.ydbaobao.service.ItemService;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
	private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
	
	@Resource
	private ItemService itemService;

	/**
	 * 관리자 화면에서 주문 목록 리스트만 받아오기
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manageOrder(Model model) {
		model.addAttribute("items", itemService.readOrderedItems());
		return "admin/orderManager";
	}
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public String manageCustomerOrder(@PathVariable String customerId, Model model) {
		model.addAttribute("items", itemService.readOrderedItemsByCustomerId(customerId));
		return "admin/orderManager";
	}

	/**
	 * 관지자 화면에서 주문 내역에 대한 상태 변경(승인, 반려)
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping(value = "/accept/{itemId}", method = RequestMethod.POST)
	public ResponseEntity<Object> acceptOrder(@PathVariable int itemId, @RequestParam int quantity) {
		if(!itemService.acceptOrder(itemId, quantity))
			return JSONResponseUtil.getJSONResponse("유효하지 않은 주문입니다.", HttpStatus.OK);
		return JSONResponseUtil.getJSONResponse("주문승인", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reject/{itemId}", method = RequestMethod.POST)
	public ResponseEntity<Object> rejectOrder(@PathVariable int itemId) {
		if(!itemService.rejectOrder(itemId))
			return JSONResponseUtil.getJSONResponse("유효하지 않은 주문입니다.", HttpStatus.OK);
		return JSONResponseUtil.getJSONResponse("주문반려", HttpStatus.OK);
	}
}
