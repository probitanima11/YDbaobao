package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.ProductsDao;
import com.ydbaobao.model.Product;

@Service
public class ProductsService {
	@Resource
	private ProductsDao productsDao;
	
	public List<Product> readListByCategory(int categoryId) {
		return productsDao.readListByCategory(categoryId);
	}
}
