/**
 * 
 */
package backend;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.io.Serializable;

/**
 * A Flight Itinerary. An Itinerary is a set of Flights for which
 * every previous flight's destination is the following flight's origin and
 * every previous flight arrives less than 6 hours before the following
 * flight's departure.
 * 
 * Additional restriction such as no repetition of origin and destinations
 * is imposed to prevent inefficient routes.
 * 
 * Itineraries have all fields inherited from Flight and a TreeSet of flights,
 * due to no cycles. Every Itinerary has a unique identifier number.
 */
public class Itinerary extends Flight implements Serializable {
	
	private static final long SIX_HOURS_TO_MILLISECONDS = (long) 2.16e+7;

	private static final long serialVersionUID = 7985656353564622420L;
	private static long idCount = 0; // the ID to be assigned next Itinerary

	private TreeSet<Flight> flights;	// the Set of flights in sequence for
										// this Itinerary
	
	/**
	 * Constructs an Itinerary from a TreeSet of Flight.
	 * 
	 * @param flights  a TreeSet of Flight objects 
	 * @throws InvalidItineraryExecption if Itinerary is not valid. 
	 */
	public Itinerary(TreeSet<Flight> flights) throws InvalidItineraryException,
	InvalidFlightException {
		/* TreeSets are used because they automatically maintain natural
		 * ordering of the elements, have methods like first and last to
		 * get the first and last flights in the set and headset(), tailset()
		 * to get all flights before or after a given flight.
		 * 
		 * Because we will Itineraries directly into files, there is no
		 * need to modify the middle elements of the set of Flights.
		 */
			super(flights.first().getAirline(),
				  idCount,
				  flights.first().getOrigin(),
				  flights.last().getDestination(),
				  flights.first().getDepartureDateTime(),
				  flights.last().getArrivalDateTime(),
				  0.0);
			  
		// Set the price of this itinerary
		double totalItineraryPrice = 0.0; 
		for (Flight f: flights) {
			totalItineraryPrice += f.getPrice();
		}
		setPrice(totalItineraryPrice);
		
		this.flights = flights;
		
		// check if valid
		if (!this.isValid()) {
			throw new InvalidItineraryException(
					"The constructor was given an invalid Flight sequence."
			);
		}

		// increase idCount
		idCount++;
	}
	
	/**
	 * Returns the TreeSet of Flights in this Itinerary.
	 * 
	 * @return the flights field of this Itinerary
	 */
	public TreeSet<Flight> getFlights() {
		return flights;
	}
	
	/**
	 * Returns if this Itinerary is traversable. An Itinerary is traversable
	 * if and only if all flights in the Itinerary form a non-cyclic path in
	 * time and cities AND all layovers are less than 6 hours long.
	 * 
	 * @return a boolean indicating if the Flight in flights are traversable.
	 */
	public boolean isValid() {
		// check there is no repetition of origins and destinations
		// and all Flights depart where the previous one arrives
		if (flights.size() == 1) {
			return true;
		}
		
		List<String> cities = new ArrayList<String>();
		cities.add(flights.first().getOrigin());
		cities.add(flights.first().getDestination());
		
		// Go over the Flights starting from the second flight
		for (Flight f = flights.higher(flights.first()); f != null;
				f = flights.higher(f)) {
			
			// check if the flight starts where the previous flight ended
			boolean continuous = f.getOrigin().equals(
												cities.get(cities.size() - 1));

			// check if the layover time is less than six hours
			long travelTime = (
				f.getDepartureDateTime().getTime() 
				- flights.lower(f).getArrivalDateTime().getTime()
			); 
			boolean shortLayover = (0 <= travelTime) &&
					(travelTime <= SIX_HOURS_TO_MILLISECONDS);
			
			// check if the flight is non-cyclic
			boolean nonCyclic = !cities.contains(f.getDestination());
			
			if (continuous && shortLayover && nonCyclic) {
				// continue checking the next Flight in Itinerary
				cities.add(f.getDestination());
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a new Itinerary with a given Flight added. If the new 
	 * Itinerary is valid. Otherwise an InvalidItineraryException is
	 * thrown.
	 * 
	 * A Flight is invalid if it forms a cycle in the Itinerary,
	 * conflicts with any other Flight in the itinerary in terms of scheduling
	 * or creates a stopover time of greater than 6 hours.
	 * 
	 * @param newFlight  the flight to add to this Itinerary. 
	 * @throws InvalidItineraryException if the flight is invalid. 
	 */
	public Itinerary addFlight(Flight newFlight) throws 
	InvalidItineraryException, InvalidFlightException {
		// Create a new TreeSet, being careful not to alias
		TreeSet<Flight> newFlights = (TreeSet<Flight>) flights.clone();
		newFlights.add(newFlight);
		Itinerary newItinerary = new Itinerary(newFlights);
		return newItinerary;
	}
	
	public static void main(String[] args) {
		try {
			Flight f = new Flight("quanta", 123, "Paris", "NY", new Date(2012, 10, 12, 6, 0), new Date(2012, 10, 13, 5, 50), 1300.0);
			Flight g = new Flight("emirates", 345, "NY", "York", new Date(2012, 10, 13, 11, 50), new Date(2012, 10, 13, 21, 50), 100.0);
			Flight h = new Flight("AA", 370, "York", "Shanghai", new Date(2012, 10, 14, 1, 20), new Date(2012, 10, 14, 12, 00), 300.0);

			TreeSet<Flight> flights = new TreeSet<Flight>();
			flights.add(f);
			flights.add(g);
			flights.add(h);
			Itinerary newIt = new Itinerary(flights);
		} catch (InvalidItineraryException e) {
			
		} catch (InvalidFlightException f) {
			
		}
	}
}
