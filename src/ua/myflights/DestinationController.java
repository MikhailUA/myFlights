package ua.myflights;

import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class DestinationController {
	
	public static void addDestionation(Flight f){
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "INSERT INTO destination "
				+ "(distId, userId, originStation, destinationStation, originStationCode, destinationStationCode, price, duration, arrival, departure,createdAt) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			if(!conn.isClosed()){			
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
				
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteDestination(String distId, int userId){
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "DELETE from destination WHERE distId=? and userId=?";

		try {
			if(!conn.isClosed()){			
				PreparedStatement pst = conn.prepareStatement(Sql);
				pst.setString(1, distId);
				pst.setInt(2, userId);
				pst.execute();				
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public static ArrayList<Flight> getDestinations(int userId) throws HeadlessException, SQLException{
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "Select * from destination where userId=?";

		if(!conn.isClosed()){
		
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
		}else{
			System.out.println("connection closed");
		};	
		
		conn.close();
		return flights;
	}
	
	public static void refreshDestinations(int userId){
		
		Thread t = new Thread(){
			public void run(){
				ArrayList<Flight> flightsDB = new ArrayList<Flight>(); 
				ArrayList<Flight> flights = new ArrayList<Flight>();
				try{
					flightsDB = getDestinations(userId);
					for (int i = 0; i < flightsDB.size(); i++){
						Flight f = flightsDB.get(i);
						SimpleDateFormat inputformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
						SimpleDateFormat outputformat = new SimpleDateFormat("yyyy-MM-dd");
						Date DepartureDate = inputformat.parse(f.getDepartureTime());
						if (f.getUpdatedAt() == null || !f.getUpdatedAt().equals(MyFlights.getCurrentDate())){
							
							flights = Request.getData(f.getOriginStationCode(), f.getDestinationStationCode(), outputformat.format(DepartureDate).toString());
							if (flights == null){return;}
							for (int j = 0; j < flights.size(); j++){
								if (f.getDepartureTime().equals(flights.get(j).getDepartureTime())){
									updateDestination(f.getDBid(), flights.get(j).getPrice());
									flights.clear();
									break;
								}
							}
						}
					}
					MyFlights.window.showMyDestinations(getDestinations(userId));				
				}catch (Exception e){
					
				}
			}			
		};
		
		t.start();
	}
	
	
	public static void updateDestination( int DBid, double priceUpdated) throws SQLException{
		Connection conn = MySqlConnection.dbConnect();
		String Sql = "UPDATE `destination` SET `priceUpdated`= ?,`updatedAt`= ? WHERE `id`= ?";
		
		if(!conn.isClosed()){
			
			PreparedStatement pst = conn.prepareStatement(Sql);
			pst.setDouble(1, priceUpdated);
			pst.setString(2, MyFlights.getCurrentDate());
			pst.setInt(3, DBid);
			pst.execute();
		}
		
		conn.close();
		
	}
	
}
