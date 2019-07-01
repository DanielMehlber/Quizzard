package main;

import javax.swing.JOptionPane;

/**
 * Used for basic and formatted printouts, debug information, and warnings
 * @author mehlber
 *
 */
public class Console {
	
	/**
	 * Informal debug information
	 * @param title Title / Subject of message
	 * @param message Message of notification
	 * @param popup allow pop up window?
	 */
	public static void info(String title, String message, boolean popup) {
		System.out.println("INFO: <"+title+">: "+message);
		if(popup)
			JOptionPane.showMessageDialog(null, title, message, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * 
	 * @param title Title / Subject of notification
	 * @param message Message of notification
	 * @param popup allow pop up window?
	 */
	public static void error(String title, String message, boolean popup) {
		System.err.println("INFO: <"+title+">: "+message);
		if(popup)
			JOptionPane.showMessageDialog(null, title, message, JOptionPane.ERROR_MESSAGE);
	}

}
