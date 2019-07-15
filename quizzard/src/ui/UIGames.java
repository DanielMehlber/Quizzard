package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.danielmehlber.myui.Designable;
import com.danielmehlber.myui.MyDesign;
import com.danielmehlber.myui.MyList;
import com.danielmehlber.myui.MyPanel;

/**
 * Panel displaying running games.
 * @author mehlber
 *
 */
public class UIGames extends MyList{
	
	private ArrayList<GameEntry> gameEntries;
	private UI ui;
	
	
	public UIGames(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setRoundness(10);
		setColorStyle(COLOR_STYLE.DESIGN_BASE);
		setHeader("Spiele");
		setHeaderStyle(MyPanel.HEADER_STYLE.BOX);
		gameEntries = new ArrayList<GameEntry>();
		//setLayout(new BorderLayout(0, 0));
		
		applyDesign();
		
	}
	
	/**
	 * Adds Game to ui
	 * @param id ID
	 * @param name Name
	 */
	public void addGame(int id, String name) {
		GameEntry ge = new GameEntry(ui, id, name);
		addEntry(ge);
		gameEntries.add(ge);
	}
	
	/**
	 * Returns Game with ID
	 * @param id ID
	 * @return Game
	 */
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
	
	
	@Override
	public void applyDesign() {
		MyDesign design = getDesign();
		setBackground(design.baseColor.lighter(UI.homePagePanelLightnessFactor));
		//TODO: Derived class should set list background here (maybe overwrite setBackground)
	}

}
