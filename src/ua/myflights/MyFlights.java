package ua.myflights;

import java.awt.EventQueue;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.simple.parser.ParseException;
// https://creately.com/diagram/iwcd4i3f1

public class MyFlights { 

	static mainView window;
	private static User LoggedInUser;
	
	public static void main(String[] args) {
		
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						window = new mainView();
		//				loginView = new loginView();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
	}	
	
	public static void setLoggedInUser(User user){
		LoggedInUser = user;
	}
	
	public static User getLoggedInUser(){
		return LoggedInUser;
	}
	
	public static String getCurrentDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate); //2016-11-16
	}
	
}



























/*
public static SpentTime spentTime = new SpentTime();
private static Thread timer = new Thread(spentTime);



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
	flight = Request.getData("HRK-sky", "KIEV-sky","2016-12-15");
	//spentTime.stopTimer();
	System.out.println(flight.getDestinationPlace() + " " + flight.getPrice());
}*/
