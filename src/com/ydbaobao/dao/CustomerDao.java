package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.domain.Customer;

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

	public int updateCustomer(Customer customer) {
		String sql = "update CUSTOMERS set customerName = ?, customerPassword = ?, customerPhone = ?, customerEmail = ?, customerAddress = ?, customerUpdateDate = default where customerId = ?";
		return getJdbcTemplate().update(sql, customer.getCustomerName(), customer.getCustomerPassword(), customer.getCustomerPhone(), customer.getCustomerEmail(), customer.getCustomerAddress(), customer.getCustomerId());		
	}
	
	public int updateCustomerGrade(Customer customer) {
		String sql = "update CUSTOMERS set gradeId = ? where customerId = ?";
		return getJdbcTemplate().update(sql, customer.getCustomerGrade(), customer.getCustomerId());		
	}
	
	public List<Customer> readCustomers() {
		String sql = "select * from CUSTOMERS order by customerCreateDate DESC";
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

	//유저 계정 비활성화
	public void deleteCustomer(String customerId) {
		String sql = "update CUSTOMERS set gradeId = 0, customerPhone='', customerEmail='', customerAddress='' where customerId = ?";
		getJdbcTemplate().update(sql, customerId);
		sql = "delete from ITEMS where customerId = ?";
		getJdbcTemplate().update(sql, customerId);
	}
	
	/**
	 * offset부터 customersPerPage만큼 상품 수를 불러온다
	 * @param offset, customersPerPage
	 * @param customersPerPage
	 * @return CUSTOMERS table에서 offset에서부터 customersPerPage 만큼 가져온 Customers
	 */
	public List<Customer> readRange(String termsForQuery, int page, int customersPerPage) {
		String sql ="select * from CUSTOMERS where customerName REGEXP (?) ORDER BY customerName LIMIT ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Customer(
						rs.getString("customerId"),
						rs.getString("customerName"), 
						rs.getString("customerPassword"),
						rs.getString("gradeId"),
						rs.getString("customerPhone"),
						rs.getString("customerEmail"),
						rs.getString("customerAddress"),
						rs.getString("customerCreateDate")), termsForQuery, page, customersPerPage);
	}

	public int countBySearchCustomerName(String termsForQuery) {
		String sql = "select count(1) as count from CUSTOMERS where customerName REGEXP (?)";
		return getJdbcTemplate().queryForObject(sql, Integer.class, termsForQuery);
	}

	public int countCustomers() {
		String sql = "select count(1) as count from CUSTOMERS";
		return getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	public List<Customer> readCustomers(int page, int customersPerPage) {
		String sql ="select * from CUSTOMERS ORDER BY customerCreateDate DESC LIMIT ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Customer(
						rs.getString("customerId"),
						rs.getString("customerName"), 
						rs.getString("customerPassword"),
						rs.getString("gradeId"),
						rs.getString("customerPhone"),
						rs.getString("customerEmail"),
						rs.getString("customerAddress"),
						rs.getString("customerCreateDate")), page, customersPerPage);
	}

	public int countBySearchCustomerId(String terms) {
		String sql = "select count(1) as count from CUSTOMERS where customerId REGEXP (?)";
		return getJdbcTemplate().queryForObject(sql, Integer.class, terms);
	}

	public List<Customer> readBySerchCustomerId(String termsForQuery, int page, int customersPerPage) {
		String sql ="select * from CUSTOMERS where customerId REGEXP (?)  ORDER BY customerCreateDate DESC LIMIT ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Customer(
						rs.getString("customerId"),
						rs.getString("customerName"), 
						rs.getString("customerPassword"),
						rs.getString("gradeId"),
						rs.getString("customerPhone"),
						rs.getString("customerEmail"),
						rs.getString("customerAddress"),
						rs.getString("customerCreateDate")), termsForQuery, page, customersPerPage);
	}
}
