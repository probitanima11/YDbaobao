package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Customer;

@Repository
public class CustomerDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public void createCustomer(Customer customer) {
		String sql = "insert into CUSTOMERS values(?, ?, ?, default, ?, ?, ?, default, default)";
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
					rs.getString("customerEmail"),
					rs.getString("customerAddress")
					), customerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateCustomer(Customer customer) {
		String sql = "update CUSTOMERS set customerName = ?, customerPassword = ?, gradeId = ?, customerPhone = ?, customerEmail = ?, customerAddress = ?, customerUpdateDate = default where customerId = ?";
		getJdbcTemplate().update(sql, customer.getCustomerName(), customer.getCustomerPassword(), customer.getCustomerGrade(), customer.getCustomerPhone(), customer.getCustomerEmail(), customer.getCustomerAddress(), customer.getCustomerId());		
	}
	
	public List<Customer> readCustomers() {
		String sql = "select * from CUSTOMERS";
		try {
			return getJdbcTemplate().query(sql, (rs, rowNum) -> new Customer(
					rs.getString("customerId"),
					rs.getString("customerName"), 
					rs.getString("customerPassword"),
					rs.getString("gradeId"),
					rs.getString("customerPhone"),
					rs.getString("customerEmail"),
					rs.getString("customerAddress"),
					rs.getString("customerCreateDate")
					));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Customer readCustomerById(String customerId) {
		String sql = "select *from CUSTOMERS where customerId = ?";
		
		return getJdbcTemplate().queryForObject(sql,  (rs, rowNum) -> new Customer(
				rs.getString("customerId"),
				rs.getString("customerName"), 
				rs.getString("customerPassword"),
				rs.getString("gradeId"),
				rs.getString("customerPhone"),
				rs.getString("customerEmail"),
				rs.getString("customerAddress"),
				rs.getString("customerCreateDate")
				), customerId);
	}

	public void deleteCustomer(String customerId) {
		String sql = "delete from CUSTOMERS where customerId = ?";
		getJdbcTemplate().update(sql, customerId);
		sql = "delete from ORDERS where customerId = ?";
		getJdbcTemplate().update(sql, customerId);
		sql = "delete from ITEMS where customerId = ?";
		getJdbcTemplate().update(sql, customerId);
	}
}
