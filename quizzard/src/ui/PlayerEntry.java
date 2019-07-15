package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.danielmehlber.myui.MyLabel;
import com.danielmehlber.myui.MyPanel;

import main.UserData;

public class PlayerEntry extends MyPanel{

	private UI ui;
	private final MyPanel image;
	private UserData userdata;
	
	public PlayerEntry(UI ui, UserData data) {
		super(ui.getDesign());
		setColorStyle(COLOR_STYLE.DESIGN_ACCENT);
		setLayout(null);
		userdata = data;
		image = new MyPanel(ui.getDesign());
		image.setBounds(10, 10, 64, 64);
		image.setRoundness(image.getWidth());
		add(image);
		
		MyLabel lblName = new MyLabel(ui.getDesign());
		lblName.setBounds(83, 10, 207, 30);
		lblName.setText("MR. NOBODY");
		add(lblName);
		
		MyLabel lblTrophies = new MyLabel(ui.getDesign());
		lblTrophies.setBounds(83, 43, 207, 30);
		lblTrophies.setText("999");
		add(lblTrophies);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		Color c = userdata.isOnline() ? Color.GREEN : Color.RED;
		g2d.setColor(c);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawOval(image.getX(), image.getY(), image.getWidth(), image.getHeight());
	}

}
