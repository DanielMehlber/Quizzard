package main;

public class GameData {

	private int id;
	private String name;
	private int[] player;
	private int round;
	private int max_rounds;
	
	public GameData(int id, String name, int[] player, int i, int j) {
		this.id = id;
		this.name = name;
		this.player = player;
		this.round = i;
		this.max_rounds = j;
	}
	public void setId(int i) {
		id=i;
	}
	public void setName(String n) {
		name=n;
	}
	public void setPlayers (int[] p) {
		player=p;
	}
	public void setI(int a) {
		round=a;
	}
	public void setJ(int j) {
		max_rounds=j;
	}
	

}
