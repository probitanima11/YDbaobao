package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Product;
import com.ydbaobao.model.Stock;

@Repository
public class StockDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public List<Stock> readListByProductId(int productId) {
		String sql = "select * from STOCKS where productId=?";
		return getJdbcTemplate().query(
				sql, (rs, rowNum) -> new Stock(
						rs.getInt("stockId"), new Product(rs.getInt("productId")),
						rs.getString("size"), rs.getInt("quantity")), productId);
	}
}
