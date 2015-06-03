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
		return getJdbcTemplate().query(sql, (rs, rowNum) -> new Category(
				rs.getInt("categoryId"),
				rs.getString("categoryName")));
	}
	
	public int create(Category category) {
		String sql = "insert into CATEGORY(categoryName) values('?');";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
            new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[] {"categoryId"});
                    ps.setString(1, category.getCategoryName());
                    return ps;
                }
            },
            keyHolder);
        return keyHolder.getKey().intValue();
	}
	
	public Category readByCategoryId(int categoryId) {
		String sql = "select * from CATEGORY where categoryId = ?";
		return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Category(
				rs.getInt("categoryId"),
				rs.getString("categoryName")), categoryId);
	}
}
