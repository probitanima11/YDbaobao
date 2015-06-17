package com.ydbaobao.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.AdminConfig;

@Repository
public class AdminConfigDao extends JdbcDaoSupport {

	@Resource
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public AdminConfig read() {
		String sql = "select * from ADMINCONFIG limit 1";
		return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new AdminConfig(rs.getInt("adminConfigId"), rs.getInt("adminDisplayProducts")));
	}
	
	public void update(AdminConfig adminConfig) {
		String sql = "update ADMINCONFIG set adminDisplayProducts = ?";
		getJdbcTemplate().update(sql, adminConfig.getAdminDisplayProducts());
	}
	
}
