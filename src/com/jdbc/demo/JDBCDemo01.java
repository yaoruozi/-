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
	 * ��Ҫдһ����������������ȡ�����ļ����ڲ���Ҫ�޸ĳ��������£�ֻ�޸��������ݾͿ���
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IOException
	 */
	public Connection getConnection() throws Exception {
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

		// ��������
		// Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		// Class.forName(driverClass);

		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "root");

		// Connection connection = DriverManager.getConnection(jdbcUrl, user,
		// password);
		// �������ݿ����Ӷ���

		Connection connection = driver.connect(jdbcUrl, info);

		return connection;
	}

	// ����
	@Test
	public void testGetConnection() throws Exception {
		System.out.println(getConnection());
	}

	// ʹ��DriverManager��Driver�Ĺ�����
	@Test
	public void testDriverManager() throws IOException, ClassNotFoundException, SQLException {
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
		// ����
		Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println(conn);

	}

}
