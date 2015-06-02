package com.ydbaobao.servece;

import javax.annotation.Resource;

import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.model.Customer;

public class CustomerService {
	@Resource
	private CustomerDao customerDao;

	public void join(Customer customer) {
		customerDao.createCustomer(customer);
	}

}
