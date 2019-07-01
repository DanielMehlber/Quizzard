package database;

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
		ArrayList<String> n= new ArrayList<String>();
		ArrayList<String> p= new ArrayList<String>();
		ErrCode code = ErrCode.NULL;
		//Hashcode
		//pw=hashcode(pw);
		try {
			ResultSet rs=stm.executeQuery("SELECT name FROM q11info1.Player WHERE (name='"+name+"');");
			while (rs.next()) {
				n.add(rs.getString("name"));
			}
			
			
					
		} catch (SQLException e) {
			Console.error("databank", "Cannot found Player", false);
			code=ErrCode.ERR_LOGIN_UNKNOWN_USER;
			e.printStackTrace();
		}
		try {
			rs=stm.executeQuery("SELECT password FROM q11info1.Player WHERE (name='"+name+"' AND password='"+pw+"');");
			while (rs.next()) {
				p.add(rs.getString("password"));
			}
		} catch(SQLException e) {
			code= ErrCode.ERR_LOGIN_INCORRECT_PASSWORD;
			Console.error("databank", "Incorrect password", false);
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
