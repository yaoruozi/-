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
		System.out.println("�ɹ�");
		System.out.println(conn);
		return conn;

	}

	@Test
	public void selectData() {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		// ��ȡ����
		try {
			conn = testDriverManager();
			statement = conn.createStatement();
			// oracle����ͷ�۵ĵط������ݱ��������Ǵ�д
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
