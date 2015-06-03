package com.ydbaobao.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ydbaobao.model.Product;

@Repository
public class ProductsDao extends JdbcDaoSupport {
	
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
//	public List<PComment> readListByPId(String pId, String noteId) {
//		String sql = "select * from PCOMMENTS, USERS where PCOMMENTS.userId = USERS.userId AND pId = ? AND noteId = ?";
//		List<PComment> pc = getJdbcTemplate().query(sql, (rs, rowNum) -> new PComment(rs.getString("pCommentId"), rs.getString("pId"),
//						rs.getString("sameSenCount"), rs.getString("sameSenIndex"), rs.getString("pCommentText"),
//						rs.getString("selectedText"), rs.getString("pCommentCreateDate"),
//						new SessionUser(rs.getString("userId"), rs.getString("userName"), rs.getString("userImage")),
//						new Note(rs.getString("noteId"))), pId, noteId);
//		return pc;
//	}
	
	public List<Product> readListByCategory(String categoryId) {
		
		return null;
	}
}
