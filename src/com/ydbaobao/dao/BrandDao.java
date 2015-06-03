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

import com.ydbaobao.model.Brand;

@Repository
public class BrandDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Brand> readBrands() {
		String sql = "select * from BRANDS";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Brand(rs.getInt("brandId"), rs.getString("brandName")));
	}

	public int createBrand(String brandName) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into BRANDS(brandName) values(?)";
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, brandName);
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
					rs.getString("brandName")), brandName);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void updateBrand(String brandId, String brandName) {
		String sql = "update BRANDS set brandName = ? where brandId = ?";
		getJdbcTemplate().update(sql, brandName, brandId);
	}

	public void deleteBrand(String brandId) {
		String sql = "delete from BRANDS where brandId = ?";
		getJdbcTemplate().update(sql, brandId);
	}
	
	public List<Brand> read() {
		String sql = "select * from BRANDS";
		return getJdbcTemplate().query(sql, (rs, rowNum) -> new Brand(
				rs.getInt("brandId"),
				rs.getString("brandName")));
	}

	public List<Brand> search(String firstLetter) {
		String sql = "select * from BRANDS where brandName like \"" + firstLetter + "%\"";
		return getJdbcTemplate().query(sql, (rs, rowNum) -> new Brand(
				rs.getInt("brandId"), 
				rs.getString("brandName")));
	}
}
