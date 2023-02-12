package example02.flightapp01.flight;

public class Flight {
	private int id;
	private String airlineName;
	private String number;
	private String source;
	private String destination;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Flight() {
		super();
	}
	public Flight(int id, String airlineName, String number, String source, String destination) {
		super();
		this.id = id;
		this.airlineName = airlineName;
		this.number = number;
		this.source = source;
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", airlineName=" + airlineName + ", number=" + number + ", source=" + source
				+ ", destination=" + destination + "]";
	}
	
	
}
