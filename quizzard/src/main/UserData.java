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
	private ArrayList<Integer> friends;
	/**
	 * Id list of users games
	 */
	private ArrayList<Integer> games;
	
	private int trophies;
	
	
	public UserData(String username, int userid, boolean online, ArrayList<Integer> friends, ArrayList<Integer> games) {
		this.username = username;
		this.userid = userid;
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


	public ArrayList<Integer> getFriends() {
		return friends;
	}


	public void setFriends(ArrayList<Integer> friends) {
		this.friends = friends;
	}


	public ArrayList<Integer> getGames() {
		return games;
	}


	public void setGames(ArrayList<Integer> games) {
		this.games = games;
	}


	public int getTrophies() {
		return trophies;
	}


	public void setTrophies(int trophies) {
		this.trophies = trophies;
	}
	
	
	
	

}
