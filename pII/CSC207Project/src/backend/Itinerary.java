/**
 * 
 */
package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

/**
 * A Flight Itinerary. An Itinerary is a sequence of Flights for which
 * every previous flight's destination is the following flight's origin and
 * every previous flight arrives less than 6 hours before the following
 * flight's departure.
 * 
 */
public class Itinerary extends Flight implements Serializable {
	
	private static final long serialVersionUID = 7985656353564622420L;
	private List<Flight> flights = new ArrayList<Flight>();
	private String origin; 
	private String destination; 
	private double cost;
	private Date departureTime;
	private Date arrivalTime; 
	
	/**
	 * Creates a new Itinerary object given a list of flights. 
	 * @param newflight a sorted list of flights by departure time. 
	 * @throws InvalidFlightException 
	 */
	public Itinerary(List<Flight> newflight) throws InvalidFlightException {
		Collections.sort(newflight);
		for (Flight flight: newflight){
			this.addFlightEnd(flight);
		}
	}
	
	/**
	 * Creates a new empty Itinerary.  
	 */
	public Itinerary(){
		this.flights = new ArrayList<Flight>();
		this.origin = "";
		this.destination = "";
		this.cost = 0.0;
		this.departureTime = null;
		this.arrivalTime = null; 
	}
	
	/**
	 * Adds a flight to the Iternary and updates all instance variables.  
	 * @param newFlight a new flight to add to the list. 
	 * @throws InvalidFlightException if the flight is invalid. 
	 */
	public void addFlightEnd(Flight newFlight) throws InvalidFlightException {
		if (this.flights.isEmpty()){
			this.flights.add(newFlight);
			this.arrivalTime = newFlight.getArrivalTime();
			this.departureTime = newFlight.getDepartureTime();
			this.cost = newFlight.getCost();
			this.origin = newFlight.getOrigin();
			this.destination = newFlight.getDestination();
		}else{
			if (this.flights.get(this.flights.size()-1).getDestination() == newFlight.getOrigin()
					&& !this.containsLoop(newFlight.getDestination())){
				this.flights.add(newFlight);
				this.cost += newFlight.getCost();
				this.arrivalTime = newFlight.getArrivalTime();
				this.destination = newFlight.getDestination();
			}else{
				String message = "The desination is: " + this.destination + 
						" The flights origin is: " + newFlight.getOrigin(); 
				throw new InvalidFlightException(message);
			}
			
		}
		
	}
	
	/**
	 * Adds a valid new Flight to the beginning of the Itinerary. Raises an error otherwise. 
	 * @param newFlight a new Flight to add to the beginning. 
	 * @throws InvalidFlightException if the Flight's destination is not equal to the origin of 
	 * this Itinerary.
	 */
	public void addFlightBeginning(Flight newFlight) throws InvalidFlightException {
		if (this.flights.isEmpty()){
			this.flights.add(newFlight);
			this.arrivalTime = newFlight.getArrivalTime();
			this.departureTime = newFlight.getDepartureTime();
			this.cost = newFlight.getCost();
			this.origin = newFlight.getOrigin();
			this.destination = newFlight.getDestination();
		}else{
			if (this.flights.get(0).getOrigin() == newFlight.getDestination() 
					&& !this.containsLoop(newFlight.getOrigin())){
				this.flights.add(0, newFlight);
				this.cost += newFlight.getCost();
				this.departureTime = newFlight.getDepartureTime();
				this.origin = newFlight.getOrigin();
			}else{
				String message = "The origin of the Iternary: " + this.destination + 
						" The new flight's desination is: " + newFlight.getDestination(); 
				throw new InvalidFlightException(message);
			}	
		}
	}
	
	/**
	 * Checks if there is already a flight in the itinerary with the same destination or origin.
	 * @param flight a string representing the location of the flight. 
	 * @return boolean true if and only if there is already a flight in the itinerary. 
	 */
	private boolean containsLoop(String flight){
		for (Flight f:this.getFlights()){
			if (f.getOrigin()==flight||f.getDestination()==flight){
				return true;
			}
		}
		return false; 
	}

	/**
	 * @return the flights
	 */
	public List<Flight> getFlights() {
		return flights;
	}


	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}


	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}


	/**
	 * @return the departureTime
	 */
	public Date getDepartureTime() {
		return departureTime;
	}


	/**
	 * @return the arrivalTime
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}


	/**
	 * @return the duration of the itinerary in hours
	 */
	public double getDuration(){
		return (this.getArrivalTime().getTime()-this.getDepartureTime().getTime())
				/(60 * 60D * 1000);
	}
}
