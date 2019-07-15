package ui;

import com.danielmehlber.myui.MyList;

import main.UserData;

public class UIFriends extends MyList{

	private final UI ui;
	
	public UIFriends(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setColorStyle(COLOR_STYLE.DESIGN_BASE);
	}
	
	public void setFriends(UserData[] all_user_data) {
		for(UserData data : all_user_data) {
			addEntry(new PlayerEntry(ui, data));
		}
	}
	
}
