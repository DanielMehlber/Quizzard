package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import database.Connection;
import tasks.TaskManager;
import tasks.TimedTask;
import ui.Notification;
import ui.UI;

/**
 * Connects all components and does the work inbetween
 * @author mehlber
 *
 */
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
		connection.fetchGameData(0);
		TaskManager t = new TaskManager(60);
		t.add(new TimedTask(()->refreshGames(), true, 10f));
		t.add(new TimedTask(()->refreshNotifcations(), true, 5f));
		t.add(new TimedTask(()->refreshProfile(), true, 10f));
		t.start();
	}
	
	/**
	 * Invokes game
	 * @param args console args
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
			ui.pageHome.uiProfile.setUserData(userData);
			ui.pageHome.entered();
			ui.home();
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
	
	public void setUserData(UserData userdata) {
		this.userData = userdata;
		ui.pageHome.uiProfile.setUserData(userdata);
	}
	
	public void refreshAll() {
		refreshProfile();
		refreshGames();
		refreshNotifcations();
	}
	
	public void refreshNotifcations() {
		if(anyNewNotifications())
			ui.pageHome.uiNotifications.setNotifications(connection.fetchNotifications(userData.getUserID()));
	}
	
	public void refreshProfile() {
		if(userData==null)
			return;
		userData = connection.fetchUserData(userData.getUserID());
		ui.pageHome.uiProfile.setUserData(userData);
	}
	
	public void refreshGames() {
		Console.info("refresh", "Refreshing game list", false);
		if(userData==null)
			return;
		int[] gameids = userData.getGames();
		ArrayList<GameData> gd = new ArrayList<GameData>();
		for(int id : gameids) {
			gd.add(connection.fetchGameData(id));
		}
		ui.pageHome.uiGames.setGames(gd.toArray(new GameData[gd.size()]));
	}
	
	public void eraseNotification(int id) {
		connection.eraseNotification(id);
	}
	
	public boolean anyNewNotifications() {
		if(userData==null)
			return false;
		return connection.fetchNotificationStatus(userData.getUserID());
	}
	
	public void createGame(GameData data) {
		connection.alterGameData(data);
		refreshGames();
	}
	
	public Topic[] getAllTopics() {
		//TODO: getAllTopics
		return null;
	}
	
	public void playGame(int id) {
		GameData game = connection.fetchGameData(id);
		//TODO: getQuestions by current round
		//TODO: UI Questions
	}
	
	public void sendAnswerResults(int gameid, int correctAnswers) {
		
	}
	

	

}
