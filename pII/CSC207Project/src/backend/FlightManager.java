package backend;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * A FlightManager object. FlightManager is a singleton responsible for handling
 * both Itineraries and Flight objects. All instantiated Flight and Itinerary
 * objects are stored inside FlightManager.
 *
 * <p>Methods for sorting FLight, Itinerary by price and duration, searching
 * Flights and Itinerary by departure date, origin and / or destination.
 */
public class FlightManager implements Serializable {
    /* This FlightManager stores all Flights and Itineraries
     * in a HashMap mapping array [Origin, Destination, DepartureDate]
     * (where time are strings to be parse by SimpleDateFormat)
     * to ArrayList of Itinerary or Flight
     *
     * This Data Structure allows easy searching by specific fields.
     */
    private static final long serialVersionUID = -7587676537029568714L;

    public Map<List<String>, List<Itinerary>> itineraries;
    public Map<List<String>, List<Flight>> flights;

    // The SimpleDateFormat is used to turn strings into Date objects
    private SimpleDateFormat dateFormatter =
            new SimpleDateFormat("yyyy-MM-dd");

    // The singleton instance
    private static FlightManager singletonInstance;

    // Comparator objects for comparing by Price and Duration
    private PriceComparator<Transport> sortPrice;
    private DurationComparator<Transport> sortDuration;

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
     * Updates a flight in this FlightManager.
     *
     * <p> If the given flight already exists in this FlightManager
     * (has the same airline and flight number) then this method will overwrite
     * any changes to the flight in this FlightManager. Otherwise it will add
     * the flight to this FlightManager.
     *
     * @param flight  the Flight to update this FlightManager with.
     */
    public void update(Flight flight) {
        // update to the Hashmap of Flight
        updateFlightsHashMap(flight);
        // update to the Hashmap of Itinerary
        updateItinerariesHashMap(flight);
    }

    /**
     * Updates the flights HashMap with given flight.
     *
     * <p>If the given flight already exists
     * (has the same airline and flight number) then this method will overwrite
     * any changes to the flight in the flights HashMap.
     *
     * @param flight  the Flight to update the flights HashMap with.
     */
    private void updateFlightsHashMap(Flight flight) {
        // If we are editing the HashMap, then we will
        // have to remove all flights with the same airline and flight number
        // from the HashMap (because the flight could have changed origin
        // destination or departure date)
        // TODO: verify if the loop below remove() aliases the flights HashMap
        for (List<Flight> value: flights.values()) {
            value.remove(flight);
        }
        // Regardless of adding or editing we will have to place
        // the flight to its corresponding key
        List<String> key = getKey(flight);
        if (!flights.containsKey(key)){
            flights.put(key, new ArrayList<Flight>());
        }
        flights.get(key).add(flight);
    }

    /**
     * Updates the itinerary HashMap with given flight.
     *
     * <p>If the given flight already exists
     * (has the same airline and flight number) then this method will overwrite
     * any changes to the flight in the itinerary HashMap.
     *
     * @param flight  the Flight to update the itineraries HashMap with.
     */
    private void updateItinerariesHashMap(Flight flight) {
        // If we are editing the HashMap, then we will
        // have to remove all itineraries containing the flight with the
        // same airline and flight number from the HashMap
        // (because the flight could have changed origin
        // destination or date times) making the itinerary invalid
        // TODO: Make sure this code block aliases the HashMap of itinerary
        for (List<Itinerary> value: itineraries.values()) {
            for (Itinerary it: new ArrayList<Itinerary>(value)) {
                if (it.getFlights().contains(flight)) {
                    value.remove(it);
                }
            }
        }
        // TODO: Fix the case where we add a new flight
        // TODO: that joins two previously existing itineraries
        // TODO: and also generates that itineraries sub itiniraries.

        // now that we have removed all the previous occurrences of the
        // flight, we can create new itineraries
        addToItineraries(flight);
    }

