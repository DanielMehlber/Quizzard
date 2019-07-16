package ui;

import java.awt.Color;
import java.awt.Component;
import com.danielmehlber.myui.MyColor;
import com.danielmehlber.myui.MyDesign;
import com.danielmehlber.myui.MyFrame;
import com.danielmehlber.myui.MySyncTask;

import main.MainComponent;

/**
 * UI head class
 * @author mehlber
 *
 */
public class UI extends MyFrame{

	MainComponent mainComponent;
	
	//Pages
	public final StartPage pageStart;
	public final RegisterPage pageRegister;
	public final HomePage pageHome;
	public static final float pageChangeSpeedFac = 4f;
	public static final int homePagePanelLightnessFactor = 30;
	
	public UI(MainComponent mc) {
		super(design());
		
		this.mainComponent = mc;
		setTitle("Quizzard");
		setResizable(false);
		
		doOnClose(() -> {
			setTitle("Auf Wiedersehen");
			mainComponent.windowOnClose();
		});
		
		pageStart = new StartPage(this);
		pageRegister = new RegisterPage(this);
		pageHome = new HomePage(this);
		
		changePage(pageStart, null); // DEBUG
		setResizable(true);
		
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
	
	/**
	 * Centeres any kind of container inside another one, maintaining its size
	 * @param head outher Container
	 * @param pos inner Container
	 * @param w centering along x axis
	 * @param h centering along y axis
	 */
	public static void center(Component head, Component pos, boolean w, boolean h) {
		int x = head.getWidth()/2 - pos.getWidth()/2;
		int y = head.getHeight()/2 - pos.getHeight()/2;
		if(w)
			pos.setLocation(x, pos.getY());
		if(h)
			pos.setLocation(pos.getX(), y);
	}
	
	/**
	 * Creates default design of UI
	 * @return generated static design
	 */
	private static MyDesign design() {
		MyDesign d = MyDesign.FOX;
		d.font = d.font.deriveFont(16f);
		return d;
	}
	
	/**
	 * Changes to home page
	 */
	public void home() {
		setResizable(true);
		setSize(pageHome.getSize());
		changePage(pageHome, null);
	}
	
	

}
