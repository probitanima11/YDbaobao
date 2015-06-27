package com.ydbaobao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Customer;
import com.ydbaobao.model.Item;
import com.ydbaobao.model.Product;

@Repository
public class ItemDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	// int brandId, String brandName, int discount_1, int discount_2, int
	// discount_3, int discount_4, int discount_5
	public List<Item> readCartItems(String customerId) {
		String sql = "select * from ITEMS A, PRODUCTS B, CUSTOMERS as C, BRANDS as D where A.itemStatus is NULL AND A.customerId= ? AND A.productId = B.productId AND A.customerId = C.customerId AND B.brandId = D.brandId order by A.productId";
		try {
			return getJdbcTemplate().query(
					sql,
					(rs, rowNum) -> new Item(rs.getInt("itemId"),
							new Customer(rs.getString("customerId"), rs.getString("customerName"), rs.getString("gradeId")),
							new Product(rs.getInt("productId"), rs.getString("productName"), rs.getInt("productPrice"), rs.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"),
									new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("discount_1"), rs.getInt("discount_2"),
											rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"))),
							rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus")), customerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	
	public void deleteCartList(int itemId) {
		String sql = "delete from ITEMS where itemId = ?";
		getJdbcTemplate().update(sql, itemId);
	}
	
	public int createItemDirectly(String customerId, int productId, String size, int quantity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into ITEMS (customerId, productId, size, quantity) values(?, ?, ?, ?)";
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, customerId);
				ps.setInt(2, productId);
				ps.setString(3, size);
				ps.setInt(4, quantity);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public void createItem(String customerId, int productId, String size, int quantity) {
		String sql = "insert into ITEMS (customerId, productId, size, quantity) values(?, ?, ?, ?)";
		getJdbcTemplate().update(sql, customerId, productId, size, quantity);
	}

	public Item readItemByItemId(int itemId) {
		String sql = "select * from ITEMS A, PRODUCTS B where A.itemId=? AND A.productId = B.productId";
		return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Item(
				rs.getInt("itemId"), new Customer(rs.getString("customerId")),
				new Product(rs.getInt("productId"),rs.getString("productName"), rs.getInt("productPrice"), rs.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"), new Brand(rs.getInt("brandId"))),
				rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus")), itemId);
	}

	public List<Item> readOrderedItems(String customerId) {
		String sql = "select * from ITEMS A, PRODUCTS B where A.customerId= ? and A.orderId is not NULL AND A.productId = B.productId order by A.orderId";
		try {
			return getJdbcTemplate().query(
					sql,
					(rs, rowNum) -> new Item(rs.getInt("itemId"), new Customer(rs.getString("customerId")),
							new Product(rs.getInt("productId"), rs.getString("productName"), rs.getInt("productPrice"),
									rs.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"), new Brand(rs.getInt("brandId"))), rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus")), customerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void orderItems(int orderId, int[] itemList) {
		String sql = "update ITEMS set orderId = ? where itemId = " + itemList[0];
		for (int i=1; i<itemList.length; i++) {
			sql += " or itemId = " + itemList[i];
		}
		getJdbcTemplate().update(sql, orderId);
	}

	public void orderDirect(String customerId, String productId, int orderId, String size, int quantity) {
		String sql = "insert into ITEMS (customerId, productId, orderId, size, quantity) values(?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql, customerId, productId, orderId, size, quantity);		
	}

	public List<Item> readItemsByOrderId(int orderId) {
		String sql = "select * from ITEMS A, PRODUCTS B where A.productId = B.productId AND orderId = ?";
		return getJdbcTemplate().query(
				sql,
				(rs, rownum) -> new Item(rs.getInt("itemId"), new Customer(rs.getString("customerId")), new Product(rs
						.getInt("productId"), rs.getString("productName"), rs.getInt("productPrice"), rs
						.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"), new Brand(rs.getInt("brandId"))), rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus")), orderId);
	}
	
	public boolean isItemByProductIdAndSize(int productId, String size) {
		String sql = "select count(1) from ITEMS where productId = ? and size = ?";
		if(getJdbcTemplate().queryForObject(sql, Integer.class, productId, size) >0){
			return true;
		}
		return false;
	}
	
	public Item readItemByProductIdAndSize(int productId, String size) {
		String sql = "select * from ITEMS where productId = ? and size = ?";
		return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Item(
				rs.getInt("itemId"), new Customer(rs.getString("customerId")),
				new Product(rs.getInt("productId")),
				rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus")), productId, size);
	}

	public int updateItem(int itemId, int quantity) {
		String sql = "update ITEMS set quantity = ? where itemId = ?";
		return getJdbcTemplate().update(sql, quantity, itemId);
	}

	public void updateStatus(int itemId, String itemStatus) {
		String sql = "update ITEMS set itemStatus = ? where itemId = ?";
		getJdbcTemplate().update(sql, itemStatus, itemId);
	}

	public List<Item> readOrderedItems() {
		String sql = "select * from ITEMS A, PRODUCTS B where A.productId = B.productId and itemStatus = 'ordered'";
		try {
			return getJdbcTemplate().query(
					sql,
					(rs, rowNum) -> new Item(rs.getInt("itemId"), 
							new Customer(rs.getString("customerId")),
							new Product(rs.getInt("productId"), rs.getString("productName"), rs.getInt("productPrice"),
									rs.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"), 
									new Brand(rs.getInt("brandId"))), rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus")));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
