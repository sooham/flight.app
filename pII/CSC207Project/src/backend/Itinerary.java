/**
 * 
 */
package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @author Angad Singh
 *
 */
public class Itinerary implements Serializable{
	
	private static final long serialVersionUID = 7985656353564622420L;
	private List<Flight> flights = new ArrayList<Flight>();
	private String origin; 
	private String destination; 
	private double cost;
	private Calendar departureTime;
	private Calendar arrivalTime; 
	
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
		this.departureTime = Calendar.getInstance();
		this.arrivalTime = Calendar.getInstance(); 
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
			if (this.flights.get(this.flights.size()-1).getDestination() == newFlight.getOrigin()){
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
	
	public void addFlightBeginning(Flight newFlight) throws InvalidFlightException {
		if (this.flights.isEmpty()){
			this.flights.add(newFlight);
			this.arrivalTime = newFlight.getArrivalTime();
			this.departureTime = newFlight.getDepartureTime();
			this.cost = newFlight.getCost();
			this.origin = newFlight.getOrigin();
			this.destination = newFlight.getDestination();
		}else{
			if (this.flights.get(0).getOrigin() == newFlight.getDestination()){
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
	public Calendar getDepartureTime() {
		return departureTime;
	}


	/**
	 * @return the arrivalTime
	 */
	public Calendar getArrivalTime() {
		return arrivalTime;
	}


	/**
	 * @return the duration of the itinerary in hours
	 */
	public double getDuration(){
		return (this.getArrivalTime().getTime().getTime()-this.getDepartureTime().getTime().getTime())
				/(1000 * 60 * 60D) ;
	}
}
