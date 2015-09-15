package com.ydbaobao.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {
	public Connection connectionTest() {
		String url = "jdbc:mysql://localhost:3307/YDBAOBAO";
		String user = "ydbaobao";
		String password  = "ydbaobao";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
		
	}
}
