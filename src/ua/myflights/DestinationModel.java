package ua.myflights;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DestinationModel {
	
	public static void add(Flight f){
		
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "INSERT INTO destination "
				+ "(distId, userId, originStation, destinationStation, originStationCode, destinationStationCode, price, duration, arrival, departure, createdAt) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
		
			PreparedStatement pst = conn.prepareStatement(Sql);
			pst.setString(1, f.getDistId());
			pst.setInt(2, MyFlights.getLoggedInUser().getId());
			pst.setString(3, f.getOriginStationName());
			pst.setString(4, f.getDestinationStationName());
			pst.setString(5, f.getOriginStationCode());
			pst.setString(6, f.getDestinationStationCode());
			pst.setDouble(7, f.getPrice());
			pst.setInt(8, (int) f.getDuration());
			pst.setString(9, f.getArrivalTime());
			pst.setString(10, f.getDepartureTime());
			pst.setString(11, MyFlights.getCurrentDate());
			
			pst.execute();
			
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}		
	}
	
	public static void delete(String distId, int userId){
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "DELETE from destination WHERE distId=? and userId=?";

		try {		
			PreparedStatement pst = conn.prepareStatement(Sql);
			pst.setString(1, distId);
			pst.setInt(2, userId);
			pst.execute();							

		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<Flight> getUserDestinations( int userId){
		
		ArrayList<Flight> flights = null;
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "Select * from destination where userId=?";
		
		try{
			
			flights = new ArrayList<Flight>();
			PreparedStatement pst = conn.prepareStatement(Sql);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()){
				
				Flight f = new Flight();
				f.setDBid(rs.getInt("id"));
				f.setDistId(rs.getString("distId"));
				f.setOriginStationName(rs.getString("originStation"));
				f.setOriginStationCode(rs.getString("originStationCode"));
				
				f.setDestinationStationName(rs.getString("destinationStation"));
				f.setDestinationStationCode(rs.getString("destinationStationCode"));
	
				f.setPrice(rs.getDouble("price"));
				f.setPriceUpdated(rs.getDouble("priceUpdated"));
				
				f.setDuration(rs.getLong("duration"));
				f.setArrivalTime(rs.getString("arrival"));
				f.setDepartureTime(rs.getString("departure"));
				
				flights.add(f);
				
			}
			
		}catch(Exception e){
			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return flights;		
				
	}
	
	public static void update(int DBid, double priceUpdated){

		Connection conn = MySqlConnection.dbConnect();
		String Sql = "UPDATE `destination` SET `priceUpdated`= ?,`updatedAt`= ? WHERE `id`= ?";
		
		try{
			PreparedStatement pst = conn.prepareStatement(Sql);
			pst.setDouble(1, priceUpdated);
			pst.setString(2, MyFlights.getCurrentDate());
			pst.setInt(3, DBid);
			pst.execute();
		}catch(Exception e){
			
		}finally{	
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
