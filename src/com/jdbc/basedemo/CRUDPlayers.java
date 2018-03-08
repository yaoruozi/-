package com.jdbc.basedemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbc.bean.Players;

/**
 * 这个类里面封装了大量的静态方法，可以在测试类中进行调用
 * 
 **/
public class CRUDPlayers {
	// 添加一个运动员，传入一个运动员对象
	public static void addPlayers(Players player) {
		// 给出一个SQL
		String sql = "insert into userinfo" + " values (" + player.getId() + ",'" + player.getPlayerID() + "','"
				+ player.getName() + "','" + player.getTeam() + "','" + player.getCity() + "'," + player.getMaxScore()
				+ ",'" + player.getDraftyear() + "')";
		System.out.println(sql);
		// 调用更新方法
		JDBCUtils.updateData(sql);
	}

	// 测试方法

	public static Players testAddPlayers() {
		Players player = getFromConsole();
		addPlayers(player);
		return player;
	}

	// 从控制台获取输入的运动员信息
	private static Players getFromConsole() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Players player = new Players();
		System.out.println("请输入球员信息");
		// 这里因为主键在数据表中设置了自增，所以拼接的SQL中只获取，而不用再一次输入ID
		System.out.print("球员ID:");
		player.setPlayerID(input.next());
		System.out.print("球员姓名:");
		player.setName(input.next());
		System.out.print("球队名称:");
		player.setTeam(input.next());
		System.out.print("球队城市:");
		player.setCity(input.next());
		System.out.print("个人单场最高分:");
		player.setMaxScore(input.nextInt());
		System.out.println("选秀年:");
		player.setDraftyear(input.next());

		return player;
	}

	// 查询类型
	public static Players testGetPlayer() {
		// 获取查询类型
		int searchType = getTypeFromConsole();
		// 执行查询
		Players players = searchPlayer(searchType);
		// 打印运动员信息
		printPlayer(players);

		return players;
	}

	// 打印运动员信息
	private static void printPlayer(Players players) {
		// TODO Auto-generated method stub
		if (players != null) {
			System.out.println(players);
		} else {
			System.out.println("查无此人....");
		}
	}

	// 具体查询运动员信息方法
	private static Players searchPlayer(int searchType) {
		// TODO Auto-generated method stub
		// select * 的写法不好，这里为了偷懒
		String sql = "select * from userinfo" + " where ";
		Scanner scanner = new Scanner(System.in);
		// 根据确定的type，提示用户输入查询的类型
		//// 最终确认SQL语句
		if (searchType == 1) {
			System.out.print("请输入球员ID编号:");
			String playerID = scanner.next();
			sql = sql + "playID='" + playerID + "'";
		} else {
			System.out.print("请输入球员姓名:");
			String name = scanner.next();
			sql = sql + "username='" + name + "'";
		}
		// 执行查询
		Players player = getPlayer(sql);

		return player;
	}

	// 执行查询的方法,根据SQL语句执行查询
	private static Players getPlayer(String sql) {
		// TODO Auto-generated method stub
		Players player = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				player = new Players(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));
				int id = rs.getInt(1);
				String playID = rs.getString(2);
				String name = rs.getString(3);
				String team = rs.getString(4);
				String city = rs.getString(5);
				int maxScore = rs.getInt(6);
				String draftYear = rs.getString(7);

				System.out.println("ID:" + id);
				System.out.println("playID:" + playID);
				System.out.println("名字:" + name);
				System.out.println("球队:" + team);
				System.out.println("城市:" + city);
				System.out.println("个人单场最高分:" + maxScore);
				System.out.println("选秀年:" + draftYear);

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
		// System.out.println(sql);
		return player;
	}

	// 从控制台读入一个整数，返回1，用球员ID 查询，2，用姓名查询，其他的无效并提示让用户重新输入
	private static int getTypeFromConsole() {
		// TODO Auto-generated method stub
		// 1 根据提示让用户输入查询类型，返回1，用球员ID 查询，2，用姓名查询
		System.out.println("请输入查询类型:1.使用球员ID查询,2.使用姓名查询");
		Scanner input = new Scanner(System.in);
		int type = input.nextInt();
		// 判断
		if (type != 1 && type != 2) {
			System.out.println("输入不合法,请重新输入....");
			throw new RuntimeException(); // 中断程序

		}
		return type;
	}

}
