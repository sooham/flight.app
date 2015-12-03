package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * A Flight Itinerary. An Itinerary is a sorted set of Flights for which
 * every previous flight's destination is the following flight's origin and
 * every previous flight arrives less than 6 hours before the following
 * flight's departure.
 * 
 * Additional restriction such as no repetition of origin and destinations
 * is imposed to prevent inefficient routes.
 */
public class Itinerary implements Comparable<Itinerary>, Iterable<Flight>, 
Serializable, Transport {
	
	private static final long SIX_HOURS_TO_MILLISECONDS = (long) 2.16e+7;

	private static final long serialVersionUID = 7985656353564622420L;

	private TreeSet<Flight> flights;	// the Set of flights in sequence for
	                                    // this Itinerary
	
	/**
	 * Constructs an Itinerary from a TreeSet of Flight.
	 * 
	 * @param flights  a TreeSet of Flight objects 
	 * @throws InvalidItineraryExecption if Itinerary is invalid. 
	 */
	public Itinerary(TreeSet<Flight> flights) throws InvalidItineraryException {
		/* TreeSets are used because they automatically maintain natural
		 * ordering of the Flights, have methods like first and last to
		 * get the first and last flights in the set and headset(), tailset()
		 * to get all flights before or after a given flight.
		 */
		this.flights = flights;
		
		// check if valid
		if (!this.isValid()) {
			throw new InvalidItineraryException(
					"The constructor was given an invalid Flight sequence."
			);
		}
	}

	/**
	 * Returns this itinerary's origin city.
	 * 
	 * @return the origin city
	 */
	public String getOrigin() {
		return flights.first().getOrigin();
	}
	
	/**
	 * Returns this itinerary's destination city.
	 * 
	 * @return the destination city
	 */
	public String getDestination() {
		return flights.last().getDestination();
	}

	/**
	 * Returns this itinerary's departing date and time with respect to UTC.
	 * 
	 * @return the departureDateTime
	 */
	public Date getDepartureDateTime() {
		return flights.first().getDepartureDateTime();
	}

	/**
	 * Returns this itinerary's arrival date and time with respect to UTC.
	 * 
	 * @return the arrivalDateTime
	 */
	public Date getArrivalDateTime() {
		return flights.last().getArrivalDateTime();
	}

	/**
	 * Returns the ticket price for this Itinerary.
	 * 
	 * @return the price 
	 */
	public double getPrice() {
		double totalPrice = 0.0;
		for (Flight f: this) {
			totalPrice += f.getPrice();
		}
		return totalPrice;
	}

	/**
	 * Returns the total travel time of this Itinerary in minutes.
	 * 
	 * @return the number of minutes between departure and arrival time.
	 */
	public long getDuration() {
		final long TO_MINUTE= 60000l; // milliseconds
		return (
				getArrivalDateTime().getTime() 
				- getDepartureDateTime().getTime()
				) / TO_MINUTE;
	}

	/**
	 * Returns the Flights in this Itinerary as a List.
	 * 
	 * @return the flights field of this Itinerary
	 */
	public List<Flight> getFlights() {
		return new ArrayList<Flight>(flights);
	}
	
	/**
	 * Returns if this Itinerary is traversable. An Itinerary is traversable
	 * if and only if all flights in the Itinerary form a continuous, 
	 * non-cyclic path in time and cities AND all layovers are 
	 * less than 6 hours long.
	 * 
	 * @return a boolean indicating if the Flight in flights are traversable.
	 */
	public boolean isValid() {
		// check there is no repetition of origins and destinations
		// and all Flights depart where the previous one arrives
		if (flights.isEmpty()) {
			return false;
		}
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
	 * Returns a new Itinerary resulting from concatenating given Itinerary 
	 * to this Itinerary, if the resulting Itinerary is valid, otherwise an 
	 * InvalidItineraryException is thrown.
	 * 
	 * The resulting Itinerary is invalid if it forms a cycle, 
	 * conflicts with in terms of scheduling or creates a 
	 * stopover time of greater than 6 hours.
	 * 
	 * @param itinerary  the Itinerary to concatenate to this Itinerary. 
	 * @throws InvalidItineraryException if the Itinerary formed is invalid. 
	 * TODO: This method should not take in equal Flight containing Itinerary
	 * TODO: Update Junit tests
	 */
	public Itinerary addItinerary(Itinerary itinerary) throws 
	InvalidItineraryException {
		// Create a new TreeSet, being careful not to alias
		TreeSet<Flight> newFlightsTreeSet = (TreeSet<Flight>) flights.clone();
		newFlightsTreeSet.addAll(itinerary.getFlights());
		Itinerary newItinerary = new Itinerary(newFlightsTreeSet);
		return newItinerary;
	}
	
	//TODO: Only needed for Unit Test, delete afterwards
	public Itinerary addFlight(Flight flight) throws 
	InvalidItineraryException {
		// Create a new TreeSet, being careful not to alias
		TreeSet<Flight> newFlightsTreeSet = (TreeSet<Flight>) flights.clone();
		newFlightsTreeSet.add(flight);
		Itinerary newItinerary = new Itinerary(newFlightsTreeSet);
		return newItinerary;
	}
	
	/**
	 * Books a single seat in this Itinerary. If this Itinerary cannot be
	 * booked then does nothing.
	 * TODO: Write Junit test
	 */
	public void bookSeat() {
		if (!this.isFull()) {
			for (Flight f: this) {
				f.bookSeat();
			}
		}
	}

	/**
	 * Returns true iff this Itinerary cannot be booked. An Itinerary is full
	 * iff there exists a Flight in the Itinerary that is full.
	 * 
	 * @return true iff this Itinerary cannot be booked.
	 */
	public boolean isFull() {
		for (Flight f: this) {
			if (f.isFull()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Compares this Itinerary and another Object. Returns true iff other object
	 * is a Itinerary and has identical Flights.
	 * 
	 * @param object  an Object to compare.
	 * @return true iff object is a Itinerary and has identical Flights.
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof Itinerary) {
			if (this.flights.size() == 
					((Itinerary) object).getFlights().size()) {
				for (Flight f: this) {
					if (!((Itinerary) object).getFlights().contains(f)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an iterator over the TreeSet of elements of type Flight
	 */
	@Override
	public Iterator<Flight> iterator() {
		return flights.iterator();
	}

	/**
	 * Returns an integer that shows relative departure time (less, equals
	 * or more than) another Itinerary object.
	 * 
	 * @param other  a Itinerary object 
	 * @returns an integer representing the relative departure times.
	 */
	@Override
	public int compareTo(Itinerary other) {
		return getDepartureDateTime().compareTo(other.getDepartureDateTime());
	}

	/**
	 * Returns the String representation of this Itinerary.
	 * @return the String representation of this Itinerary
	 */
	@Override
	public String toString() {
		return "Itinerary " + getOrigin() + " -> " + getDestination()
				+ " " + getDepartureDateTime().getDate() + " " + getDepartureDateTime().getHours() + ":" + getDepartureDateTime().getMinutes() + " -> " + 
				getArrivalDateTime().getDate() + " " + getArrivalDateTime().getHours() + ":" + getArrivalDateTime().getMinutes();
	}
}
