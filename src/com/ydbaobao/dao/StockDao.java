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
public class StockDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
}
