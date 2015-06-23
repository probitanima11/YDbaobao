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
import com.ydbaobao.model.Payment;

@Repository
public class PaymentDao extends JdbcDaoSupport {

	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public int createPayment(Payment payment) {
		String sql = "insert into PAYMENTS value(default, ?, ?, ?, default)";
		if (payment.getPaymentDate() != null) {
			sql = "insert into PAYMENTS value(default, ?, ?, ?, ?)";
			return getJdbcTemplate().update(sql, payment.getCustomer().getCustomerId(), payment.getPaymentType(), payment.getAmount(), payment.getPaymentDate());
		}
		return getJdbcTemplate().update(sql, payment.getCustomer().getCustomerId(), payment.getPaymentType(), payment.getAmount());
	}

	public List<Payment> readPaymentsByCustomerId(String customerId) {
		String sql = "select *, DATE_FORMAT(PAYMENTS.paymentDate, '%Y-%c-%e') as paymentDate from PAYMENTS where customerId = ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Payment(
						new Customer(rs.getString("customerId")),
						rs.getString("paymentType"), 
						rs.getInt("amount"),
						rs.getString("paymentDate")), customerId);
	}
}