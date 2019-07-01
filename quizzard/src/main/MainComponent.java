package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import database.Connection;
import tasks.Task;
import tasks.TaskManager;
import tasks.TimedTask;
import ui.UI;

public class MainComponent {

	/**
	 * UI Component
	 */
	private final UI ui;
	/**
	 * Connection- and Databasecomponent
	 */
	private final Connection connection;
	/**
	 * Data of current user
	 */
	public UserData userData;
	
	public MainComponent() {
		ui = new UI(this);
		connection = new Connection(this);
		connection.connect();
		TaskManager t = new TaskManager(60);
		TimedTask t0 = new TimedTask(() -> System.out.println("TASK"), false, 1f);
		t.add(t0);
		t.start();
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
		ErrCode code = connection.login(username, password);
		if(code == ErrCode.NULL) {
			Console.info("login", "Success", false);
		}
		return code;
	}
	
	/**
	 * Checks if given information is valid and then creates new Account
	 * @param username Username
	 * @param password Password of User
	 * @param passwordRepeat Password of User
	 * @return Error Code
	 */
	public ErrCode register(String username, String password, String passwordRepeat) {
		ErrCode code = ErrCode.NULL;
		if(username.length() == 0)
			code = ErrCode.ERR_REGISTRATION_USERNAME_EMPTY;
		else if(password.length() == 0)
			code = ErrCode.ERR_REGISTRATION_PASSWORD_EMPTY;
		else if(username.length() > 15)
			code = ErrCode.ERR_REGISTRATION_USERNAME_TOO_LONG;
		else if(!password.equals(passwordRepeat))
			code = ErrCode.ERR_REGISTRATION_PASSWORDS_NOT_MATCHING;
		
		String hash = getHash(password); password = null; passwordRepeat = null;
		
		if(code == ErrCode.NULL)
			code = connection.signIn(username, hash);
		
		if(code == ErrCode.NULL) 
			login(username, hash);
		
		
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
	
	/**
	 * Defines what happens after window closes or player decides to exit program
	 */
	public void windowOnClose() {
		connection.disconnect();
	}
	
	
	

}
