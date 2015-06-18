package com.ydbaobao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.dao.OrderDao;
import com.ydbaobao.model.Item;
import com.ydbaobao.model.Order;

@Service
public class OrderService {
	@Resource
	OrderDao orderDao;
	@Resource
	ItemDao itemDao;
	
	public List<Order> readOrders(String customerId) {
		List<Order> orders = orderDao.readOrders(customerId);
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
		int orderId = orderDao.createOrder(customerId);
		itemDao.orderItems(orderId, itemList);
	}
}
