package database;

import java.sql.*;
import java.util.ArrayList;

import main.Console;
import main.ErrCode;
import main.GameData;
import main.MainComponent;
import main.UserData;
import ui.Notification;


/**
 * Connection between program and database
 * @author warnberger
 */
public class Connection {
	private java.sql.Connection con;
	private Statement stm;
	private ResultSet rs;
	
	private MainComponent mainComponent;
	
	private static final String SPIELER_USERNAME = "username";
	private static final String SPIELER_PW = "password";
	

	
	/**
	 * Creates class Connection
	 * @param m MainComponent
	 */
	public Connection (MainComponent m) {
		mainComponent = m;
	}
	
	/**
	 * Connect the program with SQL database and creates a statement.
	 */
	public void connect() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://unterricht01.gym-friedberg.de/q11", "q11info1", "q11info1"); // Daten hier ändern
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Console.error("database", "Cannot connect to database", true);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Console.error("database", "Cannot load driver", true);
			e.printStackTrace();
		}
		
		try {
			stm=con.createStatement();
		} catch (SQLException e) {
			Console.error("database", "Connot creat statement", true);
			e.printStackTrace();
		}
		
		Console.info("database", "Connected", false);
		
		
	}
	
	/**
	 * Disconnects the program from the database.
	 */
	public void disconnect () {
		try {
			con.close();
		} catch (SQLException e) {
			Console.error("database", "Connot disconnect", true);
			e.printStackTrace();
		}
	}
	/**
	 * Login of a Player
	 * @param name Name of the Player
	 * @param pw Password of the Player
	 * @return Code arrors
	 */
	public ErrCode login (String name, String pw) {
		ErrCode code = ErrCode.NULL;
		ResultSet set = null;
		try {
			set = stm.executeQuery("SELECT * FROM q11info1.player WHERE name='"+name+"';");
			if(set.next() == false) {
				code = ErrCode.ERR_LOGIN_UNKNOWN_USER;
				Console.error("login", "Unknown User '"+name+"'", false);
			}else {
				System.out.println(pw);
				System.out.println(set.getString(SPIELER_PW));
				if(!set.getString(SPIELER_PW).equals(pw)) {
					code = ErrCode.ERR_LOGIN_INCORRECT_PASSWORD;
					Console.error("login", "Incorrect password", false);
				}
			}
		} catch (SQLException e) {
			Console.error("login", "Some Error occured", false);
			e.printStackTrace();
		}
		//UPDATE q11info1.player SET online=true WHERE (name='"+name+"');
		if(code == ErrCode.NULL) {
			Console.info("login", "User '"+name+"' erfolgreich eingeloggt", false);
			mainComponent.setUserData(fetchUserData(name));
			try {
				stm.executeUpdate("UPDATE q11info1.player SET online=true WHERE (name='"+name+"')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return code;
	}
	/**
	 * Creates a new account and you get logged in.
	 * @param name Playername
	 * @param pw Password of the account
	 * @return Errors
	 */
	public ErrCode signIn (String name, String pw) {
		ErrCode code=null;
		ResultSet set = null;
		int id=0;
		try {
			set=stm.executeQuery("SELECT COUNT(name) FROM q11info1.player;");
			if(set.next())
				id=set.getInt("count");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		id=id+1;
		try {
			set=stm.executeQuery("SELECT name FROM q11info1.player WHERE (name='"+name+"');");
				if (!set.next()) {
					stm.executeUpdate("INSERT INTO q11info1.player (name, password, trophies, playedgames, online, id) VALUES ('"+name+"', '"+pw+"', 0, 0, TRUE, "+id+");");
					mainComponent.login(name, pw);
				}
				else {
					code=ErrCode.ERR_REGISTRATION_REDUNDANT_USERNAME;
					Console.error("signin", "Username already exists", false);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return code;
		
	}
	/**
	 * Creates UserData.
	 * @param name Name of the user
	 * @return UserData
	 */
	public UserData fetchUserData(String name) {
		ResultSet set = null;
		UserData user=new UserData(-1, name);
		ArrayList<Integer> a=new ArrayList<Integer>();
		try {
			set=stm.executeQuery("SELECT * FROM q11info1.player WHERE (name='"+name+"');");
			if (set.next()) {
				int id=set.getInt("id");
				
				user.setUserID(id);
				user.setTrophies(set.getInt("trophies"));
				user.setOnline(set.getBoolean("online"));;
			}
			//TODO: Fetch friends
			//TODO: Fetch Games

			
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return user;
		
	}
	/**
	 * Creates UserData.
	 * @param id ID of the user
	 * @return UserData
	 */
	public UserData fetchUserData(int id) {
		ResultSet set = null;
		UserData user=new UserData(-1, name);
		ArrayList<Integer> a=new ArrayList<Integer>();
		try {
			set=stm.executeQuery("SELECT * FROM q11info1.player WHERE (id="+id+");");
			if (set.next()) {
				user.setUsername(set.getString("name"));
				user.setTrophies(set.getInt("trophies"));
				user.setOnline(set.getBoolean("online"));;
			}
			//TODO: Fetch friends
			//TODO: Fetch Games

			
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return user;
	}
	
	//TODO: Hannes
	/**
	 * Creates new GameData Object by game-id
	 * @param id
	 * @return
	 */
	public GameData fetchGameData(int id) {
		ResultSet set = null;
		GameData data=new GameData(id, null, null, 0, 0);
		ArrayList<Integer> a=new ArrayList<Integer>();
		try {
			set=stm.executeQuery("SELECT * FROM q11info1.game WHERE (id="+id+");");
			if (set.next()) {
				data.set ;
				.setUsername(set.getString("name"));
				user.setTrophies(set.getInt("trophies"));
				user.setOnline(set.getBoolean("online"));;
			}
			//TODO: Fetch friends
			//TODO: Fetch Games

			
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return user;
	}
	
	//TODO: Hannes
	/**
	 * Returns if there are new notifications or not
	 * @return boolean
	 */
	public boolean fetchNotificationStatus() {
		/*
		 * Kleine Erklärung: Der Spieler besitzt ein Attribut in der Datenbank, in dem gespeichert wird, ob es neue nachrichten für ihn gibt
		 */
		return false;
	}
	
	//TODO: Hannes
	public Notification[] fetchNotifications() {
		return null;
	}
}
