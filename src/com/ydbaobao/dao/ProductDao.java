package com.ydbaobao.dao;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;
import com.ydbaobao.model.Stock;

@Repository
public class ProductDao extends JdbcDaoSupport {
	
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public Product read(int productId) {
		String sql = "select * from PRODUCTS where productId = ?";
		return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), productId);
	}
}
