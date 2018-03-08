package com.jdbc.bean;

public class Players {
	private int id; // ID
	private String playerID;
	private String name; // 名字
	private String team; // 球队
	private String city; // 城市
	private int maxScore; // 个人单场最高分
	private String draftyear; // 选秀年

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public String getDraftyear() {
		return draftyear;
	}

	public void setDraftyear(String draftyear) {
		this.draftyear = draftyear;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public Players(int id, String playerID, String name, String team, String city, int maxScore, String draftyear) {
		super();
		this.id = id;
		this.playerID = playerID;
		this.name = name;
		this.team = team;
		this.city = city;
		this.maxScore = maxScore;
		this.draftyear = draftyear;
	}

	public Players() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Players [id=" + id + ", playerID=" + playerID + ", name=" + name + ", team=" + team + ", city=" + city
				+ ", maxScore=" + maxScore + ", draftyear=" + draftyear + "]";
	}

}
