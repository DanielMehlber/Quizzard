package main;

import java.util.ArrayList;

public class GameData {

	private int id;
	private boolean öffentlich;
	private ArrayList<Integer> playerid;
	private String password;
	private String describtion;
	private int round;
	private int max_rounds;
	
	public GameData(int id, ArrayList<Integer> p, String pw, String de, int i, int j) {
		this.id = id;
		this.playerid = p;
		password=pw;
		describtion=de;
		this.round = i;
		this.max_rounds = j;
	}
	public void setId(int i) {
		id=i;
	}
	public void setPw(String n) {
		password=n;
	}
	public void setDes(String d) {
		describtion=d;
	}
	public void setPlayers (ArrayList<Integer> p) {
		playerid=p;
	}
	public void setI(int a) {
		round=a;
	}
	public void setJ(int j) {
		max_rounds=j;
	}
	

}
