package com.ydbaobao.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.CustomerDao;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.model.Customer;

@Service
@Transactional
public class CustomerService {
	@Resource
	private CustomerDao customerDao;
	@Resource
	private ItemService itemService;
	
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
		if(customer.getCustomerGrade().equals("0")){
			throw new ExceptionForMessage("비활성 상태의 계정입니다. 관리자의 승인이 필요합니다.", "/login");
		}
		return customer;
	}

	public void update(Customer customer) {
		customerDao.updateCustomer(customer);
	}
	
	public List<Customer> readCustomers(int page, int customersPerPage) {
		return customerDao.readCustomers((page-1) * customersPerPage, customersPerPage);
	}

	public Customer readCustomerById(String customerId) {
		return customerDao.readCustomerById(customerId);
	}

	public boolean updateGrade(String customerId, String grade) {
		Customer customer = customerDao.readCustomerById(customerId);
		if (grade.equals("0")) {
			delete(customerId);
		}
		customer.setCustomerGrade(grade);
		if(customerDao.updateCustomerGrade(customer) == 1) {
			itemService.updateItemPriceByCustomerId(customerId);
			return true;
		}
		return false;
	}

	public void delete(String customerId) {
		customerDao.deleteCustomer(customerId);
	}

	public Customer readCustomer(String customerId) {
		return customerDao.findCustomerByCustomerId(customerId);
	}

	public String preprocessingTerms(String terms) {
		Pattern pt = Pattern.compile("^\\s{1,}|\\s{1,}$");
		Matcher m = pt.matcher(terms);
		String query = m.replaceAll("").replaceAll(" ", "|");
		return query;
	}

	public List<Customer> readByCustomerName(String termsForQuery, int page, int customersPerPage) {
		return customerDao.readRange(termsForQuery, (page - 1) * customersPerPage, customersPerPage);
	}

	public int countBySearchCustomerName(String termsForQuery) {
		return customerDao.countBySearchCustomerName(termsForQuery);
	}

	public int countCustomers() {
		return customerDao.countCustomers();
	}

	public int countBySearchCustomerId(String terms) {
		return customerDao.countBySearchCustomerId(terms);
	}

	public List<Customer> readBySerchCustomerId(String termsForQuery, int page, int customersPerPage) {
		return customerDao.readBySerchCustomerId(termsForQuery, (page - 1) * customersPerPage, customersPerPage);
	}



}
