package ua.myflights;

public class Flight {

	private String OriginStationId;
	private String DestinationStatinId;
	private String Date;
	private double Price;
	
	
	private String Id;
	private long Duration;
	private String ArrivalTime;
	private String DepartureTime;
	
	public Flight(String originPlace, String destination, String date, double price){
		this.OriginStationId = originPlace;
		this.DestinationStatinId = destination;
		this.Date = date;
		this.Price = price;
	} 
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public void setOriginStationId(String place){
		this.OriginStationId = place;
	}
	
	public void setDestinationStationId(String place){
		this.DestinationStatinId = place;
	}
	
	public void setDate(String date){
		this.Date = date;
	}
	
	public void setPrice(double price){
		this.Price = price;
	}
	
	public void setId(String Id){
		this.Id = Id;
	}
	
	public String getOriginStationId(){
		return this.OriginStationId;
	}
	
	public String getDestinationStationId(){
		return this.DestinationStatinId;
	}
	
	public double getPrice(){
		return this.Price;
	}

	public String getId(){
		return this.Id;
	}
	
	public String getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return DepartureTime;
	}

	public void setDepartureTime(String departureTime) {
		DepartureTime = departureTime;
	}

	public long getDuration() {
		return Duration;
	}

	public void setDuration(long duration) {
		Duration = duration;
	}
	
}
