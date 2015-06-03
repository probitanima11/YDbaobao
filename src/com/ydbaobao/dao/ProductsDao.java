package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Product;

@Repository
public class ProductsDao extends JdbcDaoSupport {

	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Product> readListByCategory(int categoryId) {
		String sql = "";
		return null;
	}

	public void create(Brand brand, Product product) {
		String sql = "insert into PRODUCTS values(default, ?, ?, ?, default, ?, default, default, default)";
		getJdbcTemplate().update(sql, 
				product.getProductName(), 
				product.getCategory().getCategoryId(),
				product.getBrand().getBrandId(),
				product.getProductImage());
	}
}
