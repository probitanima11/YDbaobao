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
	
	public List<Product> readByProductName(String query, int index, int quantity) {
		String sql = "select * from products WHERE productName REGEXP (?) order by productId desc limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), query, index, quantity);
	}
	
	public List<Product> readByBrandName(String query, int index, int quantity) {
		String sql = "select * from products, brands WHERE products.brandId = brands.brandId and brandName REGEXP (?) order by productId desc limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate"), new ArrayList<Stock>()), query, index, quantity);
	}

	public int count() {
		String sql = "select count(1) from PRODUCTS";
		return getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	public int countByCategoryId(int categoryId) {
		String sql = "select count(1) from PRODUCTS where categoryId=?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, categoryId);
	}
	
	public int countBySearchProductName(String query) {
		String sql = "select count(1) as count from products WHERE productName REGEXP (?)";
		return getJdbcTemplate().queryForObject(sql, Integer.class, query);
	}
	
	public int countBySearchBrandName(String query) {
		String sql = "select count(1) as count from products, brands WHERE products.brandId = brands.brandId and brandName REGEXP (?)";
		return getJdbcTemplate().queryForObject(sql, Integer.class, query);
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

	public Integer unregisteredProductsCountByBrand(int brandId) {
		String sql = "select count(1) from PRODUCTS where brandId=? and categoryId=0";
		return getJdbcTemplate().queryForObject(sql, Integer.class, brandId);
	}

	public List<Product> readProductsList() {
		String sql = "select * from PRODUCTS ORDER BY productId DESC";
		return getJdbcTemplate()
				.query(sql,
						(rs, rowNum) -> new Product(rs.getInt("productId"), rs.getString("productName"), new Category(
								rs.getInt("categoryId"), null), new Brand(rs.getInt("brandId"), null), rs
								.getInt("productPrice"), rs.getString("productImage"), rs
								.getString("productDescription"), rs.getLong("productCreateDate"), rs
								.getLong("productUpdateDate"), new ArrayList<Stock>()));
	}
}
