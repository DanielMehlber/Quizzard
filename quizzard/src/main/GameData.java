package main;

public class GameData {

	private int id;
	private boolean öffentlich;
	private int[] playerid;
	private String password;
	private String describtion;
	private int round;
	private int max_rounds;
	
	public GameData(int id, int[] p, String pw, String de, int i, int j) {
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
	public void setPlayers (int[] p) {
		playerid=p;
	}
	public void setI(int a) {
		round=a;
	}
	public void setJ(int j) {
		max_rounds=j;
	}
	

}
