package ua.myflights;

import java.awt.HeadlessException;
import java.sql.*;

import javax.swing.JOptionPane;

public class UserController {

	public static void register(String username, String password){
		
		User usr = getUserByUsername(username);
		
		if (usr.getUsername() != username){
			JOptionPane.showMessageDialog(null, "User already exists", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			
			Connection connection = MySqlConnection.dbConnect();
			String Sql = "INSERT INTO user (username, password) VALUES ( ?, ?)";
			
			PreparedStatement pst;
			try {
				pst = connection.prepareStatement(Sql);
				pst.setString(1, username);
				pst.setString(2, password);
				pst.execute();
				connection.close();
				
				JOptionPane.showMessageDialog(null, "Registered and Logged in");
				login(username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		JOptionPane.showMessageDialog(null, "User exists");
//		MyFlights.window.showMainVWindow();		
	}
	
	public static void login(String username, String password) throws SQLException{
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "Select * from user where username=? and password=?";

		if(!conn.isClosed()){
		
			PreparedStatement pst = conn.prepareStatement(Sql);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()){
				MyFlights.setLoggedInUser(getUser(rs.getInt("id")));
				JOptionPane.showMessageDialog(null, "Logged in. Id: " + MyFlights.getLoggedInUser().getId());
				MyFlights.window.showLoggedInUsername(username);
			}else{
				JOptionPane.showMessageDialog(null, "Invalid credetentials", "Error", JOptionPane.ERROR_MESSAGE);			
			}
		}else{
			System.out.println("connection closed");
		};	
		
		conn.close();
	}
	
	public static void logout(){
		MyFlights.setLoggedInUser(null);
		MyFlights.window.hideLoggedInUsername();
	}
	
	public static User getUser(int id){
		User usr = new User();
		
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "Select * from user where id=?";

		try {
			if(!conn.isClosed()){
			
				PreparedStatement pst = conn.prepareStatement(Sql);
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				
				if (rs.next()){
					usr.setId(rs.getInt("id"));
					usr.setUsername(rs.getString("username"));
				}
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usr;
	}
	
	public static User getUserByUsername(String username){
		User usr = new User();
		
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "Select * from user where username=?";

		try {
			if(!conn.isClosed()){
			
				PreparedStatement pst = conn.prepareStatement(Sql);
				pst.setString(1, username);
				ResultSet rs = pst.executeQuery();
				
				if (rs.next()){
					usr.setId(rs.getInt("id"));
					usr.setUsername(rs.getString("username"));
				}
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usr;
	}
	
}
