package ua.myflights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlConnection {
	
	public static Connection dbConnect() {
		
		String url = "jdbc:mysql://localhost:3306/myflights";
		String username = "root";
		String password = "";

		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
			return connection;
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
}