    /**
     * Adds a new flight not in the itineraries HashMap to the itineraries
     * HashMap.
     *
     * @param flight  the new flight to add.
     */
    private void addToItineraries(Flight flight) {
        // We first add the Flight by itself as an Itinerary in the HashMap
        TreeSet<Flight> singleFlight = new TreeSet<>();
        singleFlight.add(flight);
        try {
            Itinerary trivialItinerary = new Itinerary(singleFlight);
            List<String> key = getKey(flight);
            if (!itineraries.containsKey(key)){
                itineraries.put(key, new ArrayList<Itinerary>());
            }
            itineraries.get(key).add(trivialItinerary);
        } catch (InvalidItineraryException e) {/*cannot happen */}

		// check if flight is continuous to any key in itineraries and add

		// holds new pairs of key values generated
		Map<List<String>, List<Itinerary>> newKeyValuePairs = new HashMap<>();

		for (List<String> itKey : itineraries.keySet()) {
			boolean continuous = (itKey.get(0) == flight.getDestination() ||
					itKey.get(1) == flight.getOrigin());

			if (continuous) {
				// we can just add this flight to all Itinerary mapped by
				// itKey. All time and non cyclic logic is dealt with by
				// Itinerary.addFlight() method
				for (Itinerary it : itineraries.get(itKey)) {
					// make a new itinerary with flight
					try {
						Itinerary newItinerary = it.addFlight(flight);
						// make the key for this itinerary
						List<String> newKey = getKey(newItinerary);
						// check if this key already exists in Map
						if (itineraries.containsKey(newKey)) {
							itineraries.get(newKey).add(newItinerary);
						} else {
							// put in addPairs
							if (!newKeyValuePairs.containsKey(newKey)) {
								newKeyValuePairs.put(
										newKey,
										new ArrayList<Itinerary>()
										);
							}
							newKeyValuePairs.get(newKey).add(newItinerary);
						}
					} catch (InvalidItineraryException c) {/*skip*/}
				}
			}
		}
		// finally take all the key value pairs and put them into HashMap
		itineraries.putAll(newKeyValuePairs);
    }

    /**
     * Gets all non-full itineraries in this FlightManager by given 
     * origin, destination and departure date.
     *
     * @param origin  the origin of the Itinerary
     * @param destination  the destination of the Itinerary
     * @param departureDate  the departure date of the Itinerary
     * @return an List of all Itinerary meeting criterion, if criterion
     * is not met return empty List of Itinerary
     */
    public List<Itinerary> getItineraries(String origin, String destination,
    		String departureDate) {
        List<String> key = new ArrayList<>();
        key.add(origin);
        key.add(destination);
        key.add(departureDate);
        
        if (itineraries.containsKey(key)){
        	// filter all non-full Itinerary from the List mapped
        	// to by key
        	List<Itinerary> result = new ArrayList<Itinerary>();
        	for (Itinerary it: itineraries.get(key)) {
        		if (!it.isFull()) {
        			result.add(it);
        		}
        	}
        	return result;
        }
        return new ArrayList<Itinerary>();
    }


    /**
     * Gets all non-full Flight in this FlightManager by given 
     * origin, destination and departure date.
     *
     * @param origin  the origin of the Flight
     * @param destination  the destination of the Flight
     * @param departureDate  the departure date of the Flight
     * @return an List of all Flight meeting criterion, if criterion
     * is not met return empty List of Flight
     */
    public List<Flight> getFlights(String origin, String destination,
                                   String departureDate) {
        List<String> key = new ArrayList<>();
        key.add(origin);
        key.add(destination);
        key.add(departureDate);
        if (flights.containsKey(key)){
        	// filter all non-full Itinerary from the List mapped
        	// to by key
        	List<Flight> result = new ArrayList<Flight>();
        	for (Flight f: flights.get(key)) {
        		if (!f.isFull()) {
        			result.add(f);
        		}
        	}
        	return result;
        }
        return new ArrayList<Flight>();
    }

    /**
     * Sorts a List of Flight or Itinerary by Price.
     * @param list  a list of Flight or Itinerary
     */
    public void sortByPrice(List<? extends Transport> list) {
        Collections.sort(list, sortPrice);
    }

    /**
     * Sorts a List of Flight or Itinerary by Duration.
     * @param list  a list of Flight or Itinerary
     */
    public void sortByDuration(List<? extends Transport> list) {
        Collections.sort(list, sortDuration);
    }

    /**
     * Returns the key for the given Transport to use with this FlightManager.
     *
     * @param t a Transport.
     * @return the key List<String> stating origin, destination and departure
     * date.
     */
    private List<String> getKey(Transport t) {
        List<String> key = new ArrayList<>();
        key.add(t.getOrigin());
        key.add(t.getDestination());
        key.add(dateFormatter.format(t.getDepartureDateTime()));

        return key;
    }
}
