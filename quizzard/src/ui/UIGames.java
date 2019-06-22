package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.danielmehlber.myui.MyDesign;
import com.danielmehlber.myui.MyPanel;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;

public class UIGames extends JPanel{
	
	private JScrollPane scrollPane;
	private ArrayList<GameEntry> gameEntries;
	private UI ui;
	private JPanel contentPane;
	
	public UIGames(UI ui) {
		this.ui = ui;
		gameEntries = new ArrayList<GameEntry>();
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel();
		scrollPane = new JScrollPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		//super(d);
		//setHeader("Spiele");
		//setHeaderStyle(MyPanel.HEADER_STYLE.BOX);
		//TODO: setRoundness
		
		
	}
	
	public void addGame(int id, String name) {
		GameEntry ge = new GameEntry(id, name);
		ge.setLocation(0, gameEntries.size()*ge.getHeight());
		gameEntries.add(ge);
		contentPane.add(ge);
		scrollPane.revalidate();
		contentPane.setPreferredSize(new Dimension(200, ge.getHeight() * gameEntries.size()));
		System.out.println(contentPane.getHeight());
	}
	
	public GameEntry getGame(int id) {
		GameEntry r = null;
		for(GameEntry e : gameEntries) {
			if(e.getId() == id) {
				r = e;
				break;
			}
		}
		return r;
	}

}
