package com.ydbaobao.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GradeDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public List<Map<String, Object>> readGrades() {
		String sql = "select * from GRADES order by gradeId";
		return getJdbcTemplate().queryForList(sql);
	}

	public void updateGrade(int gradeId, int discount) {
		String sql = "update GRADES set discount = ? where gradeId = ?";
		getJdbcTemplate().update(sql, discount, gradeId);
		
	}
}
