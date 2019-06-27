package main;

import javax.swing.JOptionPane;

public class Console {
	
	public static void info(String title, String message, boolean popup) {
		System.out.println("INFO: <"+title+">: "+message);
		if(popup)
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void error(String title, String message, boolean popup) {
		System.err.println("ERROR: <"+title+">: "+message);
		if(popup)
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

}
