package main;

import java.util.ArrayList;

/**
 * Stores user data. Can update itself using the MainComponent
 * @author mehlber
 *
 */
public class UserData {
	
	/**
	 * Username of User
	 */
	private String username;
	/**
	 * Tells if user is online
	 */
	private boolean online;
	/**
	 * id of user. Is used in database
	 */
	private int userid;
	/**
	 * Id list of users friends
	 */
	private int[] friends;
	/**
	 * Id list of users games
	 */
	private int[] games;
	
	private int trophies;
	
	
	public UserData(int id, String username) {
		this.username = username;
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


	public int getTrophies() {
		return trophies;
	}


	public void setTrophies(int trophies) {
		this.trophies = trophies;
	}
	
	
	
	

}
