package com.ydbaobao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.exception.FailedLoginException;
import com.ydbaobao.model.Customer;

@Service
public class CustomerService {

	@Resource
	private CustomerDao customerDao;

	public void join(Customer customer) throws ExceptionForMessage{
		if(customerDao.findCustomerByCustomerId(customer.getCustomerId()) != null)
			throw new ExceptionForMessage("이미 존재하는 아이디 입니다.");
		customerDao.createCustomer(customer);
	}

	public Customer login(String customerId, String customerPassword) {
		Customer customer = customerDao.findCustomerByCustomerId(customerId);
		if (customer == null || !customer.isCorrectPassword(customerPassword)) {
			throw new FailedLoginException();
		}
		return customer;
	}
}
