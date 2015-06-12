package com.ydbaobao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	
	public int create(Product product) {
		String sql = "insert into PRODUCTS values(default, ?, ?, ?, default, ?, default, default, default)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "noteId" });
				ps.setString(1, product.getProductName());
				ps.setObject(2, product.getCategory().getCategoryId());
				ps.setObject(3, product.getBrand().getBrandId());
				ps.setString(4, product.getProductImage());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public Product read(int productId) {
		String sql = "select * from PRODUCTS where productId = ?";
		return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), null, 0),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), productId);
	}
	
	public int updateProductImage(int productId, String imageName) {
		String sql = "update PRODUCTS set productImage = ? where productId = ?";
		return getJdbcTemplate().update(sql, imageName, productId);
	}

	public int update(Product product) {
		String sql = "update PRODUCTS set productName = ?, categoryId = ?, brandId = ?, productPrice=?, productDescription=?, productUpdateDate=default where productId = ?";
		return getJdbcTemplate().update(sql, product.getProductName(), product.getCategory().getCategoryId(), product.getBrand().getBrandId(), product.getProductPrice(), product.getProductDescription(), product.getProductId());
	}
}
