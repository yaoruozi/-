package com.jdbc.basedemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbc.bean.Players;

/**
 * ����������װ�˴����ľ�̬�����������ڲ������н��е���
 * 
 **/
public class CRUDPlayers {
	// ���һ���˶�Ա������һ���˶�Ա����
	public static void addPlayers(Players player) {
		// ����һ��SQL
		String sql = "insert into userinfo" + " values (" + player.getId() + ",'" + player.getPlayerID() + "','"
				+ player.getName() + "','" + player.getTeam() + "','" + player.getCity() + "'," + player.getMaxScore()
				+ ",'" + player.getDraftyear() + "')";
		System.out.println(sql);
		// ���ø��·���
		JDBCUtils.updateData(sql);
	}

	// ���Է���

	public static Players testAddPlayers() {
		Players player = getFromConsole();
		addPlayers(player);
		return player;
	}

	// �ӿ���̨��ȡ������˶�Ա��Ϣ
	private static Players getFromConsole() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Players player = new Players();
		System.out.println("��������Ա��Ϣ");
		// ������Ϊ���������ݱ�������������������ƴ�ӵ�SQL��ֻ��ȡ����������һ������ID
		System.out.print("��ԱID:");
		player.setPlayerID(input.next());
		System.out.print("��Ա����:");
		player.setName(input.next());
		System.out.print("�������:");
		player.setTeam(input.next());
		System.out.print("��ӳ���:");
		player.setCity(input.next());
		System.out.print("���˵�����߷�:");
		player.setMaxScore(input.nextInt());
		System.out.println("ѡ����:");
		player.setDraftyear(input.next());

		return player;
	}

	// ��ѯ����
	public static Players testGetPlayer() {
		// ��ȡ��ѯ����
		int searchType = getTypeFromConsole();
		// ִ�в�ѯ
		Players players = searchPlayer(searchType);
		// ��ӡ�˶�Ա��Ϣ
		printPlayer(players);

		return players;
	}

	// ��ӡ�˶�Ա��Ϣ
	private static void printPlayer(Players players) {
		// TODO Auto-generated method stub
		if (players != null) {
			System.out.println(players);
		} else {
			System.out.println("���޴���....");
		}
	}

	// �����ѯ�˶�Ա��Ϣ����
	private static Players searchPlayer(int searchType) {
		// TODO Auto-generated method stub
		// select * ��д�����ã�����Ϊ��͵��
		String sql = "select * from userinfo" + " where ";
		Scanner scanner = new Scanner(System.in);
		// ����ȷ����type����ʾ�û������ѯ������
		//// ����ȷ��SQL���
		if (searchType == 1) {
			System.out.print("��������ԱID���:");
			String playerID = scanner.next();
			sql = sql + "playID='" + playerID + "'";
		} else {
			System.out.print("��������Ա����:");
			String name = scanner.next();
			sql = sql + "username='" + name + "'";
		}
		// ִ�в�ѯ
		Players player = getPlayer(sql);

		return player;
	}

	// ִ�в�ѯ�ķ���,����SQL���ִ�в�ѯ
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
				System.out.println("����:" + name);
				System.out.println("���:" + team);
				System.out.println("����:" + city);
				System.out.println("���˵�����߷�:" + maxScore);
				System.out.println("ѡ����:" + draftYear);

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

	// �ӿ���̨����һ������������1������ԱID ��ѯ��2����������ѯ����������Ч����ʾ���û���������
	private static int getTypeFromConsole() {
		// TODO Auto-generated method stub
		// 1 ������ʾ���û������ѯ���ͣ�����1������ԱID ��ѯ��2����������ѯ
		System.out.println("�������ѯ����:1.ʹ����ԱID��ѯ,2.ʹ��������ѯ");
		Scanner input = new Scanner(System.in);
		int type = input.nextInt();
		// �ж�
		if (type != 1 && type != 2) {
			System.out.println("���벻�Ϸ�,����������....");
			throw new RuntimeException(); // �жϳ���

		}
		return type;
	}

}
