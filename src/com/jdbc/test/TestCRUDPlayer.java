package com.jdbc.test;

import com.jdbc.basedemo.CRUDPlayers;
import com.jdbc.bean.Players;

public class TestCRUDPlayer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Players player = new Players();
		player = CRUDPlayers.testAddPlayers();
		System.out.println("==========================");
		player = CRUDPlayers.testGetPlayer();
		System.out.println(player);
	}

}
