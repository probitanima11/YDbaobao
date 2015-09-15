package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.domain.IndexImage;

@Repository
public class AdminIndexImageDao extends JdbcDaoSupport {

	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<IndexImage> readIndexImages() {
		String sql = "select * from INDEXIMAGES";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new IndexImage(rs.getInt("indexImageId")
						, rs.getString("indexImageName")));
	}

	public void create(String fileName) {
		String sql = "insert into INDEXIMAGES(indexImageName) values(?)";
		getJdbcTemplate().update(sql, fileName);
	}

	public void delete(int indexImageId) {
		String sql = "delete from INDEXIMAGES where indexImageId = ?";
		getJdbcTemplate().update(sql, indexImageId);
	}
}
