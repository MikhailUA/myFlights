package ua.myflights;

public class Flight {

	private String OriginStationId;
	private String OriginStationName;
	private String OriginStationCode;
	
	private String DestinationStationId;
	private String DestinationStationName;
	private String DestinationStationCode;
	
	private String Date;
	private double Price;	
	
	private String DistId;
	private long Duration;
	private String ArrivalTime;
	private String DepartureTime;
	
	
	
	public Flight(String originPlace, String destination, String date, double price){
		this.OriginStationId = originPlace;
		this.DestinationStationId = destination;
		this.Date = date;
		this.Price = price;
	} 
	
	public Flight(String DistId, String OriginStationName, String OriginStationCode, String DestinationStationName, String DestinationStationCode, double Price ) {
		this.DistId = DistId;
		this.OriginStationName = OriginStationName;
		this.OriginStationCode = OriginStationCode;
		this.DestinationStationName = DestinationStationName;
		this.DestinationStationCode = DestinationStationCode;		
		this.Price = Price;
	}

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public void setOriginStationId(String place){
		this.OriginStationId = place;
	}
	
	public void setDestinationStationId(String place){
		this.DestinationStationId = place;
	}
	
	public void setDate(String date){
		this.Date = date;
	}
	
	public void setPrice(double price){
		this.Price = price;
	}
	
	public void setDistId(String Id){
		this.DistId = Id;
	}
	
	public String getOriginStationId(){
		return this.OriginStationId;
	}
	
	public String getDestinationStationId(){
		return this.DestinationStationId;
	}
	
	public double getPrice(){
		return this.Price;
	}

	public String getDistId(){
		return this.DistId;
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

	public void setOriginStationName(String Name) {
		OriginStationName = Name;
	}

	public void setDestinationStationName(String Name) {
		// TODO Auto-generated method stub
		DestinationStationName = Name;
	}

	public String getOriginStationName() {
		// TODO Auto-generated method stub
		return this.OriginStationName;
	}

	public String getDestinationStationName() {
		// TODO Auto-generated method stub
		return this.DestinationStationName;
	}
	
	public void save(){
		
	}

	public String getOriginStationCode() {
		return OriginStationCode;
	}

	public void setOriginStationCode(String originStationCode) {
		OriginStationCode = originStationCode;
	}

	public String getDestinationStationCode() {
		return DestinationStationCode;
	}

	public void setDestinationStationCode(String destinationStationCode) {
		DestinationStationCode = destinationStationCode;
	}
	
}
