package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;

@Repository
public class ProductsDao extends JdbcDaoSupport {
	
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public void create(Product product) {
		String sql = "insert into PRODUCTS values(default, ?, ?, ?, default, ?, default, default, default)";
		getJdbcTemplate().update(sql, 
				product.getProductName(), 
				product.getCategory().getCategoryId(),
				product.getBrand().getBrandId(),
				product.getProductImage());
	}
	
	public List<Product> readRange(int start, int quantity) {
		String sql ="select * from PRODUCTS ORDER BY productId DESC LIMIT ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate")), start, quantity);
	}

	public List<Product> readListByCategoryId(int categoryId, int index, int quantity) {
		String sql = "select * from PRODUCTS where categoryId=? ORDER BY productId DESC limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate")), categoryId, index, quantity);
	}
	
	public List<Product> readListByCategoryId(int categoryId) {
		String sql = "select * from PRODUCTS where categoryId=? limit ?, ?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Product(
						rs.getInt("productId"), rs.getString("productName"),
						new Category(rs.getInt("categoryId"), null),
						new Brand(rs.getInt("brandId"), null),
						rs.getInt("productPrice"), rs.getString("productImage"),
						rs.getString("productDescription"), rs.getLong("productCreateDate"),
						rs.getLong("productUpdateDate")), categoryId);
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
						rs.getLong("productUpdateDate")), brandId);
	}
	
	public boolean isExistImageName(String imageName) {
		String sql = "select count(1) from PRODUCTS where productImage = ?";
		if (getJdbcTemplate().queryForObject(sql, Integer.class, imageName) == 0) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
