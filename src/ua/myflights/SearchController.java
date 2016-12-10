package ua.myflights;

import java.io.IOException;
import java.util.*;

import org.json.simple.parser.ParseException;

public class SearchController {

	private static Flight flight = null;
	private static ArrayList<Flight> flights;
	
	public static void searchFlights(String From, String To, String date) throws IOException, InterruptedException, ParseException{
		//flight = Request.getData("HRK-sky", "KIEV-sky","2016-12-15");
		flights =  new ArrayList<Flight>();
		flight = Request.getData(From, To, date);	
		flights.add(flight);
		MyFlights.window.show(flights);
	}	
}
