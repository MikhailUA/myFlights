package ua.myflights;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;
// https://creately.com/diagram/iwcd4i3f1

public class MyFlights { 

	public static SpentTime spentTime = new SpentTime();
	private static Thread timer = new Thread(spentTime);
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		
		String origin, destination,date;
		Flight flight = null;

		timer.start();
		
		while(true){			
			Scanner scn = new Scanner(System.in);
			//System.out.println(scn.nextLine());
			origin = scn.nextLine();
			destination = scn.nextLine();
			date = scn.nextLine();
		
			//spentTime.startTimer();
			//flight = Request.getData(origin,destination, date);
			flight = Request.getData("KIEV-sky","HRK-sky", "2016-12-15");
			//spentTime.stopTimer();
			System.out.println(flight.getDestinationPlace() + " " + flight.getPrice());
		}
		
	}	
		
}