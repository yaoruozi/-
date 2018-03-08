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
	 * 1 StateMent 适用于执行SQL语句的对象 通过Connection对象的creatStateMent()方法来获取
	 * statement的executeUpdate 可以执行insert,delete,update操作，select不行
	 * 
	 * 2 Connection 和statement都是应用程序和数据库资源的连接对象，在使用完成之后，一定要关闭
	 * 最终的关闭需要在finally中进行关闭
	 */

	// 连接数据库
	public Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
		// 定义相关变量
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;

		// 读取配置文件
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");

		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		// 加载数据库驱动
		Class.forName(driverClass);
		// 获取连接
		Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
		return conn;
	}

	// 操作Satement
	@Test
	public void testStatement() throws ClassNotFoundException, IOException, SQLException {
		// 1 获取连接
		Connection conn = null;
		Statement statement = null;
		try {
			conn = getConnection();
			// 3准备插入得SQL语句
			String sql = "INSERT into userinfo(username,userteam,address)" + " VALUES('杜兰特','勇士','金州')";
			// 4执行SQL语句
			// 4.1 获取操作SQL语句的statement对象:调用Connection的creatStatement()方法
			statement = conn.createStatement();
			// 4.2调用StateMent的executeUpdate()方法
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				// 5关闭statement对象
				if (statement != null)
					statement.close();
			} catch (Exception e2) {
				// TODO: handle exception
			} finally {
				// 2关闭连接
				if (conn != null)
					conn.close();
			}

		}

	}

}
