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
 * �������ݿ�Ĺ����࣬������һЩ��������
 * */
public class JDBCUtils {
	// ��ȡ���ݿ�����
	// �������ݿ�

	public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
		// ������ر���
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;

		// ��ȡ�����ļ�jdbc.properties
		InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

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

	// �ͷ���Դ�ķ���
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

	// ��װһ��ͨ�õĸ��·�����������INSERT,UPDATE,DELETE
	public static void updateData(String sql) {
		// ��ȡ����
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();// �������
			statement = conn.createStatement(); // ����statement����
			statement.executeUpdate(sql);
			// System.out.println(sql);
			System.out.println("SQLִ�гɹ�");
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
			System.out.println("��Դ�رճɹ�");
		}

	}
	// ʹ��preparedStatement������и���

	public static void updateDataUsePS(String sql, Object... args) {
		// ��ȡ����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();// �������
			ps = conn.prepareStatement(sql); // ����statement����
			// ��ȡ�ɱ������ֵ
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ps.executeUpdate();
			// System.out.println(sql);
			System.out.println("SQLִ�гɹ�");
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
			System.out.println("��Դ�رճɹ�");
		}

	}
}
