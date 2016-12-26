package ua.myflights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
		
	private static Connection conn = null;
	
	public static Connection dbConnect(){
		
		try {

			if(conn!=null && !conn.isClosed())
				return conn;
			
			String url = "jdbc:mysql://localhost:3306/myflights";
			String username = "root";
			String password = "";

			System.out.println("Connecting database...");
			
			conn = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
			return conn;
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}
