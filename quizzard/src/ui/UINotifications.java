package ui;

import java.util.LinkedList;

import com.danielmehlber.myui.MyList;

public class UINotifications extends MyList{

	private final UI ui;
	private LinkedList<Notification> notifications;
	private final static int max = 20;
	
	public UINotifications(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		notifications = new LinkedList<>();
	}
	
	public void setNotifications(Notification[] n) {
		
		for(Notification e : n)
			push(e);
	}
	
	public void push(Notification n) {
		notifications.add(n);
		if(notifications.size() > max) {
			Notification remove = notifications.poll();
			ui.mainComponent.eraseNotification(remove.getId());
			//TODO: remove top notification ui
			//TODO: add bottom notification ui
		}
	}
	
	public void remove_all() {
		notifications.clear();
	}

}
