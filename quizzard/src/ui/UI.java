package ui;

import java.awt.Component;

import com.danielmehlber.myui.MyDesign;
import com.danielmehlber.myui.MyFrame;

import main.MainComponent;

public class UI extends MyFrame{

	MainComponent mainComponent;
	
	//Pages
	public StartPage pageStart;
	public RegisterPage pageRegister;
	
	public UI(MainComponent mc) {
		super(design());
		this.mainComponent = mc;
		setTitle("Quizzard");
		
		pageStart = new StartPage(this);
		pageRegister = new RegisterPage(this);
		
		changePage(pageStart, null);
		//Final line
		go(600, 600);
	}
	
	public MainComponent getMainComponent() {
		return mainComponent;
	}
	
	public static void center(Component head, Component pos, boolean w, boolean h) {
		int x = head.getWidth()/2 - pos.getWidth()/2;
		int y = head.getHeight()/2 - pos.getHeight()/2;
		if(w)
			pos.setLocation(x, pos.getY());
		if(h)
			pos.setLocation(pos.getX(), y);
	}
	
	private static MyDesign design() {
		MyDesign d = MyDesign.FOX;
		d.font = d.font.deriveFont(16f);
		return d;
	}

}
