package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCDemo2 {
	public final String driverClass = "oracle.jdbc.driver.OracleDriver";
	public final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	public final String user = "scott";
	public final String password = "yao";

	public Connection testDriverManager() throws Exception {
		Class.forName(driverClass);
		Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println("成功");
		System.out.println(conn);
		return conn;

	}

	@Test
	public void selectData() {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		// 获取连接
		try {
			conn = testDriverManager();
			statement = conn.createStatement();
			// oracle让人头疼的地方，数据表名必须是大写
			String sql = "SELECT * from USERINFO";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String team = rs.getString(3);
				System.out.println(id);
				System.out.println(name);
				System.out.println(team);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
