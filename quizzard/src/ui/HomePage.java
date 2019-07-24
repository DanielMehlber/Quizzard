package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyDirection;
import com.danielmehlber.myui.MyPage;
import com.danielmehlber.myui.MyPanel;
import com.danielmehlber.myui.MyPanel.COLOR_STYLE;

import main.UserData;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MultipleGradientPaint.ColorSpaceType;

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
	public final UIFriends uiFriends;
	public final UINotifications uiNotifications;
	public final MyButton btnCreateGame;
	public final MyButton btnJoinGame;
	
	public HomePage(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setLayout(new BorderLayout(0, 0));
		setSize(1000, 800);
		
		btnCreateGame = new MyButton(getDesign(), "Spiel erstellen");
		btnCreateGame.setPreferredSize(new Dimension(200, 35));
		btnJoinGame = new MyButton(getDesign(), "Spiel beitreten");
		btnJoinGame.setPreferredSize(new Dimension(200, 35));
		btnCreateGame.addRunnable(()->ui.changePage(ui.pageCreateGame, MyDirection.SOUTH));
		
		uiGames = new UIGames(ui);
		uiProfile = new UIProfile(ui);
		uiGame = new UIGame(ui);
		uiFriends = new UIFriends(ui);
		uiNotifications = new UINotifications(ui);
		
		MyPanel topPanel = new MyPanel(getDesign());
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.setPreferredSize(new Dimension(0, 50));
		topPanel.setColorStyle(COLOR_STYLE.DESIGN_BASE);
		topPanel.setTitled(false);
		topPanel.add(btnCreateGame);
		topPanel.add(btnJoinGame);
		topPanel.setPreferredSize(new Dimension(0, 50));
		
		MyPanel leftPanel = new MyPanel(getDesign());
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(300, 0));
		leftPanel.setColorStyle(COLOR_STYLE.DESIGN_BASE);
		
		MyPanel uiSpieleHolder = new MyPanel(getDesign());
		leftPanel.add(uiSpieleHolder);
		uiSpieleHolder.setLayout(new BorderLayout());
		uiSpieleHolder.add(uiGames, BorderLayout.CENTER);
		uiSpieleHolder.setColorStyle(COLOR_STYLE.DESIGN_BASE);
		uiSpieleHolder.setTitled(false);
		
		MyPanel uiSpielHolder = new MyPanel(getDesign());
		leftPanel.add(uiSpielHolder);
		uiSpielHolder.setLayout(new BorderLayout());
		uiSpielHolder.add(uiGame, BorderLayout.CENTER);
		uiSpielHolder.setColorStyle(COLOR_STYLE.DESIGN_BASE);

		
		MyPanel rightPanel = new MyPanel(getDesign());
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setPreferredSize(new Dimension(300, 0));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setColorStyle(COLOR_STYLE.DESIGN_BASE);

		
		MyPanel uiProfilHolder = new MyPanel(getDesign());
		rightPanel.add(uiProfilHolder);
		uiProfilHolder.add(uiProfile, BorderLayout.CENTER);
		uiProfilHolder.setColorStyle(COLOR_STYLE.DESIGN_BASE);

		
		MyPanel uiFreundeHolder = new MyPanel(getDesign());
		rightPanel.add(uiFreundeHolder);
		uiFreundeHolder.setLayout(new BorderLayout());
		uiFreundeHolder.add(uiFriends, BorderLayout.CENTER);
		uiFreundeHolder.setColorStyle(COLOR_STYLE.DESIGN_BASE);
		
		MyPanel uiChat = new MyPanel(getDesign());
		uiChat.setColorStyle(COLOR_STYLE.DESIGN_BASE);
		add(uiChat, BorderLayout.CENTER);
		uiChat.setTitled(false);
		uiChat.add(uiNotifications);
		
		uiGames.addGame(0, "$BEAT ME$");
		uiGames.addGame(0, "JOIN FOR FREE");
		
		UserData[] data = {
				new UserData(0, "Daniel"),
				new UserData(1, "Hannes"),
				new UserData(2, "Ah")
		};
		//TODO: Does not work
		uiFriends.setFriends(data);
		
		
	}
	
	public void entered() {
		ui.mainComponent.refreshAll();
	}
	
	
	
}
