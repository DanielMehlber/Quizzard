package main;

import java.util.ArrayList;

public class GameData {

	private int id;
	private String name;
	private boolean öffentlich;
	private ArrayList<Integer> playerid;
	private String password;
	private String describtion;
	private int round;
	private int max_rounds;
	private int topicId;
	
	public GameData(int id, String name, ArrayList<Integer> p, String pw, String de, int i, int j) {
		this.id = id;
		this.playerid = p;
		password=pw;
		describtion=de;
		this.round = i;
		this.max_rounds = j;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isÖffentlich() {
		return öffentlich;
	}

	public void setÖffentlich(boolean öffentlich) {
		this.öffentlich = öffentlich;
	}

	public ArrayList<Integer> getPlayerid() {
		return playerid;
	}

	public void setPlayerid(ArrayList<Integer> playerid) {
		this.playerid = playerid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescription(String describtion) {
		this.describtion = describtion;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getMax_rounds() {
		return max_rounds;
	}

	public void setMax_rounds(int max_rounds) {
		this.max_rounds = max_rounds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
	
	
	
	

}
