package database;

import java.beans.FeatureDescriptor;
import java.sql.*;
import java.util.ArrayList;

import main.Console;
import main.ErrCode;
import main.MainComponent;
import main.UserData;


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
			con = DriverManager.getConnection("jdbc:postgresql://unterricht01.gym-friedberg.de/q11", "q11info1", "q11info1");
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
			}else {
				System.out.println(pw);
				System.out.println(set.getString(SPIELER_PW));
				if(!set.getString(SPIELER_PW).equals(pw))
					code = ErrCode.ERR_LOGIN_INCORRECT_PASSWORD;
			}
		} catch (SQLException e) {
			Console.error("login", "Some Error occured", false);
			e.printStackTrace();
		}
		
		if(code == ErrCode.NULL) {
			Console.info("login", "User '"+name+"' erfolgreich eingeloggt", false);
			mainComponent.setUserData(userInformation(name));
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
			set=stm.executeQuery("SELECT name FROM q11info1.player WHERE (name='"+name+"');");
				if (!set.next()) {
					set = stm.executeQuery("SELECT * FROM q11info1.player");
					id = set.getInt(1)+1;
					stm.executeUpdate("INSERT INTO q11info1.player (id, name, password, trophies, playedgames, online) VALUES ("+id+", '"+name+"', '"+pw+"', 0, 0, 0);");
					login(name, pw);
				}
				else {
					code=ErrCode.ERR_REGISTRATION_REDUNDANT_USERNAME;
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
	private UserData userInformation (String name) {
		ResultSet set = null;
		UserData user=new UserData(name);
		ArrayList<Integer> a=new ArrayList<Integer>();
		try {
			//Abfragen müssen noch getestet werden. Jedoch habe ich zuhause leider keine Möglichkeit eine Datenbank zu erstellen.
			set=stm.executeQuery("SELECT * FROM q11info1.player WHERE (name='"+name+"');");
			int id=set.getInt("id");
			user.setUserID(id);
			user.setTrophies(set.getInt("trophies"));
			set=stm.executeQuery("SELECT b.id FROM player AS a, player AS b, friends WHERE (a.id=friends.player1id AND b.id=friends.player2id AND a.name='"+name+"');");
			while (set.next()) {
				a.add((Integer) set.getInt("id"));
			}
			user.setFriends(a);
			set=stm.executeQuery("SELECT b.id FROM player AS a, game AS b, playergame WHERE (a.id=playergame.playerid AND b.id=playergame.gameid AND a.name='"+name+"');");
			while (set.next()) {
				a.add((Integer) set.getInt("id"));
			}
			user.setGames(a);

			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return user;
		
	}
}
