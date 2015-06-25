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
import org.springframework.web.bind.annotation.PathVariable;
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
	 * 주문 정보 상세 조회
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Object> readOrder(@PathVariable int orderId) {
		return JSONResponseUtil.getJSONResponse(orderService.readOrder(orderId), HttpStatus.OK);
	}

	/**
	 * 장바구니에서 선택 된 아이템을 주문서 생성
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
	
	/**
	 * 주문 내역에 대한 상태 변경(취소)
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateOrder(@PathVariable int orderId, @RequestParam String orderStatus) {
		if (orderService.readOrder(orderId).getOrderStatus().equals('C')) {
			return JSONResponseUtil.getJSONResponse("이미 취소된 주문입니다.", HttpStatus.OK);
		}
		orderService.updateOrder(orderId, orderStatus);
		return JSONResponseUtil.getJSONResponse("주문상태변경완료", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String orderConfirm(@RequestParam int[] itemList, Model model) {
		List<Item> list = new ArrayList<Item>();
		for (int itemId : itemList) {
			list.add(orderService.readItemByItemId(itemId));
		}
		model.addAttribute("items", list);
		return "orderConfirm";
	}
	
	@RequestMapping(value = "/receipt/{orderId}", method = RequestMethod.GET)
	public String readReceipt(@PathVariable int orderId, HttpSession session, Model model) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("order", orderService.readOrder(orderId));
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		return "receipt";
	}
	
	//TODO 상품화면에서 주문하기
}
