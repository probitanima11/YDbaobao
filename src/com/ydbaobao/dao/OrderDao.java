package com.ydbaobao.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Customer;
import com.ydbaobao.model.Order;

@Repository
public class OrderDao extends JdbcDaoSupport {

	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Map<String, Object>> readOrders(String customerId) {
		String sql = "select DATE_FORMAT(ORDERS.orderUpdateDate, '%Y-%c-%e') as orderUpdateDate, ORDERS.orderId, ORDERS.orderStatus, ORDERS.realPrice, ITEMS.itemId, PRODUCTS.productName from ORDERS, ITEMS, PRODUCTS where PRODUCTS.productId = ITEMS.productId and ORDERS.customerId = ? and ORDERS.orderId is not NULL and ORDERS.orderId = ITEMS.orderId;";
		return getJdbcTemplate().queryForList(sql,customerId);
	}
	
	public Order readOrder(int orderId) {
		String sql = "select * from ORDERS where orderId = ?";
		return getJdbcTemplate().queryForObject(
				sql, (rs, rowNum) -> new Order(rs.getInt("orderId"), rs
						.getString("orderStatus"), new Customer(rs
								.getString("customerId")), rs.getInt("enuri"), rs
								.getInt("realPrice"), rs.getString("orderAddress")),
								orderId);
	}
}
