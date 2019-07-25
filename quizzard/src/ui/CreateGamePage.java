package ui;

import javax.swing.JPanel;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyDirection;
import com.danielmehlber.myui.MyLabel;
import com.danielmehlber.myui.MyPage;
import com.danielmehlber.myui.MyPanel;
import com.danielmehlber.myui.MyPanel.COLOR_STYLE;
import com.danielmehlber.myui.MyTextEntry;
import com.danielmehlber.myui.MyToggleButton;

import main.Console;
import main.GameData;
import main.Topic;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class CreateGamePage extends MyPage{

	private UI ui;
	private final int distAdditive = 15;
	MyTextEntry entryMaxRounds;
	MyTextEntry entryMaxPlayers;
	JComboBox<Topic> topics;
	
	public CreateGamePage(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setLayout(null);
		
		MyPanel content = new MyPanel(getDesign());
		content.setBounds(0,0,400, 500+ distAdditive);
		add(content);
		content.setColorStyle(COLOR_STYLE.DESIGN_BASE);
		content.setTitled(false);
		
		MyLabel lblGameCreationPage = new MyLabel(getDesign(), "Spiel erstellen");
		lblGameCreationPage.setBounds(23, 25 + distAdditive, 190, 26 + distAdditive);
		content.add(lblGameCreationPage);
		
		MyTextEntry entryName = new MyTextEntry(getDesign());
		entryName.setBounds(23, 70+ distAdditive, 286, 37+ distAdditive);
		entryName.setSubtext("* Benenne das Spiel");
		content.add(entryName);
		
		MyTextEntry entryDescription = new MyTextEntry(getDesign());
		entryDescription.setBounds(23, 118 + distAdditive, 286, 37+distAdditive);
		entryDescription.setSubtext("Füge eine kurze Beschreibung hinzu");
		content.add(entryDescription);
		
		entryMaxRounds = new MyTextEntry(getDesign());
		entryMaxRounds.setBounds(23, 214+ distAdditive, 126, 37+ distAdditive);
		entryMaxRounds.setSubtext("* Maximale Runden?");
		content.add(entryMaxRounds);
		
		entryMaxPlayers = new MyTextEntry(getDesign());
		entryMaxPlayers.setBounds(183, 214+ distAdditive, 126, 37+ distAdditive);
		entryMaxPlayers.setSubtext("* Spieleranzahl");
		content.add(entryMaxPlayers);
		
		MyTextEntry entryPassword = new MyTextEntry(getDesign());
		entryPassword.setBounds(23, 312+ distAdditive, 286, 37+ distAdditive);
		entryPassword.setSubtext("Passwort");
		content.add(entryPassword);
		
		
		topics = new JComboBox<Topic>();
		topics.setBounds(23, 375+ distAdditive, 286, 20+ distAdditive);
		content.add(topics);
		MyButton btnErstellen = new MyButton(getDesign(), "Erstellen");
		btnErstellen.setBounds(220, 419+ distAdditive, 89, 23+ distAdditive);
		content.add(btnErstellen);
		btnErstellen.addRunnable(()->createGame(entryName.getText(), entryPassword.getText(), entryMaxRounds.getText(), entryMaxPlayers.getText(), topics, entryDescription.getText()));
		topics.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setTopics(ui.mainComponent.getAllTopics());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		MyButton btnZurueck = new MyButton(getDesign(), "Zur\u00FCck");
		btnZurueck.setBounds(23, 419+ distAdditive, 89, 23+ distAdditive);
		content.add(btnZurueck);
		btnZurueck.addRunnable(()->ui.changePage(ui.pageHome, MyDirection.NORTH));
		
		MyPage _this = this;
		
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				UI.center(_this, content, true, true);
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void createGame(String name, String pw, String rounds, String player, JComboBox cards, String desc) {
		int player_count = Integer.parseInt(player);
		int round_count = Integer.parseInt(rounds);
		if(player_count < 1) {
			Console.error("game-creation", "Spieleranzahl muss >0 sein", false);
			entryMaxPlayers.error("Zu wenig Spieler", 3);
			return;
		}
		if(round_count < 1) {
			Console.error("game-creation", "Rundenanzahl muss >0 sein", false);
			entryMaxRounds.error("Zu wenig Runden", 3);
			return;
		}
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(ui.mainComponent.userData.getUserID());
		GameData data = new GameData(-1, name, p, pw, desc, player_count, round_count);
		Topic t = (Topic)topics.getSelectedItem();
		if(t == null) {
			Console.error("game-creation", "Ungueltiges Topic ausgewaehlt", true);
			return;
		}
		data.setTopicId(t.getId());
		ui.mainComponent.createGame(data);
	}
	
	public void setTopics(Topic[] topic_arr) {
		topics.removeAllItems();
		for(Topic t : topic_arr)
			topics.addItem(t);
	}
}
