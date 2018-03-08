package com.jdbc.basedemo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * 操作数据库的公共类，里面是一些公共方法
 * */
public class JDBCUtils {
	// 获取数据库连接
	// 连接数据库

	public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
		// 定义相关变量
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;

		// 读取配置文件jdbc.properties
		InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

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

	// 释放资源的方法
	public static void closeSource(ResultSet rs, Statement statement, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	// 封装一个通用的更新方法，适用于INSERT,UPDATE,DELETE
	public static void updateData(String sql) {
		// 获取连接
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();// 获得连接
			statement = conn.createStatement(); // 创建statement对象
			statement.executeUpdate(sql);
			// System.out.println(sql);
			System.out.println("SQL执行成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeSource(rs, statement, conn);
			System.out.println("资源关闭成功");
		}

	}
	// 使用preparedStatement对象进行更新

	public static void updateDataUsePS(String sql, Object... args) {
		// 获取连接
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();// 获得连接
			ps = conn.prepareStatement(sql); // 创建statement对象
			// 获取可变参数的值
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ps.executeUpdate();
			// System.out.println(sql);
			System.out.println("SQL执行成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeSource(null, ps, conn);
			System.out.println("资源关闭成功");
		}

	}
}
