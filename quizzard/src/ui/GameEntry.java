package ui;

import javax.swing.JPanel;

import com.sun.media.jfxmedia.effects.EqualizerBand;

import javax.swing.JLabel;

public class GameEntry extends JPanel{

	private String name;
	private int id;
	private int round;
	private JLabel currentRound;
	private int playerCount;
	private JLabel players;
	
	public GameEntry(int id, String name) {
		setSize(200, 88);
		setLayout(null);
		
		JLabel gameName = new JLabel(name);
		gameName.setBounds(10, 5, 183, 20);
		add(gameName);
		
		currentRound = new JLabel("Runde: K/A");
		currentRound.setBounds(20, 38, 173, 16);
		add(currentRound);
		
		players = new JLabel("Spieler: K/A");
		players.setBounds(20, 54, 168, 16);
		add(players);
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
		currentRound.setText("Aktuelle Runde: "+round);
	}
	
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
		players.setText("Spieler: "+playerCount);
		
	}

	public boolean equals(GameEntry e) {
		return this.id == e.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JLabel getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(JLabel currentRound) {
		this.currentRound = currentRound;
	}

	public JLabel getPlayers() {
		return players;
	}

	public void setPlayers(JLabel players) {
		this.players = players;
	}

	public int getPlayerCount() {
		return playerCount;
	}
	
	
	
}
