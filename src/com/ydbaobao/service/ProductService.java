package com.ydbaobao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.dao.StockDao;
import com.ydbaobao.model.Product;

@Service
public class ProductService {
	
	@Resource
	ProductDao productDao;
	@Resource
	StockDao stockDao;
	
	public Product read(int productId) {
		Product product = productDao.read(productId);
		product.setStockList(stockDao.readListByProductId(productId));
		return product;
	}
}
