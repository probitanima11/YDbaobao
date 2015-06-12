package com.ydbaobao.dao;

import java.util.ArrayList;
import java.util.List;

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
public class ProductsDao extends JdbcDaoSupport {

	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Product> readRange(int start, int quantity) {
		String sql ="select * from PRODUCTS, brands where brands.brandId = PRODUCTS.brandId ORDER BY productId desc LIMIT ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), start, quantity);
	}

	public List<Product> readListByCategoryId(int categoryId, int index, int quantity) {
		String sql = "select * from PRODUCTS, brands where products.brandId = brands.brandId and categoryId=? ORDER BY productId desc limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), categoryId, index, quantity);
	}

	public List<Product> readListByCategoryId(int categoryId) {
		String sql = "select * from PRODUCTS, brands where products.brandId = brands.brandId and categoryId=? ORDER BY productId desc";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), categoryId);
	}
	
	public List<Product> readByProductName(String query, int index, int quantity) {
		String sql = "select * from products, brands WHERE products.brandId = brands.brandId and productName REGEXP (?) order by productId desc limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), query, index, quantity);
	}
	
	public List<Product> readByBrandName(String query, int index, int quantity) {
		String sql = "select * from products, brands WHERE products.brandId = brands.brandId and brandName REGEXP (?) order by productId desc limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null, 0),
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), query, index, quantity);
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
						new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5")),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), brandId, index, quantity);
	}

	public Integer unregisteredProductsCountByBrand(int brandId) {
		String sql = "select count(1) from PRODUCTS where brandId=? and categoryId=0";
		return getJdbcTemplate().queryForObject(sql, Integer.class, brandId);
	}

	public List<Product> readProductsList() {
		String sql = "select * from PRODUCTS ORDER BY productId DESC";
		return getJdbcTemplate()
				.query(sql,
						(rs, rowNum) -> new Product(rs.getInt("productId"), rs.getString("productName"), new Category(
								rs.getInt("categoryId"), null, 0), new Brand(rs.getInt("brandId"), null, 0, 0, 0, 0, 0, 0), rs
								.getInt("productPrice"), rs.getString("productImage"), rs
								.getString("productDescription"), rs.getLong("productCreateDate"), rs
								.getLong("productUpdateDate"), new ArrayList<Stock>()));
	}
}
