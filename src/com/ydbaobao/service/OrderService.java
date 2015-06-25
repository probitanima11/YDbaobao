package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.dao.OrderDao;
import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.model.Item;
import com.ydbaobao.model.Order;
import com.ydbaobao.model.Product;

@Service
@Transactional
public class OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Resource
	OrderDao orderDao;
	@Resource
	ItemDao itemDao;
	@Resource
	ProductDao productDao;
	@Resource
	ProductService productService;
	
	/**
	 * 주문 목록 리스트만 받아오기
	 * @return
	 */
	//TODO 기간별로 받아오게 변경 요망
	public List<Order> readOrdersListOnly() {
		return orderDao.readOrders();
	}
	
	/**
	 * 주문서 생성
	 * @param customerId
	 * @param itemList
	 */
	public void createOrder(String customerId, int[] itemList) {
		int totalPrice = 0;
		for(int i=0; i<itemList.length; i++) {
			Item item = itemDao.readItemByItemId(itemList[i]);
			Product product = productDao.read(item.getProduct().getProductId());
			int price = productService.readByDiscount(product, item.getCustomer()).getProductPrice();
			totalPrice += price * item.getQuantity();
		}
		int orderId = orderDao.createOrder(customerId, totalPrice);
		itemDao.orderItems(orderId, itemList);
	}
	
	/**
	 * 주문서 상태 변경(승인, 반려, 취소)
	 * @param customerId
	 * @param itemList
	 */
	public void updateOrder(int orderId, String orderStatus) {
		logger.debug("orderId: {}, orderStatus: {}", orderId, orderStatus);
		orderDao.updateOrder(orderId, orderStatus);
	}
	
	/**
	 * 고객의 주문 내역 받아오기
	 * @param customerId
	 * @return
	 */
	//TODO 기간별로 받아오게 변경 요망
	public List<Order> readOrdersByCustomerId(String customerId) {
		List<Order> orders = orderDao.readOrdersByCustomerId(customerId);
		setItems(orders);
		return orders;
	}

	private void setItems(List<Order> orders) {
		for(Order order : orders) {
			List<Item> items = itemDao.readItemsByOrderId(order.getOrderId());
			setDiscountedPrice(items);
			order.setItems(itemDao.readItemsByOrderId(order.getOrderId()));
		}
	}

	private void setDiscountedPrice(List<Item> items) {
		for (Item item : items) {
			Product product = item.getProduct();
			product.setProductPrice(productService.readByDiscount(product, item.getCustomer()).getProductPrice());
		}
	}

	/**
	 * 주문 내역 상세보기
	 * @param orderId
	 * @return
	 */
	public Order readOrder(int orderId) {
		Order order = orderDao.readOrder(orderId);
		List<Item> items = itemDao.readItemsByOrderId(orderId);
		order.setItems(items);
		return order;
	}
	
	public Item readItemByItemId(int itemId) {
		Item item = itemDao.readItemByItemId(itemId);
		Product product = item.getProduct(); 
		product.setProductPrice(productService.readByDiscount(product, item.getCustomer()).getProductPrice());
		return item;
	}

	public void createOrderDirectly() {
	}

}
