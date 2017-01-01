package ua.myflights;

import java.io.IOException;
import java.util.*;

import org.json.simple.parser.ParseException;

public class SearchController {
	
	public static void searchFlights(String From, String To, String date) throws IOException, InterruptedException, ParseException{
	
		Thread t = new Thread (){
			public void run(){
				MyFlights.window.searchResultsView.startProgressBar();
				ArrayList<Flight> flights = null;

				  try {
					flights = Request.getData(From, To, date);
				} catch (IOException | InterruptedException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				MyFlights.window.searchResultsView.show(flights);
				MyFlights.window.searchResultsView.stopProgressBar();
			};
		};
		
		t.start();

	}
	

	public static void searchPlaces(String input) throws IOException, ParseException{
		ArrayList<Place> Places = Request.getPlaces(input) ;
		
		for (int i = 0; i< Places.size(); i++){
			System.out.println(Places.get(i).getPlaceName() + ", " + Places.get(i).getPlaceId());
		}
	}
}


