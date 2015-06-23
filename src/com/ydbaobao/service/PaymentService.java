package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.PaymentDao;
import com.ydbaobao.model.Payment;

@Service
@Transactional
public class PaymentService {
	@Resource
	PaymentDao paymentDao;
	
	public List<Payment> readPaymentsByCustomerId(String customerId) {
		return paymentDao.readPaymentsByCustomerId(customerId);
	}
	
	public boolean createPayment(Payment payment) {
		if (paymentDao.createPayment(payment) == 1) return true;
		return false;
	}
}
