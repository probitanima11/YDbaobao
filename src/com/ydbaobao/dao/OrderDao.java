package com.ydbaobao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		String sql = "select *, DATE_FORMAT(ORDERS.orderUpdateDate, '%Y-%c-%e') as orderDate from ORDERS where customerId = ?"; 
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Order(
						rs.getInt("orderId"), 
						rs.getString("orderStatus"),
						new Customer(rs.getString("customerId")), 
						rs.getInt("enuri"), 
						rs.getInt("realPrice"), 
						rs.getString("orderAddress"),
						rs.getString("orderDate")), customerId);
	}
	
	public Order readOrder(int orderId) {
		String sql = "select * from ORDERS where orderId = ?";
		return getJdbcTemplate().queryForObject(
				sql, (rs, rowNum) -> new Order(rs.getInt("orderId"), rs
						.getString("orderStatus"), new Customer(rs
								.getString("customerId")), rs.getInt("enuri"), rs
								.getInt("realPrice"), rs.getString("orderUpdateDate"), rs.getString("orderAddress")),
								orderId);
	}

	public int createOrder(String customerId) {
		String sql = "insert into ORDERS values(default, '승인대기', ?, 0, 0, '', default, default)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				//TODO new String param 논의.(알파)
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "orderId" });
				ps.setString(1, customerId);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
}
