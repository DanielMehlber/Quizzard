package ui;

import javax.swing.SwingConstants;

import com.danielmehlber.myui.MyLabel;
import com.danielmehlber.myui.MyList;
import com.danielmehlber.myui.MyPanel;

public class UIGame extends MyPanel{

	private final UI ui;
	public UIGame(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setLayout(null);
		setColorStyle(COLOR_STYLE.CUSTOM);
		setHeader("Ausgewähltes Spiel");
		
		MyLabel lblName = new MyLabel(getDesign());
		lblName.setBounds(10, 27, 179, 24);
		add(lblName);
		
		MyLabel lblRound = new MyLabel(getDesign());
		lblRound.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRound.setBounds(168, 27, 122, 24);
		add(lblRound);
		
		MyList player = new MyList(getDesign());
		player.setRoundness(player.getWidth());
		player.setBounds(10, 71, 280, 162);
		add(player);
		
		
	}
	
	@Override
	public void applyDesign() {
		if(ui == null)
			return;
		setColor(ui.getDesign().baseColor.lighter(UI.homePagePanelLightnessFactor));
	}
}
