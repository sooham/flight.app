package backend;

import java.util.Calendar;
/**
 * An object that represents a flight. Administrators can edit flights. 
 *
 */
public class Flight implements Comparable<Flight>{
	private String origin; 
	private String destination; 
	private float cost;
	private Calendar departureTime;
	private Calendar arrivalTime; 
	private String airline; 
	private int flightNumber;
	

	/**
	 * Generates a new flight object given the parameters. 
	 * @param origin the location from where the flight is leaving from. 
	 * @param destination the location where the flight is going to. 
	 * @param cost the total cost of the flight. 
	 * @param departureTime the time and date of the flight. 
	 * @param arrivalTime the time and date of arrival of the flight. 
	 * @param airline the airline of the flight. 
	 * @param flightNumber a unique number representing the flight. 
	 */
	public Flight(String origin, String destination, float cost, Calendar departureTime, Calendar arrivalTime,
			String airline, int flightNumber) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.cost = cost;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.airline = airline;
		this.flightNumber = flightNumber;
	}



	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}



	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}



	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}



	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}



	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}



	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}



	/**
	 * @return the departureTime
	 */
	public Calendar getDepartureTime() {
		return departureTime;
	}



	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Calendar departureTime) {
		this.departureTime = departureTime;
	}



	/**
	 * @return the arrivalTime
	 */
	public Calendar getArrivalTime() {
		return arrivalTime;
	}



	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(Calendar arrivalTime) {
		this.arrivalTime = arrivalTime;
	}



	/**
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}



	/**
	 * @param airline the airline to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}



	/**
	 * @return the flightNumber
	 */
	public int getFlightNumber() {
		return flightNumber;
	}
	
	
	
	/**
	 * @param flightNumber the flightNumber to set
	 */
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	

	/**
	 * Returns if both Flight objects are the same. 
	 * @param obj a Flight object.
	 * @return true if and only if both Flight objects have the same flight Numbers.
	 */

	public boolean equals(Flight obj) {
		// TODO Auto-generated method stub
		return this.flightNumber == obj.getFlightNumber();
	}
	

	/**
	 * Returns an integer that that represents if this flight object is greater than or 
	 * less than the given Flight object. 
	 * 
	 * @param a Flight object 
	 * @returns an integer representing the difference between the two flight objects 
	 */
	@Override
	public int compareTo(Flight obj) {
		// TODO Auto-generated method stub
		return this.departureTime.compareTo(obj.getDepartureTime());
	}
	

}
