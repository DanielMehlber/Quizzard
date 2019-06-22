package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import com.danielmehlber.myui.MyColor;
import com.danielmehlber.myui.MyDesign;
import com.danielmehlber.myui.MyFrame;
import com.danielmehlber.myui.MySyncTask;

import main.MainComponent;

public class UI extends MyFrame{

	MainComponent mainComponent;
	
	//Pages
	public StartPage pageStart;
	public RegisterPage pageRegister;
	public HomePage pageHome;
	public static float pageChangeSpeedFac = 4f;
	
	public UI(MainComponent mc) {
		super(design());
		
		this.mainComponent = mc;
		setTitle("Quizzard");
		setResizable(false);
		
		doOnClose(() -> setTitle("Auf Wiedersehen"));
		
		pageStart = new StartPage(this);
		pageRegister = new RegisterPage(this);
		pageHome = new HomePage(this);
		
		changePage(pageHome, null); // DEBUG
		
		go(600, 600);
		
		new Thread(() -> {
			while(true) {
				MyColor prev = getDesign().accentColor;
				float[] hsv = Color.RGBtoHSB(prev.getRed(), prev.getGreen(), prev.getBlue(), null);
				int rgb = Color.HSBtoRGB(hsv[0]+0.001f, hsv[1], hsv[2]);
				getDesign().accentColor = new MyColor(new Color(rgb));
				getDesign().apply();
				MySyncTask.sync(30);
			}
			
		}).start();
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
