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

	// 查询方法
	@Test
	public void selectData() {
		// 获取连接
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			// 获取数据库连接
			conn = JDBCUtils.getConnection();
			statement = conn.createStatement();
			String sql = "SELECT * from userinfo";
			rs = statement.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString("username");
				String team = rs.getString(3);
				String address = rs.getString(4);

				System.out.println("ID" + id + ",名字:" + username + ",队伍:" + team + ",地址:" + address);

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

	// 封装一个通用的更新方法，适用于INSERT,UPDATE,DELETE
	public void updateData(String sql) {
		// 获取连接
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();// 获得连接
			statement = conn.createStatement(); // 创建statement对象
			statement.executeUpdate(sql);
			System.out.println(sql);
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

	// 测试方法
	@Test
	public void test01() {
		String sql = "UPDATE userinfo SET username='伊戈达拉' WHERE username like '%杜兰特%'";
		updateData(sql);
	}

}
