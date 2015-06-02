package com.ydbaobao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.exception.FailedLoginException;
import com.ydbaobao.model.Customer;

@Service
public class CustomerService {

	@Resource
	private CustomerDao customerDao;

	public void join(Customer customer) {
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
