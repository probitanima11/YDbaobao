package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Brand;
<<<<<<< HEAD
=======
import com.ydbaobao.model.Category;
>>>>>>> branch 'master' of https://github.com/HelloMocca/YDbaobao.git
import com.ydbaobao.model.Product;

@Repository
public class ProductsDao extends JdbcDaoSupport {

	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
<<<<<<< HEAD

=======
	
	public List<Product> readByCount(int count) {
		String sql ="select * from PRODUCTS ORDER BY productCreateDate ASC LIMIT ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate")), count);
	}
	
>>>>>>> branch 'master' of https://github.com/HelloMocca/YDbaobao.git
	public List<Product> readListByCategory(int categoryId) {
		String sql = "select * from PRODUCTS where categoryId=?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate")), categoryId);
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
