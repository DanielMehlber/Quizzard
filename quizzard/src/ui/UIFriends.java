package ui;

import java.awt.Dimension;

import com.danielmehlber.myui.MyList;

import main.UserData;

public class UIFriends extends MyList{

	private final UI ui;
	
	
	public UIFriends(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setColorStyle(COLOR_STYLE.CUSTOM);
	}
	
	public void setFriends(UserData[] all_user_data) {
		for(UserData data : all_user_data) {
			PlayerEntry pe = new PlayerEntry(ui, data);
			addEntry(pe);
		}
	}
	
	@Override
	public void applyDesign() {
		setBackground(getDesign().baseColor.lighter(UI.homePagePanelLightnessFactor));
	}
	
}
