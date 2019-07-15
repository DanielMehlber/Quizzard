package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.danielmehlber.myui.MyPage;
import com.danielmehlber.myui.MyPanel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 * Contains all UI Elements
 * @author mehlber
 *
 */
public class HomePage extends MyPage{
	
	private final UI ui;
	public final UIGames uiGames;
	public final UIProfile uiProfile;
	public final UIGame uiGame;
	
	public HomePage(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setLayout(new BorderLayout(0, 0));
		setSize(1000, 800);
		
		uiGames = new UIGames(ui);
		uiProfile = new UIProfile(ui);
		uiGame = new UIGame(ui);
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setPreferredSize(new Dimension(0, 50));
		
		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(300, 0));
		
		JPanel uiSpieleHolder = new JPanel();
		leftPanel.add(uiSpieleHolder);
		uiSpieleHolder.setLayout(new BorderLayout());
		uiSpieleHolder.add(uiGames, BorderLayout.CENTER);
		
		JPanel uiSpielHolder = new JPanel();
		leftPanel.add(uiSpielHolder);
		uiSpielHolder.setLayout(new BorderLayout());
		uiSpielHolder.add(uiGame, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setPreferredSize(new Dimension(300, 0));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JPanel uiProfilHolder = new JPanel();
		rightPanel.add(uiProfilHolder);
		uiProfilHolder.add(uiProfile, BorderLayout.CENTER);
		
		JPanel uiFreundeHolder = new JPanel();
		rightPanel.add(uiFreundeHolder);
		
		JPanel uiChat = new JPanel();
		add(uiChat, BorderLayout.CENTER);
		
		uiGames.addGame(0, "$BEAT ME$");
		uiGames.addGame(0, "JOIN FOR FREE");
		
	}
	
	
	
}
