package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ui.UI;

public class MainComponent {

	UI ui;
	
	public MainComponent() {
		ui = new UI(this);
	}
	
	/**
	 * Invokes game
	 * @param args
	 */
	public static void main(String[]args) {
		Console.info("main", "game started", false);
		new MainComponent();
	}
	
	/**
	 * Called by the UI. Attempts to login user using given data. If ErrCode.NULL is returned, the UI assumes that all given
	 * information is correct and modifies its variables/design/environment.
	 * @param username Username (length of 15)
	 * @param password Password
	 * @return Error Code
	 */
	public ErrCode login(String username, String password) {
		//TODO: Check if username exists
		//TODO: Check if hashcode matches
		//TODO: Set Environment
		return ErrCode.ERR_LOGIN_UNKNOWN_USER;
	}
	
	/**
	 * Checks if given information is valid and then creates new Account
	 * @param username Username
	 * @param password Password of User
	 * @param passwordRepeat Password of User
	 * @return Error Code
	 */
	public ErrCode register(String username, String password, String passwordRepeat) {
		if(username.length() == 0)
			return ErrCode.ERR_REGISTRATION_USERNAME_EMPTY;
		if(password.length() == 0)
			return ErrCode.ERR_REGISTRATION_PASSWORD_EMPTY;
		
		if(username.length() > 15)
			return ErrCode.ERR_REGISTRATION_USERNAME_TOO_LONG;
		if(!password.equals(passwordRepeat))
			return ErrCode.ERR_REGISTRATION_PASSWORDS_NOT_MATCHING;
		
		String hash = getHash(password); password = null; passwordRepeat = null;
		
		//TODO: use given information to create new account, wait for its creation beeing finished and then call login(username, password)
		
		return ErrCode.NULL;
	}
	
	/**
	 * Converts any String to unreversible Hash Code
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
