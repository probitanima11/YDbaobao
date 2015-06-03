package com.ydbaobao.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.model.Customer;

@Service
public class CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Resource
	private CustomerDao customerDao;

	public void join(Customer customer) throws ExceptionForMessage{
		if(customerDao.findCustomerByCustomerId(customer.getCustomerId()) != null)
			throw new ExceptionForMessage("이미 존재하는 아이디 입니다.", "/form");
		customerDao.createCustomer(customer);
	}

	public Customer login(String customerId, String customerPassword) throws ExceptionForMessage{
		Customer customer = customerDao.findCustomerByCustomerId(customerId);
		
		if (customer == null) {
			throw new ExceptionForMessage("존재하지 않는 아이디 입니다.", "/login");
		}
		if(!customer.isCorrectPassword(customerPassword)){
			throw new ExceptionForMessage("비밀번호가 일치하지 않습니다.", "/login");
		}
		return customer;
	}

	public void update(Customer customer) {
		customerDao.updateCustomer(customer);
	}
}
