package com.ydbaobao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
	@Resource
	OrderDao orderDao;
	@Resource
	ItemDao itemDao;
	@Resource
	ProductDao productDao;
	
	public List<Order> readOrders() {
		return orderDao.readOrders();
	}
	
	public List<Order> readOrdersByCustomerId(String customerId) {
		List<Order> orders = orderDao.readOrdersByCustomerId(customerId);
		List<Item> items = itemDao.readOrderedItems(customerId);
		return repackOrders(orders, items);
	}
	
	private List<Order> repackOrders(List<Order> orders, List<Item> items) {
		Map<String,Order> mapOrders = new HashMap<String,Order>();
		for (Order order : orders)
			mapOrders.put(""+order.getOrderId(),order);
		
		for (Item item : items) {
			mapOrders.get(""+item.getOrder().getOrderId()).addItem(item);
		}
		return new ArrayList<Order>(mapOrders.values());
	}

	public Order readOrder(int orderId) {
		return orderDao.readOrder(orderId);
	}

	public void createOrder(String customerId, int[] itemList) {
		int totalPrice = 0;
		for(int i=0; i<itemList.length; i++) {
			Item item = itemDao.readItemByItemId(itemList[i]);
			Product product = productDao.read(item.getProduct().getProductId());
			totalPrice += product.getProductPrice() * item.getQuantity();
		}
		int orderId = orderDao.createOrder(customerId, totalPrice);
		itemDao.orderItems(orderId, itemList);
	}
}
