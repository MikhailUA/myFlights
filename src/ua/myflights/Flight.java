package ua.myflights;

public class Flight {

	private String OriginStationId;
	private String OriginStationName;
	private String OriginStationCode;
	
	private String DestinationStationId;
	private String DestinationStationName;
	private String DestinationStationCode;
	
	private String createdAt;
	private String updatedAt;
	private double Price;	
	private double PriceUpdated;
	
	private int DBid;
	private String DistId;
	private long Duration;
	private String ArrivalTime;
	private String DepartureTime;
	 
	
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public double getPriceUpdated() {
		return PriceUpdated;
	}

	public void setPriceUpdated(double priceUpdated) {
		PriceUpdated = priceUpdated;
	}

	public int getDBid() {
		return DBid;
	}

	public void setDBid(int dBid) {
		DBid = dBid;
	}
	
}
