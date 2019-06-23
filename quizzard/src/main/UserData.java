package main;

/**
 * Stores user data. Can update itself using the MainComponent
 * @author danie
 *
 */
public class UserData {
	
	private String username;
	private boolean online;
	private int userid;
	private int[] friends;
	private int[] games;
	
	
	public UserData() {
		
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public boolean isOnline() {
		return online;
	}


	public void setOnline(boolean online) {
		this.online = online;
	}


	public int getUserID() {
		return userid;
	}


	public void setUserID(int userid) {
		this.userid = userid;
	}


	public int[] getFriends() {
		return friends;
	}


	public void setFriends(int[] friends) {
		this.friends = friends;
	}


	public int[] getGames() {
		return games;
	}


	public void setGames(int[] games) {
		this.games = games;
	}
	
	
	

}
