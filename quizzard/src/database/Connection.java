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
	
	private static final String fetchUserByUsername = "SELECT * FROM q11info1.player WHERE";

	
	/**
	 * Creates class Connection
	 * @param m MainComponent
	 */
	public Connection (MainComponent m) {
		MainComponent main = m;
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
		ArrayList<String> a=new ArrayList<String> ();
		try {
			set = stm.executeQuery(fetchUserByUsername+"(name='"+name+"');");
			if(set.next() == false) {
				code = ErrCode.ERR_LOGIN_UNKNOWN_USER;
				while(set.next()) {
					a.add(set.getString("password"));
				}
			}
			else {
				String p=null;
				while (a.size()>0) {
					p=a.get(0);
					if (p==pw) {
					
					}
				}
			}
		} catch (SQLException e) {
			Console.error("login", "Some Error occured", false);
			e.printStackTrace();
		}
		
		

		return code;
	}
	public ErrCode signIn (String name, String pw) {
		//pw=hashcode(pw);
		//COUNT
		try {
			
			stm.executeUpdate("INSERT INTO q11info1.Player (id, name, password, trophies, playedGames, online) VALUES (3,'"+name+"','"+pw+"', 0,0,'0');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
