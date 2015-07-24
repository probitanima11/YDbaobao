package com.ydbaobao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Category;

@Repository
public class CategoryDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Category> read() {
		String sql = "select * from CATEGORY";
		RowMapper<Category> rm = new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Category(
						rs.getInt("categoryId"),
						rs.getString("categoryName"), rs.getInt("categoryCount"));
			}
		};
		return getJdbcTemplate().query(sql, rm);
	}

	public List<Category> readWithoutUnclassifiedCategory() {
		String sql = "select * from CATEGORY where categoryId not in('0')";
		RowMapper<Category> rm = new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Category(
						rs.getInt("categoryId"),
						rs.getString("categoryName"), rs.getInt("categoryCount"));
			}
		};
		return getJdbcTemplate().query(sql, rm);
	}
	
	
	public int update(long categoryId, String categoryName) {
		String sql = "update CATEGORY set categoryName = ? where categoryId = ?";
		return getJdbcTemplate().update(sql, categoryName, categoryId);
	}

	public int delete(long categoryId) {
		String sql = "delete from CATEGORY where categoryId = ?";
		return getJdbcTemplate().update(sql, categoryId);
	}
	
	public int create(String categoryName) {
		String sql = "insert into CATEGORY(categoryName) values(?);";
        return getJdbcTemplate().update(sql, categoryName);
	}
	
	public Category readByCategoryId(int categoryId) {
		String sql = "select * from CATEGORY where categoryId = ?";
		RowMapper<Category> rm = new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Category(
						rs.getInt("categoryId"),
						rs.getString("categoryName"), rs.getInt("categoryCount"));
			}
		};
		return getJdbcTemplate().queryForObject(sql, rm, categoryId);
	}
	
	public int increaseCount(long categoryId) {
		String sql = "update CATEGORY set categoryCount = categoryCount+1 where categoryId = ?";
		return getJdbcTemplate().update(sql, categoryId);
	}
	
	public int decreaseCount(long categoryId) {
		String sql = "update CATEGORY set categoryCount = categoryCount-1 where categoryId = ?";
		return getJdbcTemplate().update(sql, categoryId);
	}

	public void resetCount() {
		String sql = "update CATEGORY set categoryCount = 0";
		getJdbcTemplate().update(sql);
	}
}
