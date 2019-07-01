package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.danielmehlber.myui.MySyncTask;

public class Logo extends JPanel{

	private BufferedImage image;
	
	public Logo() {
		setOpaque(false);
		new Thread( () -> {
		File classPathInput = new File(this.getClass().getResource("LogoProjekt.png").getFile());
        try {
			image = ImageIO.read(classPathInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        repaint();
        
        int y = getY();
        while(true) {
        	setLocation(getX(), (int) (y + Math.sin((float)System.nanoTime() * 0.000000005) * 5));
        	MySyncTask.sync(60);
        }
        
		}).start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(image == null)
			return;
		Graphics2D g2d = (Graphics2D) g;
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	
	
}
