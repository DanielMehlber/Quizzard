package ui;

import javax.swing.SwingConstants;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyLabel;
import com.danielmehlber.myui.MyPanel;

import main.UserData;

import java.awt.Dimension;

/**
 * Contains all Profile data in visualized and structured manner
 * @author mehlber
 *
 */
public class UIProfile extends MyPanel{
	
	private UI ui;
	private UserData user;
	
	MyLabel name;
	MyLabel trophies;
	MyLabel gameCount;
	MyLabel recentGamesCount;
	MyLabel friends;
	MyButton btnEinstellungen;
	
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
		
		name = new MyLabel(getDesign());
		name.setBounds(106, 32, 182, 22);
		add(name);
		name.setText("NAME");
		
		trophies = new MyLabel(getDesign());
		trophies.setBounds(30, 96, 64, 22);
		trophies.setHorizontalAlignment(SwingConstants.CENTER);
		add(trophies);
		trophies.setText("999");
		
		gameCount = new MyLabel(getDesign());
		gameCount.setBounds(27, 141, 248, 22);
		add(gameCount);
		gameCount.setText("Gespielte Spiele: K/A");
		
		recentGamesCount = new MyLabel(getDesign());
		recentGamesCount.setBounds(27, 164, 248, 22);
		add(recentGamesCount);
		recentGamesCount.setText("Gespielte Spiele: K/A");
		
		friends = new MyLabel(getDesign());
		friends.setBounds(27, 188, 248, 22);
		add(friends);
		friends.setText("Freunde:");
		
		btnEinstellungen = new MyButton(ui.getDesign(), "Einstellungen");
		btnEinstellungen.setBounds(163, 255, 125, 31);
		add(btnEinstellungen);
	}
	
	@Override
	public void applyDesign() {
		setColor(getDesign().baseColor.lighter(UI.homePagePanelLightnessFactor));
	}
	
	public void setUserData(UserData data) {
		name.setText(data.getUsername());
		trophies.setText(String.valueOf(data.getTrophies()));
		//friends.setText(String.valueOf(data.getFriends().size()));
	}
	
	
}
