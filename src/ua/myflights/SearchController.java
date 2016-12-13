package ua.myflights;

import java.io.IOException;
import java.util.*;

import org.json.simple.parser.ParseException;

public class SearchController {
	
	public static void searchFlights(String From, String To, String date) throws IOException, InterruptedException, ParseException{
		ArrayList<Flight> flights = Request.getData(From, To, date);
		/*System.out.println("size" + flights.size());
		for (int i = 0; i< flights.size(); i++){
			System.out.println(flights.get(i).getOriginStationName() + "," 
					+ flights.get(i).getDestinationStationName() + ","
					+ flights.get(i).getPrice() + ","
					+ flights.get(i).getDuration() + ","
					+ flights.get(i).getDepartureTime() + ","
					+ flights.get(i).getArrivalTime() + ","
					);
		}
		*/
		
		MyFlights.window.show(flights);
	}
	
	public static void searchPlaces(String input) throws IOException, ParseException{
		ArrayList<Place> Places = Request.getPlaces(input) ;
		
		for (int i = 0; i< Places.size(); i++){
			System.out.println(Places.get(i).getPlaceName() + ", " + Places.get(i).getPlaceId());
		}
		
		//MyFlights.window.updateSearchBox(Places);
	}
	
}






















//flight = Request.getData("HRK-sky", "KIEV-sky","2016-12-15");
//flights =  new ArrayList<Flight>();
//flights = Request.getData(From, To, date);	





