package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ydbaobao.model.Customer;
import com.ydbaobao.model.Item;
import com.ydbaobao.model.Order;
import com.ydbaobao.model.Product;

public class ItemDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Item> read(String customerId) {
		String sql = "select * from ITEMS where customerId=?";
		try {
			return getJdbcTemplate().query(sql, (rs, rowNum) -> new Item(
					rs.getInt("itemId"),
					new Customer(rs.getString("customerId")),
					new Product(rs.getInt("productId")),
					new Order(rs.getInt("orderId")),
					rs.getString("size"),
					rs.getInt("quantity")
					), customerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void delete() {

	}

	public void add() {

	}
}
