package com.ydbaobao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.JSONResponseUtil;
import com.support.ServletRequestUtil;
import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.model.Item;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ItemService;
import com.ydbaobao.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Resource
	private OrderService orderService;
	@Resource
	private ItemService itemService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ItemDao itemDao;

	/**
	 * 주문 내역 조회
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping()
	public String readOrders(HttpSession session, Model model) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("orders", orderService.readOrdersByCustomerId(customerId));
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		return "order";
	}

	/**
	 * 장바구니에서 선택 된 아이템 주문서 호출
	 * @param itemList
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createOrder(@RequestParam int[] itemList, HttpSession session) throws IOException{
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		orderService.createOrder(customerId, itemList);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String orderConfirm(@RequestParam int[] itemList, Model model) {
		List<Item> list = new ArrayList<Item>();
		for (int itemId : itemList) {
			list.add(orderService.readItemByItemId(itemId));
		}
		model.addAttribute("items", list);
		logger.debug("item list {}", list);
		return "orderConfirm";
	}
	
	//TODO 상품화면에서 주문하기
	//TODO 주문사항 변경하기
	//TODO 주문 취소하기
}
