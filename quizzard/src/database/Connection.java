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
			con = DriverManager.getConnection("jdbc:postgresql://localhost:6666/q11", "postgres", "al123pha"); // Daten hier �ndern
			//con = DriverManager.getConnection("jdbc:postgresql://unterricht01.gym-friedberg.de/q11", "q11info1", "q11info1"); // Daten hier �ndern
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
		UserData user=new UserData(id, "");
		try {
			set=stm.executeQuery("SELECT * FROM q11info1.player WHERE (id="+id+");");
			if (set.next()) {
				user.setUsername(set.getString("name"));
				user.setTrophies(set.getInt("trophies"));
				user.setOnline(set.getBoolean("online"));;
			}
			//Friends
			set=stm.executeQuery("SELECT friends.player2id FROM q11info1.friends WHERE player1id="+id+";");
			int[] f=new int[lengthQuery(set)]; 
			for(int i=0; i<f.length;i++) {
				f[i]=set.getInt("player2id");
			}
			user.setFriends(f);
			
			//Games
			set=stm.executeQuery("SELECT games.id FROM q11info1.player, q11info1.games, q11info1.playergame WHERE playergame.playerid=player.id AND playergame.gameId=games.id AND player.id="+id+";");
			int[] g=new int[lengthQuery(set)]; 
			for(int i=0; i<g.length;i++) {
				g[i]=set.getInt("id");
			}
			user.setGames(g);
			
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return user;
	}
	
	/**
	 * Creates new GameData Object by game-id
	 * @param id
	 * @return
	 */
	public GameData fetchGameData(int id) {
		ResultSet set = null;
		GameData data=new GameData(id, null, null, null, null, 0, 0);
		ArrayList<Integer> a=new ArrayList<Integer>();
		try {
			set=stm.executeQuery("SELECT * FROM q11info1.games WHERE (id="+id+");");
			if (set.next()) {
				data.setRound(set.getInt("round"));
				data.setMax_rounds(set.getInt("maxRounds"));
				data.setDescription(set.getString("description"));
				data.setPassword(set.getString("password"));
				//TODO: setName of game
				set=stm.executeQuery("SELECT player.id FROM q11info1.player, q11info1.games, q11info1.playergame WHERE playergame.playerid=player.id AND playergame.gameId=games.id AND games.id="+id+";");
				ArrayList<Integer> p=new ArrayList<Integer>();
				while (set.next()) {
					p.add(set.getInt("id"));
				}
				data.setPlayerid(p);
			}

		} catch (SQLException e) {
				e.printStackTrace();
			}
		return data;
	}
	/**
	 * Returns if there are new notifications or not
	 * @return boolean
	 */
	public boolean fetchNotificationStatus(int id) {
		ResultSet set;
		boolean b=false;
		try {
			set=stm.executeQuery("SELECT * FROM q11info1.notification WHERE id="+id+";");
			if (set.next()) {
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	private int lengthQuery(ResultSet set) throws SQLException {
		return set.getFetchSize();
	}
	
	public Notification[] fetchNotifications(int id) {
		ResultSet set;
		Notification[] b=null;
		try {
			set=stm.executeQuery("SELECT * FROM q11info1.notification WHERE playerid="+id+";");
			if (set.next()) {
				int l=lengthQuery(set);
				b=new Notification[l];
				for(int i=0; i<b.length; i++) {
					b[i]=new Notification(set.getInt("id"), set.getString("notification"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * Deletes Notification from database
	 * @param id id of notification
	 */
	public void eraseNotification(int id) {
		
	}
	
	/**
	 * Returns all games currently running on server
	 * @return
	 */
	public GameData[] fetchAllGames() {
		return null;
	}
	
	/**
	 * Alters game data. If game doesnt exists yet, it gets created
	 * @param data
	 */
	public void alterGameData(GameData data) {
		//Wenn Spiel noch nicht existiert, erstellen
		//Bei der id handelt es sich um einen SERIAL type in der Datenbank, d.h. sie wird automatisch im Client erstellt, wenn du ein neues Spiel hinzufuegst.
		//Du musst also keine id in der Datenbank setzen, sondern nur die anderen Spalten. 
		//Um zu pr�fen obb ein spiel bereits existiert, kannst du �berpr�fen, ob es bereits ein Spiel mit dieser id gibt.
	}
	
	/**
	 * inserts notofication into databse
	 * @param playerid
	 * @param n
	 */
	public void sendNotification(int playerid, Notification n) {
		
	}
	
	/**
	 * User enters game
	 * @param id
	 */
	public void joinGame(int id) {

	}
	
	
	
	
}
