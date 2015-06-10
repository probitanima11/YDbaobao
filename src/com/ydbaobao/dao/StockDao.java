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

	public void create(Product product, Stock stock) {
		String sql = "insert into STOCKS(productId, size, quantity) values(?, ?, ?);";
		getJdbcTemplate().update(sql, product.getProductId(), stock.getSize(), stock.getQuantity());
	}
	
	public void createDefault(int productId) {
		String sql = "insert into STOCKS(productId) values(?);";
		getJdbcTemplate().update(sql, productId);
	}

	public void update(Stock stock) {
		String sql = "update STOCKS set size = ?, quantity=?, stockUpdateDate=default  where stockId = ?";
		getJdbcTemplate().update(sql, stock.getSize(), stock.getQuantity(), stock.getStockId());
	}

}
