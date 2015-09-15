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

import com.ydbaobao.domain.Brand;
import com.ydbaobao.domain.Customer;
import com.ydbaobao.domain.Item;
import com.ydbaobao.domain.Product;

@Repository
public class ItemDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Item> readCartItems(String customerId, String itemStatus) {
		String sql = "select * from ITEMS A, PRODUCTS B, CUSTOMERS as C, BRANDS as D where A.itemStatus = ? AND A.customerId= ? AND A.productId = B.productId AND A.customerId = C.customerId AND B.brandId = D.brandId order by A.productId";
		try {
			return getJdbcTemplate().query(
					sql, (rs, rowNum) -> new Item(rs.getInt("itemId"),
							new Customer(rs.getString("customerId"), rs.getString("customerName"), rs.getString("gradeId")),
							new Product(rs.getInt("productId"), rs.getString("productName"), rs.getInt("productPrice"), rs.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"),
									new Brand(rs.getInt("brandId"), rs.getString("brandName"), rs.getInt("discount_1"), rs.getInt("discount_2"),
											rs.getInt("discount_3"), rs.getInt("discount_4"), rs.getInt("discount_5"))),
							rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus"), rs.getInt("price")), itemStatus, customerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Item> readOrderedItems() {
		String sql = "select * from ITEMS A, PRODUCTS B where A.itemStatus = 'S' AND A.quantity != 0 AND A.productId = B.productId order by A.itemId";
		try {
			return getJdbcTemplate().query(
					sql,
					(rs, rowNum) -> new Item(rs.getInt("itemId"), new Customer(rs.getString("customerId")),
							new Product(rs.getInt("productId"), rs.getString("productName"), rs.getInt("productPrice"),
									rs.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"), new Brand(rs.getInt("brandId"))), rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus"), rs.getInt("price")));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void deleteItem(int itemId) {
		String sql = "delete from ITEMS where itemId = ?";
		getJdbcTemplate().update(sql, itemId);
	}
	
	public int createItem(String customerId, int productId, String size, int quantity, String itemStatus) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into ITEMS (customerId, productId, size, quantity, itemStatus, price) values(?, ?, ?, ?, ?, 0)";
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, customerId);
				ps.setInt(2, productId);
				ps.setString(3, size);
				ps.setInt(4, quantity);
				ps.setString(5, itemStatus);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public Item readItem(int itemId) {
		String sql = "select * from ITEMS A, PRODUCTS B where A.itemId=? AND A.productId = B.productId";
		try {
			return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Item(
					rs.getInt("itemId"), new Customer(rs.getString("customerId")),
					new Product(rs.getInt("productId"),rs.getString("productName"), rs.getInt("productPrice"), rs.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"), new Brand(rs.getInt("brandId"))),
					rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus"), rs.getInt("price")), itemId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Item readItemByStatus(int itemId, String itemStatus) {
		String sql = "select * from ITEMS A, PRODUCTS B where A.itemId=? AND A.productId = B.productId AND A.itemStatus = ?";
		try {
			return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Item(
					rs.getInt("itemId"), new Customer(rs.getString("customerId")),
					new Product(rs.getInt("productId"),rs.getString("productName"), rs.getInt("productPrice"), rs.getString("productImage"), rs.getString("productSize"), rs.getInt("isSoldout"), new Brand(rs.getInt("brandId"))),
					rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus"), rs.getInt("price")), itemId, itemStatus);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Item readItemByProductIdAndSizeAndItemStatus(int productId, String size, String customerId, String itemStatus) {
		String sql = "select * from ITEMS where productId = ? and size = ? and customerId = ? and itemStatus = ?";
		try {
			return getJdbcTemplate().queryForObject(sql, (rs, rowNum) -> new Item(
					rs.getInt("itemId"), new Customer(rs.getString("customerId")),
					new Product(rs.getInt("productId")),
					rs.getString("size"), rs.getInt("quantity"), rs.getString("itemStatus"), rs.getInt("price")), productId, size, customerId, itemStatus);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateItemPrice(int itemId, int price) {
		String sql = "update ITEMS set price = ? where itemId = ?";
		getJdbcTemplate().update(sql, price, itemId);
	}

	public void addItemQuantity(int itemId, int quantity) {
		String sql = "update ITEMS set quantity = quantity + ? where itemId = ?";
		getJdbcTemplate().update(sql, quantity, itemId);
	}
	
	public void updateItemQuantity(int itemId, int quantity) {
		String sql = "update ITEMS set quantity = ? where itemId = ?";
		getJdbcTemplate().update(sql, quantity, itemId);
	}

	public void updateItemStatus(int itemId, String itemStatus) {
		String sql = "update ITEMS set itemStatus = ? where itemId = ?";
		getJdbcTemplate().update(sql, itemStatus, itemId);
	}

	public List<Item> readOrderedItemsByCustomerId(String customerId) {
		String sql = "select * from ITEMS A, PRODUCTS B where A.customerId = ? "
				+ "AND A.productId = B.productId "
				+ "AND A.itemStatus = 'S'";
		return getJdbcTemplate().query(sql, (rs, rowNum) ->  new Item(
				rs.getInt("itemId"), 
				new Customer(rs.getString("customerId")),
				new Product(rs.getInt("productId"),rs.getString("productName"), 
						rs.getInt("productPrice"), rs.getString("productImage"), 
						rs.getString("productSize"), rs.getInt("isSoldout"), 
				new Brand(rs.getInt("brandId"))), rs.getString("size"), 
				rs.getInt("quantity"), rs.getString("itemStatus"), 
				rs.getInt("price")), customerId);
	}
	
	public List<Item> readItemsByProductId(int productId) {
		String sql  = "select * from ITEMS A, PRODUCTS B where A.productId = ? AND A.productId = B.productId";
		return getJdbcTemplate().query(sql, (rs, rowNum) -> new Item(
				rs.getInt("itemId"), 
				new Customer(rs.getString("customerId")),
				new Product(rs.getInt("productId"),rs.getString("productName"), 
						rs.getInt("productPrice"), rs.getString("productImage"), 
						rs.getString("productSize"), rs.getInt("isSoldout"), 
				new Brand(rs.getInt("brandId"))), rs.getString("size"), 
				rs.getInt("quantity"), rs.getString("itemStatus"), 
				rs.getInt("price")), productId);
	}

	public List<Item> readOrderedItemsOrderBy(String arg) {
		String sql = "select * from ITEMS A, PRODUCTS B, BRANDS C where A.itemStatus = 'S' AND A.productId = B.productId AND B.brandId = C.brandId";
		if (arg.equals("customerId")) {
			sql += " ORDER BY A.customerId desc";
		} else { //Default
			sql  += " ORDER BY B.brandId desc";
		}
		return getJdbcTemplate().query(sql, (rs, rowNum) -> new Item(
				rs.getInt("itemId"), 
				new Customer(rs.getString("customerId")),
				new Product(rs.getInt("productId"),rs.getString("productName"), 
						rs.getInt("productPrice"), rs.getString("productImage"), 
						rs.getString("productSize"), rs.getInt("isSoldout"), 
				new Brand(rs.getInt("brandId"), rs.getString("brandName"))), rs.getString("size"), 
				rs.getInt("quantity"), rs.getString("itemStatus"), 
				rs.getInt("price")));
	}
}
