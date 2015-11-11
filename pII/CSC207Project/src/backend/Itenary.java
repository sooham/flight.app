package backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * An object that represents an itenary. Contains a valid list of flights. 
 */
public class Itenary{
	private List<Flight> flights;
	private String origin; 
	private String destination; 
	private double cost;
	private Calendar departureTime;
	private Calendar arrivalTime; 
	
	/**
	 * Creates a new Iternary object given a sorted list of flights. 
	 * @param newflight a sorted list of flights by departure time. 
	 * @throws InvalidFlightException 
	 */
	public Itenary(List<Flight> newflight) throws InvalidFlightException {
		for (Flight flight: newflight){
			this.addFlight(flight);
		}
	}
	
	/**
	 * Creates a new empty Iternary.  
	 */
	public Itenary(){
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
	public void addFlight(Flight newFlight) throws InvalidFlightException {
		if (this.flights.isEmpty()){
			this.flights.add(newFlight);
			this.arrivalTime = newFlight.getArrivalTime();
			this.departureTime = newFlight.getDepartureTime();
			this.cost = newFlight.getCost();
			this.origin = newFlight.getOrigin();
			this.destination = newFlight.getDestination();
		}else{
			if (this.flights.get(-1).getDestination() == newFlight.getOrigin()){
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
	 * @return the flights
	 */
	public List<Flight> getFlights() {
		return flights;
	}

	/**
	 * @param flights the flights to set
	 */
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
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
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
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
	
	
	
}
