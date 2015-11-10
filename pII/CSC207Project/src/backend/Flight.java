package backend;

import java.sql.Time;

public class Flight {
	private String origin; 
	private String destination; 
	private float cost;
	private Time travelTime; 
	private String airline; 
	private int flightNumber;
	
	public Flight(String origin, String destination, float cost, Time travelTime, String airline, int flightNumber) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.cost = cost;
		this.travelTime = travelTime;
		this.airline = airline;
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public float getCost() {
		return cost;
	}

	public Time getTravelTime() {
		return travelTime;
	}

	public String getAirline() {
		return airline;
	}

	public int getFlightNumber() {
		return flightNumber;
	} 
	

}
