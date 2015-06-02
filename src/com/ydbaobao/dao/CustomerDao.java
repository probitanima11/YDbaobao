package com.ydbaobao.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Customer;

@Repository
public class CustomerDao extends JdbcDaoSupport {
	private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);
	
	@Resource
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public void createCustomer(Customer customer) {
		String sql = "insert into CUSTOMERS values(?, ?, ?, default, ?, ?, ?, default, default)";
//		String sql = "insert into CUSTOMERS (customerId, customerName, customerPassword, customerPhone, customerEmail) values(?, ?, ?, ?)";
		getJdbcTemplate().update(sql, customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerPassword(), customer.getCustomerPhone(), customer.getCustomerEmail(), customer.getCustomerAddress());
	}
	
	public Customer findCustomerByCustomerId(String customerId) {
		String sql = "select * from CUSTOMERS where customerId=?";
		try {
			return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Customer(
					rs.getString("customerId"), 
					rs.getString("customerName"), 
					rs.getString("customerPassword"),
					rs.getString("gradeId"),
					rs.getString("customerPhone"),
					rs.getString("customerEmail")
					), customerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
