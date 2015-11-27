package backend;

import java.io.Serializable;
import java.util.Date;

/**
 * A Flight. Every Flight has a number, airline name, departure arrival 
 * date and time, origin, destination, ticket price and travel time.
 * 
 * <p>Flight objects will be persistent, hence they implement Serializable.
 * The natural ordering for Flights will be by Date, hence implements
 * Comparable.
 */
public class Flight implements Comparable<Flight>, Serializable {

	private static final long serialVersionUID = 4362700743234104218L;

	private String airline; 
	private long number;
	private String origin; 
	private String destination; 
	private Date departureDateTime;
	private Date arrivalDateTime; 
	private double price;
	private int numSeats;		// the total number of seats in the Flight
	private int numEmptySeats;		// the number of empty seats in the Flight

	/**
	 * Generates a new Flight object.
	 * 
	 * @param airline  the airline.
	 * @param number  a unique number representing this Flight.
	 * @param origin  the location where this Flight begins.
	 * @param destination  the location where this Flight ends.
	 * @param departureDateTime  the Date this Flight departs.
	 * @param arrivalDateTime  the Date this Flight arrives.
	 * @param price  the ticket price for this Flight.
	 * @param numSeats  the number of seats for this Flight.
	 * 
	 * @throws InvalidFlightException if the timing of the Flight is incorrect
	 * or the Flight has the same origin and destination.
	 */
	public Flight(String airline, long number, String origin,
	String destination, Date departureDateTime, Date arrivalDateTime,
	double price, int numSeats) throws InvalidFlightException {
		if (((arrivalDateTime.getTime() - departureDateTime.getTime()) < 0) ||
			 origin.equals(destination)) {
			throw new InvalidFlightException(
					"The Flight constructor inupts were invalid"
					);
		}
		this.airline = airline;
		this.number = number;
		this.origin = origin;
		this.destination = destination;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.price = price;
		this.numSeats = numSeats;
		this.numEmptySeats = numSeats;
	}

	/**
	 * Returns this flight's airline.
	 * 
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Sets this flight's airline.
	 * 
	 * @param airline  the new airline
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * Returns this flight's unique number.
	 * 
	 * @return the flightNumber
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Sets this flight's number.
	 * 
	 * @param number  the new number
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * Returns this flight's origin city.
	 * 
	 * @return the origin city
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Sets the origin of this Flight.
	 * @param origin  the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Returns this flight's destination city.
	 * 
	 * @return the destination city
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the destination of this Flight.
	 * 
	 * @param destination  the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Returns this flight's departing date and time with respect to UTC.
	 * 
	 * @return the departureDateTime
	 */
	public Date getDepartureDateTime() {
		return departureDateTime;
	}

	/**
	 * Set the departure Date and time for this Flight.
	 * 
	 * @param departureDateTime  the departureDateTime to set
	 */
	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	/**
	 * Returns this flight's arrival date and time with respect to UTC.
	 * 
	 * @return the arrivalDateTime
	 */
	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	/**
	 * Set the arrival Date and time for this Flight.
	 * 
	 * @param arrivalDateTime  the arrivalDateTime to set
	 */
	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	/**
	 * Returns the ticket price for this Flight.
	 * 
	 * @return the price 
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of this Flight
	 * 
	 * @param price  the new price of this Flight
	 */
	public void setPrice(double price) {
		price = price;
	}
	
	/**
	 * Returns the number of seats for this Flight.
	 * 
	 * @return the number of seats 
	 */
	public int getNumSeats() {
		return numSeats;
	}

	/**
	 * Sets the number of seats for this Flight
	 * 
	 * @param numSeats  the new number of seats of this Flight
	 */
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	/**
	 * Returns the number of empty seats for this Flight.
	 * 
	 * @return the number of empty seats 
	 */
	public int getNumEmptySeats() {
		return numEmptySeats;
	}

	/**
	 * Sets the number of empty seats for this Flight
	 * 
	 * @param numEmptySeats  the new number of empty seats of this Flight
	 */
	public void setNumEmptySeats(int numEmptySeats) {
		this.numEmptySeats = numEmptySeats;
	}

	/**
	 * Returns an integer that shows relative departure time (less, equals
	 * or more than) another Flight object.
	 * 
	 * @param other  a Flight object 
	 * @returns an integer representing the relative departure times.
	 */
	@Override
	public int compareTo(Flight other) {

		return departureDateTime.compareTo(other.getDepartureDateTime());
	}

	/**
	 * Compares this flight and another Object. Returns true iff other object
	 * is a Flight and has identical fields.
	 * 
	 * @param object  an Object to compare.
	 * @return true iff object is a Flight and has identical fields.
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof Flight) {
			Flight f = (Flight) object;
			return number == f.number &&
					airline.equals(f.airline) && 
					origin.equals(f.origin) &&
					destination.equals(f.destination) &&
					departureDateTime.equals(f.departureDateTime) &&
					arrivalDateTime.equals(f.arrivalDateTime) &&
					price == f.price &&
					numSeats == f.numSeats;
		}
		return false;
	}

	/**
	 * Returns the total travel time of this flight in minutes.
	 * 
	 * @return the number of minutes between departure and arrival time.
	 */
	public long getDuration() {
		final int TO_MINUTE= 60000; // milliseconds
		return (arrivalDateTime.getTime() - departureDateTime.getTime()) /
				TO_MINUTE;
	}

	/**
	 * Returns a String representation of this Flight.
	 * @return a String representation of this Flight.
	 */
	@Override
	public String toString() {
		return "Flight " + number + " from " + origin + " to " + destination
				+ " (" + departureDateTime + " ___ " + arrivalDateTime + ")";
	}
}
