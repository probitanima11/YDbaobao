package com.ydbaobao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
public class ProductsDao extends JdbcDaoSupport {
	
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
	
	public List<Product> readRange(int start, int quantity) {
		String sql ="select * from PRODUCTS ORDER BY productCreateDate ASC LIMIT ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), start, quantity);
	}

	public List<Product> readListByCategoryId(int categoryId, int index, int quantity) {
		String sql = "select * from PRODUCTS where categoryId=? limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), categoryId, index, quantity);
	}
	
	public List<Product> readListByCategoryId(int categoryId) {
		String sql = "select * from PRODUCTS where categoryId=?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), categoryId);
	}
	
	public int count() {
		String sql = "select count(1) from PRODUCTS";
		return getJdbcTemplate().queryForObject(sql, Integer.class);
	}
	
	public int countByCategoryId(int categoryId) {
		String sql = "select count(1) from PRODUCTS where categoryId=?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, categoryId);
	}

	/**
	 * brand 이름 클릭시 brandId로 상품 검색
	 * @author jyb
	 * @param 검색에 사용될 브랜드Id
	 * @return DB에 저장된 Product 결과를 List<Product>로 반환
	 */
	public List<Product> readListByBrandId(int brandId) {
		String sql = "select * from PRODUCTS where brandId = ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), brandId);
	}
	
	public boolean isExistImageName(String imageName) {
		String sql = "select count(1) from PRODUCTS where productImage = ?";
		if (getJdbcTemplate().queryForObject(sql, Integer.class, imageName) == 0) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public Integer unregisteredProductsCountByBrand(int brandId) {
		String sql = "select count(1) from PRODUCTS where brandId=?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, brandId);
	}

	public int updateProductImage(int productId, String imageName) {
		String sql = "update PRODUCTS set productImage = ? where productId = ?";
		return getJdbcTemplate().update(sql, imageName, productId);
	}

}
