package com.ydbaobao.dao;

import java.util.List;

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

	public List<Order> readOrders(String customerId) {
		String sql = "select * from ORDERS where customerId = ?";
		return getJdbcTemplate().query(
				sql,
				(rs, rowNum) -> new Order(rs.getInt("orderId"), rs
						.getString("orderStatus"), new Customer(rs
						.getString("customerId")), rs.getInt("enuri"), rs
						.getInt("realPrice"), rs.getString("orderAddress")),
				customerId);
	}
	
	public Order readOrder(String customerId) {
		String sql = "select * from ORDERS where customerId = ?";
		return getJdbcTemplate().queryForObject(
				sql, (rs, rowNum) -> new Order(rs.getInt("orderId"), rs
						.getString("orderStatus"), new Customer(rs
								.getString("customerId")), rs.getInt("enuri"), rs
								.getInt("realPrice"), rs.getString("orderAddress")),
								customerId);
	}
}
