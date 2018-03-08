package com.jdbc.demo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

public class TestStateMent01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * 1 StateMent ������ִ��SQL���Ķ��� ͨ��Connection�����creatStateMent()��������ȡ
	 * statement��executeUpdate ����ִ��insert,delete,update������select����
	 * 
	 * 2 Connection ��statement����Ӧ�ó�������ݿ���Դ�����Ӷ�����ʹ�����֮��һ��Ҫ�ر�
	 * ���յĹر���Ҫ��finally�н��йر�
	 */

	// �������ݿ�
	public Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
		// ������ر���
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;

		// ��ȡ�����ļ�
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");

		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		// �������ݿ�����
		Class.forName(driverClass);
		// ��ȡ����
		Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
		return conn;
	}

	// ����Satement
	@Test
	public void testStatement() throws ClassNotFoundException, IOException, SQLException {
		// 1 ��ȡ����
		Connection conn = null;
		Statement statement = null;
		try {
			conn = getConnection();
			// 3׼�������SQL���
			String sql = "INSERT into userinfo(username,userteam,address)" + " VALUES('������','��ʿ','����')";
			// 4ִ��SQL���
			// 4.1 ��ȡ����SQL����statement����:����Connection��creatStatement()����
			statement = conn.createStatement();
			// 4.2����StateMent��executeUpdate()����
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				// 5�ر�statement����
				if (statement != null)
					statement.close();
			} catch (Exception e2) {
				// TODO: handle exception
			} finally {
				// 2�ر�����
				if (conn != null)
					conn.close();
			}

		}

	}

}
