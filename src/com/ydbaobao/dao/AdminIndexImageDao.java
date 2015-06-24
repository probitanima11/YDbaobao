package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AdminIndexImageDao extends JdbcDaoSupport {

	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<String> read() {
		String sql = "select * from ADMININDEXIMAGE";
		return getJdbcTemplate().query(sql, (rs, rowNum) -> new String(rs.getString("indexImageName")));
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void create() {
		// TODO Auto-generated method stub
		
	}

}
