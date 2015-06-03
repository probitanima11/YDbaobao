package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

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
		String sql = "select categoryName from CATEGORY";
		return getJdbcTemplate().query(sql, (rs, rowNum) -> new Category(rs.getString("categoryName")));
	}
}
