package com.ydbaobao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.model.Product;

@Service
public class ProductService {
	
	@Resource
	ProductDao productDao;
	
	public Product read(int productId) {
		return productDao.read(productId);
	}
}
