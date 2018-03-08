package com.jdbc.basedemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestJDBCDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ��ѯ����
	@Test
	public void selectData() {
		// ��ȡ����
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			// ��ȡ���ݿ�����
			conn = JDBCUtils.getConnection();
			statement = conn.createStatement();
			String sql = "SELECT * from userinfo";
			rs = statement.executeQuery(sql);
			// ��������
			while (rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString("username");
				String team = rs.getString(3);
				String address = rs.getString(4);

				System.out.println("ID" + id + ",����:" + username + ",����:" + team + ",��ַ:" + address);

			}
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
		}

	}

	// ��װһ��ͨ�õĸ��·�����������INSERT,UPDATE,DELETE
	public void updateData(String sql) {
		// ��ȡ����
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();// �������
			statement = conn.createStatement(); // ����statement����
			statement.executeUpdate(sql);
			System.out.println(sql);
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

	// ���Է���
	@Test
	public void test01() {
		String sql = "UPDATE userinfo SET username='�������' WHERE username like '%������%'";
		updateData(sql);
	}

}
