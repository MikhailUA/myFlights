package ua.myflights;

public class Flight {

	private String OriginPlace;
	private String DestinationPlace;
	private String Date;
	private double Price;
	
	public Flight(String originPlace, String destination, String date, double price){
		this.OriginPlace = originPlace;
		this.DestinationPlace = destination;
		this.Date = date;
		this.Price = price;
	} 
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public void setOriginPlace(String place){
		this.OriginPlace = place;
	}
	
	public void setDestinationPlace(String place){
		this.DestinationPlace = place;
	}
	
	public void setDate(String date){
		this.Date = date;
	}
	
	public void setPrice(double price){
		this.Price = price;
	}	
	
	public String getOriginPlace(){
		return this.OriginPlace;
	}
	
	public String getDestinationPlace(){
		return this.DestinationPlace;
	}
	
	public double getPrice(){
		return this.Price;
	}
	
}
