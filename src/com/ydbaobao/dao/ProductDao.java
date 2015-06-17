package com.ydbaobao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

@Repository
public class ProductDao extends JdbcDaoSupport {
	
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public int create(Product product) {
		String sql = "insert into PRODUCTS values(default, ?, ?, ?, default, ?, default, default, default, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] {"productId"});
				ps.setString(1, product.getProductName());
				ps.setObject(2, product.getCategory().getCategoryId());
				ps.setObject(3, product.getBrand().getBrandId());
				ps.setString(4, product.getProductImage());
				ps.setString(5, product.getProductSize());
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
						new Brand(rs.getInt("brandId"), null, 0, 0, 0, 0, 0, 0, ""),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), rs.getString("productSize")), productId);
	}
	
	public int updateProductImage(int productId, String imageName) {
		String sql = "update PRODUCTS set productImage = ? where productId = ?";
		return getJdbcTemplate().update(sql, imageName, productId);
	}

	public int update(Product product) {
		String sql = "update PRODUCTS set productName = ?, categoryId = ?, brandId = ?, productPrice=?, productDescription=?, productUpdateDate=default, productSize=? where productId = ?";
		return getJdbcTemplate().update(sql, product.getProductName(), product.getCategory().getCategoryId(), product.getBrand().getBrandId(), product.getProductPrice(), product.getProductDescription(), product.getProductSize(), product.getProductId());
	}
	

	public List<Product> readRange(int start, int quantity) {
		String sql ="select * from PRODUCTS, brands where brands.brandId = PRODUCTS.brandId ORDER BY productId desc LIMIT ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), rs.getString("productSize")), start, quantity);
	}

	public List<Product> readListByCategoryId(int categoryId, int index, int quantity) {
		String sql = "select * from PRODUCTS, brands where products.brandId = brands.brandId and categoryId=? ORDER BY productId desc limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), rs.getString("productSize")), categoryId, index, quantity);
	}

	public List<Product> readListByCategoryId(int categoryId) {
		String sql = "select * from PRODUCTS, brands where products.brandId = brands.brandId and categoryId=? ORDER BY productId desc";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), rs.getString("productSize")), categoryId);
	}
	
	public List<Product> readByProductName(String query, int index, int quantity) {
		String sql = "select * from products, brands WHERE products.brandId = brands.brandId and productName REGEXP (?) order by productId desc limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), rs.getString("productSize")), query, index, quantity);
	}
	
	public List<Product> readByBrandName(String query, int index, int quantity) {
		String sql = "select * from products, brands WHERE products.brandId = brands.brandId and brandName REGEXP (?) order by productId desc limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), rs.getString("productSize")), query, index, quantity);
	}

	public int count() {
		String sql = "select count(1) from PRODUCTS";
		return getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	public int countBySearchProductName(String query) {
		String sql = "select count(1) as count from products WHERE productName REGEXP (?)";
		return getJdbcTemplate().queryForObject(sql, Integer.class, query);
	}
	
	public int countBySearchBrandName(String query) {
		String sql = "select count(1) as count from products, brands WHERE products.brandId = brands.brandId and brandName REGEXP (?)";
		return getJdbcTemplate().queryForObject(sql, Integer.class, query);
	}

	public List<Product> readListByBrandId(int brandId, int index, int quantity) {
		String sql = "select * from PRODUCTS, BRANDS where BRANDS.brandId = PRODUCTS.brandId and PRODUCTS.brandId=? ORDER BY productId DESC limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), rs.getString("productSize")), brandId, index, quantity);
	}

	public List<Product> readProductsList() {
		String sql = "select * from PRODUCTS ORDER BY productId DESC";
		return getJdbcTemplate()
				.query(sql,
						(rs, rowNum) -> new Product(rs.getInt("productId"), rs.getString("productName"), new Category(
								rs.getInt("categoryId"), null, 0), new Brand(rs.getInt("brandId"), null, 0, 0, 0, 0, 0, 0, ""), rs
								.getInt("productPrice"), rs.getString("productImage"), rs
								.getString("productDescription"), rs.getLong("productCreateDate"), rs
								.getLong("productUpdateDate"), rs.getString("productSize")));
	}

	public int deleteAll() {
		String sql = "delete from PRODUCTS";
		return getJdbcTemplate().update(sql);
	}

	public int delete(Product product) {
		String sql = "delete from PRODUCTS where productId=?";
		return getJdbcTemplate().update(sql, product.getProductId());
	}
}
