package database;

import java.beans.FeatureDescriptor;
import java.sql.*;
import java.util.ArrayList;

import main.Console;
import main.ErrCode;
import main.MainComponent;


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
			//TODO: mainComponent.setUserData(...)
		}

		return code;
	}
	public ErrCode signIn (String name, String pw) {
		ErrCode code=null;
		ResultSet set = null;
		int id=0;
		//Hannes, bitte nur überprüfen, ob ein nutzername wie der gegebene schon existiert. ~Daniel
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
}
