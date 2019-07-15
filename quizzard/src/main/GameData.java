package main;

public class GameData {

	private final int id;
	private final String name;
	private int[] player;
	private short round;
	private short max_rounds;
	
	public GameData(int id, String name, int[] player, short round, short max_rounds) {
		this.id = id;
		this.name = name;
		this.player = player;
		this.round = round;
		this.max_rounds = max_rounds;
	}

}
