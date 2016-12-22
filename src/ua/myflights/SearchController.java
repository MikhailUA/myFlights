package ua.myflights;

import java.io.IOException;
import java.util.*;

import org.json.simple.parser.ParseException;

public class SearchController {
	
	public static void searchFlights(String From, String To, String date) throws IOException, InterruptedException, ParseException{
	
		Thread t = new Thread (){
			public void run(){
				MyFlights.window.startProgressBar();
				ArrayList<Flight> flights = null;
				try {
				  flights = Request.getData(From, To, date);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MyFlights.window.show(flights);
				MyFlights.window.stopProgressBar();
			};
		};
		
		t.start();

	}
	
	public static void searchPlaces(String input) throws IOException, ParseException{
		ArrayList<Place> Places = Request.getPlaces(input) ;
		
		for (int i = 0; i< Places.size(); i++){
			System.out.println(Places.get(i).getPlaceName() + ", " + Places.get(i).getPlaceId());
		}
		//MyFlights.window.updateSearchBox(Places);
	}
	
	
	public static void badRequest(String code){
		if (code == "429"){}
	}
}


/*class MyTimer extends Thread{
private boolean stopper = false;
public void run(){
	while(true){
		if (stopper == true){
			break; 
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(".");
	}
}

public void setStop(){
	stopper = true;
}
};

MyTimer timer = new MyTimer();*/ 


//timer.start();
















//flight = Request.getData("HRK-sky", "KIEV-sky","2016-12-15");
//flights =  new ArrayList<Flight>();
//flights = Request.getData(From, To, date);	





