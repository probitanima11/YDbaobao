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
import com.ydbaobao.domain.Item;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ItemService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Resource
	private ItemService itemService;
	@Resource
	private CategoryService categoryService;
	
	/**
	 * 주문내역 페이지 호출하기
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
	public String readOrder(HttpSession session, Model model) throws IOException {
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("items", itemService.readOrderedItemsByCustomerId(customerId));
		return "order";
	}
	
	/**
	 * 장바구니에서 주문요청 페이지호출
	 * @param itemList
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String orderConfirm(@RequestParam int[] itemList, Model model) {
		List<Item> list = new ArrayList<Item>();
		for (int itemId : itemList) {
			list.add(itemService.readItemByItemId(itemId));
		}
		model.addAttribute("items", list);
		return "orderConfirm";
	}
	
	/**
	 * 상품화면에서 주문요청
	 * @param session
	 * @param productId
	 * @param size
	 * @param quantity
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/direct", method = RequestMethod.POST)
	public ResponseEntity<Object> createOrderDirectly(HttpSession session,  @RequestParam int productId, @RequestParam List<String> size, @RequestParam List<Integer> quantity) throws IOException{
		logger.debug("상품화면에서 바로 주문하기");
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		itemService.createItems(customerId, size, quantity, productId, "S");
		return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
	}
	
	/**
	 * 주문요청 페이지에서 주문요청하기
	 * @param itemList
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createOrder(@RequestParam int[] itemList, HttpSession session) throws IOException{
		String customerId = ServletRequestUtil.getCustomerIdFromSession(session);
		itemService.requestItems(customerId, itemList);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
}
