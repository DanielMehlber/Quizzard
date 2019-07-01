package ui;

import javax.swing.JPanel;

import com.danielmehlber.myui.MyColor;
import com.danielmehlber.myui.MyPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

/**
 * Used on HomePage
 * @author mehlber
 *
 */
public class GameEntry extends MyPanel{

	private String name;
	private int id;
	private int round;
	private JLabel currentRound;
	private int playerCount;
	private JLabel players;
	private UI ui;
	private MyColor _color;
	
	public GameEntry(UI ui, int id, String name) {
		super(ui.getDesign());
		this.ui = ui;
		setSize(180, 65);
		setLayout(null);
		setTitled(false);
		setColorStyle(COLOR_STYLE.DESIGN_ACCENT);
		setRoundness(20);
		
		JLabel gameName = new JLabel(name);
		gameName.setBounds(10, 5, 183, 20);
		add(gameName);
		gameName.setFont(ui.getDesign().font);
		currentRound = new JLabel("Runde: K/A");
		currentRound.setBounds(20, 25, 173, 16);
		add(currentRound);
		
		players = new JLabel("Spieler: K/A");
		players.setBounds(20, 40, 168, 16);
		add(players);
		
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setColor(((MyColor)getColor()).darker(30));
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setColor(((MyColor) getColor()).lighter(30));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setColor(_color);
				reset();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				_color = (MyColor) getColor();
				setColor((MyColor) _color.darker(30));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
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
