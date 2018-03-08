package com.jdbc.demo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class JDBCDemo01 {
	/**
	 * 需要写一个公共方法，来读取公共文件，在不需要修改程序的情况下，只修改配置内容就可以
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IOException
	 */
	public Connection getConnection() throws Exception {
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

		// 加载驱动
		// Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		// Class.forName(driverClass);

		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "root");

		// Connection connection = DriverManager.getConnection(jdbcUrl, user,
		// password);
		// 创建数据库连接对象

		Connection connection = driver.connect(jdbcUrl, info);

		return connection;
	}

	// 测试
	@Test
	public void testGetConnection() throws Exception {
		System.out.println(getConnection());
	}

	// 使用DriverManager，Driver的管理类
	@Test
	public void testDriverManager() throws IOException, ClassNotFoundException, SQLException {
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
		// 连接
		Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println(conn);

	}

}
