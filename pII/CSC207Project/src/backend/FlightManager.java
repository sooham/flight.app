package backend;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * A FlightManager object. FlightManager is a singleton responsible for handling
 * both Itineraries and Flight objects. All instantiated Flight and Itinerary
 * objects are stored inside FlightManager.
 * 
 * <p>Methods for sorting FLight, Itinerary by price and duration, searching
 * Flights and Itinerary by departure date, origin and / or destination.
 */
public class FlightManager implements Serializable{
	/* This FlightManager stores all Flights and Itineraries
	 * in a HashMap mapping array [Origin, Destination, DepartureDate] 
	 * (where time are strings to be parse by SimpleDateFormat)
	 * to ArrayList of Itinerary or Flight
	 * 
	 * This Data Structure allows easy searching by specific fields.
	 */
	private static final long serialVersionUID = -7587676537029568714L;
	
	public Map<String[], ArrayList<Itinerary>> itineraries; 
	public Map<String[], ArrayList<Flight>> flights;

	// The SimpleDateFormat is used to turn strings into Date objects
	private SimpleDateFormat dateFormatter = 
			new SimpleDateFormat("yyyy-MM-dd");
	
	// The singleton instance
	private static FlightManager singletonInstance;
	
	// Comparator objects for comparing by Price and Duration
	private PriceComparator<Flight> sortPrice;
	private DurationComparator<Flight> sortDuration;
	
	/**
	 * Constructs an empty FlightManager object.
	 */
	private FlightManager() {
		itineraries = new HashMap<>();
		flights = new HashMap<>();
		sortPrice = new PriceComparator<>();
		sortDuration = new DurationComparator<>();
	}
	
	/**
	 * Returns the singleton instance of this class. If the singleton has
	 * not been instantiated, this method constructs the singleton and
	 * returns it.
	 * 
	 * @return the singleton FlightManager
	 */
	public static FlightManager getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new FlightManager();
		}
		return singletonInstance;
	}

	/**
	 * Adds or edits a flight to this FlightManager. Updates the Hashtable 
	 * of Flights and Itineraries as necessary.
	 * 
	 * @param newFlight  the flight to add
	 */
	public void addFlight(Flight newFlight) {
		// Add to the Hashmap of Flight
		// Check if key is in Hashmap and create if necessary
		String [] key = {
				newFlight.getOrigin(),
				newFlight.getDestination(),
				dateFormatter.format(newFlight.getDepartureDateTime())
		};

		if (!flights.containsKey(key)) {
			// add the key and value
			flights.put(key, new ArrayList<Flight>());
		}

		if (!flights.get(key).contains(newFlight)) {
			flights.get(key).add(newFlight);
		}

		// Add to the Hashmap of Itinerary
		// If the flight is connected to any itinerary X
		// 		add the flight to the itinerary X
		//		store the new itinerary in FlightManager itineraries
		// if the flight is not connected to itinerary X
		//		if the flight is not connected to any itinerary
		//			create a new itinerary and add
		
		addtoItineraries(newFlight);
	}
	
	/**
	 * Updates the HashMap of Itineraries in this FlightManager when adding
	 * a new Flight.
	 * 
	 * @param f  the new Flight object to add.
	 */
	private void addtoItineraries(Flight f) {
		// A single Flight alone is also an itinerary, so we can first add
		// that
		TreeSet<Flight> t = new TreeSet<>();
		t.add(f);

		try {
		Itinerary trivialItinerary = new Itinerary(t);
		String[] key = {
				f.getOrigin(), 
				f.getDestination(),
				dateFormatter.format(f.getDepartureDateTime())
				};
		itineraries.putIfAbsent(key, new ArrayList<Itinerary>());
		itineraries.get(key).add(trivialItinerary);
		} catch(Exception e) {
			// This exception will not be thrown since Flight is valid
		}

		// check if the flight f is continuous to any key in HashMap of
		// itineraries
		
		// holds new pairs of key values for itineraries generated
		Map<String[], ArrayList<Itinerary>> addPairs = new HashMap<>(); 

		for (String[] key : itineraries.keySet()) {
			boolean continuous = (key[0] == f.getDestination() ||
					key[1] == f.getOrigin());

			if (continuous) {
				// we can just add this flight to all the Itinerary in
				// the mapped values, time and non cyclic logic is dealt with
				// by Itinerary.addFlight() method
				for (Itinerary it : itineraries.get(key)) {
					try {
						// make a new itinerary with flight f
						Itinerary newItinerary = it.addFlight(f);
						// make the key for this itinerary
						String[] newKey = {
								newItinerary.getOrigin(),
								newItinerary.getDestination(),
								dateFormatter.format(
										newItinerary.getDepartureDateTime()
										)};
						
						// check if this key already exists in itineraries
						if (itineraries.containsKey(newKey)) {
							itineraries.get(newKey).add(newItinerary);
						} else {
							// put in addPairs
							addPairs.putIfAbsent(
										newKey, new ArrayList<Itinerary>()
									);
							addPairs.get(newKey).add(newItinerary);
						}
					} catch (InvalidItineraryException e) {
						// The timing of the itinerary invalid
					}
				}
			} 
		}
		
		// finally take all the key value pairs in addPairs and put them 
		// into Hashmap of itineraries
		itineraries.putAll(addPairs);
	}

	/**
	 * Gets all Itinerary in this FlightManager by given origin, destination 
	 * and departure date.
	 * 
	 * @param origin  the origin of the Itinerary
	 * @param destination  the destination of the Itinerary
	 * @param departureDate  the departure date of the Itinerary
	 * @return an ArrayList of all Itinerary meeting criterion, if criterion
	 * is not met return empty ArrayList of Itinerary
	 */
	public ArrayList<Itinerary> getItineraries(String origin,
	String destination, String departureDate) {
		String[] key = {origin, destination, departureDate};
		return itineraries.getOrDefault(key, new ArrayList<Itinerary>());
	}
	

	/**
	 * Gets all Flight in this FlightManager by given origin, destination 
	 * and departure date.
	 * 
	 * @param origin  the origin of the Flight
	 * @param destination  the destination of the Flight
	 * @param departureDate  the departure date of the Flight
	 * @return an ArrayList of all Flight meeting criterion, if criterion
	 * is not met return empty ArrayList of Flight
	 */
	public ArrayList<Flight> getFlights(String origin, String destination,
	String departureDate) {
		String[] key = {origin, destination, departureDate};
		return flights.getOrDefault(key, new ArrayList<Flight>());
	}
	
	/**
	 * Sorts a List of Flight or Itinerary by Price.
	 * @param list  a list of Flight or Itinerary
	 */
	public void sortByPrice(List<? extends Flight> list) {
		Collections.sort(list, sortPrice);
	}

	/**
	 * Sorts a List of Flight or Itinerary by Duration.
	 * @param list  a list of Flight or Itinerary
	 */
	public void sortByDuration(List<? extends Flight> list) {
		Collections.sort(list, sortDuration);
	}
}
