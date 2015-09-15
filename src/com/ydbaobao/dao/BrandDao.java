package com.ydbaobao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ydbaobao.domain.Brand;

@Repository
public class BrandDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Brand> readBrands() {
		String sql = "select * from BRANDS order by brandName";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")));
	}
	
	public List<Brand> readBrandsByCategoryId(int categoryId) {
		String sql = "select distinct b.* from BRANDS as b, PRODUCTS as p where p.categoryId = ? and p.brandId = b.brandId order by brandName";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")), categoryId);
	}
	
	public List<Brand> findBrands(String searchValue) {
		String sql = "select * from BRANDS where brandName like \"%" + searchValue + "%\"";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")));
	}

	public int createBrand(Brand brand) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into BRANDS(brandName, discount_1, discount_2, discount_3, discount_4, discount_5, brandSize) values(?, ?, ?, ?, ?, ?, ?)";
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, brand.getBrandName());
				ps.setInt(2, brand.getDiscount_1());
				ps.setInt(3, brand.getDiscount_2());
				ps.setInt(4, brand.getDiscount_3());
				ps.setInt(5, brand.getDiscount_4());
				ps.setInt(6, brand.getDiscount_5());
				ps.setString(7, brand.getBrandSize());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public Brand readBrandByBrandName(String brandName) {
		String sql = "select * from BRANDS where brandName=?";
		try {
			return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Brand(
					rs.getInt("brandId"), 
					rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")), brandName);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void updateBrand(Brand brand) {
		String sql = "update BRANDS set brandName = ?, discount_1 = ?, discount_2 = ?, discount_3 = ?, discount_4 = ?, discount_5 = ?, brandSize = ? where brandId = ?";
		getJdbcTemplate().update(sql, brand.getBrandName(), brand.getDiscount_1(), brand.getDiscount_2(), brand.getDiscount_3(), brand.getDiscount_4(), brand.getDiscount_5(), brand.getBrandSize(), brand.getBrandId());
	}

	public void deleteBrand(String brandId) {
		String sql = "delete from BRANDS where brandId = ?";
		getJdbcTemplate().update(sql, brandId);
	}
	
	public Brand readBrandByBrandId(int brandId) {
		String sql = "select * from BRANDS where brandId=?";
		try {
			return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Brand(
					rs.getInt("brandId"), 
					rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")), brandId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Brand readBrandByProductId(int productId) {
		String sql = "select * from BRANDS where productId=?";
		try {
			return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Brand(
					rs.getInt("brandId"), 
					rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")), productId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Brand> search(String firstLetter) {
		String sql = "select * from BRANDS where brandName like \"" + firstLetter + "%\" order by brandName";
		return getJdbcTemplate().query(sql, (rs, rowNum) -> new Brand(
				rs.getInt("brandId"), 
				rs.getString("brandName"), rs.getInt("brandCount"), rs.getInt("discount_1"), rs.getInt("discount_2"), rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"), rs.getString("brandSize")));
	}
	
	public int increaseCount(long brandId) {
		String sql = "update BRANDS set brandCount = brandCount+1 where brandId = ?";
		return getJdbcTemplate().update(sql, brandId);
	}
	
	public int decreaseCount(long brandId) {
		String sql = "update BRANDS set brandCount = brandCount-1 where brandId = ?";
		return getJdbcTemplate().update(sql, brandId);
	}

	public void resetCount() {
		String sql = "update BRANDS set brandCount = 0";
		getJdbcTemplate().update(sql);
	}
}
