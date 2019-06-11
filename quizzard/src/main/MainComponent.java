package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ui.UI;

public class MainComponent {

	UI ui;
	
	public MainComponent() {
		ui = new UI(this);
	}
	
	public static void main(String[]args) {
		Console.info("main", "game started", false);
		new MainComponent();
	}
	
	/**
	 * Called by the UI. Attempts to login using given data
	 * @param username Username (length of 15)
	 * @param password Password
	 * @return Error Code
	 */
	public ErrCode login(String username, String password) {
		return ErrCode.NULL;
	}
	
	public ErrCode register(String username, String password, String passwordRepeat) {
		if(username.length() > 15)
			return ErrCode.ERR_REGISTRATION_USERNAME_TOO_LONG;
		if(!password.equals(passwordRepeat))
			return ErrCode.ERR_REGISTRATION_PASSWORDS_NOT_MATCHING;
		
		String hash = getHash(password); password = null; passwordRepeat = null;
		
		return ErrCode.NULL;
	}
	
	/**
	 * Converts any String to Hash Code
	 * @param passwordToHash The password to be converted
	 * @return The Hashcode as String
	 */
	public String getHash(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
          
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}
	
	

}
