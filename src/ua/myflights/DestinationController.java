package ua.myflights;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DestinationController {
	
	public static void addDestination(Flight f){
		
		DestinationModel.add(f);

	}
	
	public static void deleteDestination(String distId, int userId){
		
		DestinationModel.delete(distId, userId);

	} 
	
	public static void refreshDestinations(int userId){
			
		Thread t = new Thread(){
			public void run(){
				ArrayList<Flight> flightsDB = new ArrayList<Flight>(); 
				ArrayList<Flight> flights = new ArrayList<Flight>();
				try{
					flightsDB = DestinationModel.getUserDestinations(userId);
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
									DestinationModel.update(f.getDBid(), flights.get(j).getPrice());
									flights.clear();
									break;
								}
							}
						}
					}
					MyFlights.window.destinationsView.showMyDestinations(DestinationModel.getUserDestinations(userId));			
				}catch (Exception e){
					
				}
			}			
		};
		
		t.start();
	}
	
}
