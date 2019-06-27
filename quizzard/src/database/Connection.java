package database;

import java.sql.*;

import main.Console;

public class Connection {
	private java.sql.Connection con;
	
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
		Console.info("database", "Connected", false);
		
		
	}
}
