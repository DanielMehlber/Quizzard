package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyLabel;
import com.danielmehlber.myui.MyPanel;

import java.awt.Dimension;

import javax.swing.JButton;

public class UIProfile extends MyPanel{
	
	private UI ui;
	
	public UIProfile(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setPreferredSize(new Dimension(300,299));
		setLayout(null);
		setColorStyle(COLOR_STYLE.DESIGN_BASE);
		setTitled(false);
		
		MyPanel picture = new MyPanel(ui.getDesign());
		picture.setBounds(30, 30, 64, 64);
		add(picture);
		picture.setRoundness(picture.getWidth());
		picture.setTitled(false);
		
		MyLabel name = new MyLabel(getDesign());
		name.setBounds(106, 32, 182, 22);
		add(name);
		name.setText("NAME");
		
		JLabel trophies = new JLabel("999");
		trophies.setBounds(30, 96, 64, 22);
		trophies.setHorizontalAlignment(SwingConstants.CENTER);
		add(trophies);
		
		MyLabel gameCount = new MyLabel(getDesign());
		gameCount.setBounds(27, 141, 248, 22);
		add(gameCount);
		gameCount.setText("Gespielte Spiele: K/A");
		
		MyLabel recentGamesCount = new MyLabel(getDesign());
		recentGamesCount.setBounds(27, 164, 248, 22);
		add(recentGamesCount);
		recentGamesCount.setText("Gespielte Spiele: K/A");
		
		MyLabel friends = new MyLabel(getDesign());
		friends.setBounds(27, 188, 248, 22);
		add(friends);
		friends.setText("Freunde:");
		
		MyButton btnEinstellungen = new MyButton(ui.getDesign(), "Einstellungen");
		btnEinstellungen.setBounds(163, 255, 125, 31);
		add(btnEinstellungen);
	}
}
