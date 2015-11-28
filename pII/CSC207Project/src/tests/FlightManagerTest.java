package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.TreeMap;
import java.text.ParseException;

import backend.FlightManager;
import backend.Itinerary;
import backend.Flight;
import backend.InvalidItineraryException;
import backend.InvalidFlightException;

public class FlightManagerTest {
	private FlightManager flightManager;
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");


	@Before
	public void setUp() throws Exception {
		flightManager = FlightManager.getInstance();
		assertTrue(flightManager.itineraries.isEmpty());
		assertTrue(flightManager.flights.isEmpty());
	}

	/*
	
	@Test
	public void addFLightShouldNotAddPrexistingFlight() {
		// First add a Flight to the FM
		Flight f = null;
		try {
		f = new Flight("Quanta", 315l, "A", "B", dateTimeFormatter.parse("2015-08-19 0:00"),
				dateTimeFormatter.parse("2015-08-19 6:00"), 300.0);
		flightManager.addFlight(f);
		} catch (Exception e) {}
		
		// Now verify it has been added from flights field
		ArrayList<Flight> a = new ArrayList<>();
		a.add(f);
		assertTrue(flightManager.flights.containsValue(a));

		HashMap<String[], ArrayList<Flight>> oldFlights = (HashMap<String[], ArrayList<Flight>>) flightManager.flights.clone();
		HashMap<String[], ArrayList<Itinerary>> oldItineraries = (HashMap<String[], ArrayList<Itinerary>>) flightManager.itineraries.clone();
		// Now add the same old thing again
		flightManager.addFlight(f);
		assertEquals(flightManager.flights, oldFlights);
		assertEquals(flightManager.itineraries, oldItineraries);
	}
	
	@Test
	public void addFlightShouldAddNewFlight() {
		// Make the Flight, save the old flights and itineraries
		Flight f = null;
		try {
		f = new Flight("Emirates", 707l, "M", "N", dateTimeFormatter.parse("2015-08-19 18:00"),
				dateTimeFormatter.parse("2015-08-19 23:00"), 700.0);
		} catch (Exception e) {}
		
		ArrayList<Flight> a = new ArrayList<>();
		a.add(f);
		assertTrue(!flightManager.flights.containsValue(a));
		HashMap<String[], ArrayList<Flight>> oldFlights = (HashMap<String[], ArrayList<Flight>>) flightManager.flights.clone();
		HashMap<String[], ArrayList<Itinerary>> oldItineraries = (HashMap<String[], ArrayList<Itinerary>>) flightManager.itineraries.clone();
		// Now add the unseen value
		flightManager.addFlight(f);
		assertNotEquals(flightManager.flights, oldFlights);
		assertNotEquals(flightManager.itineraries, oldItineraries);
		assertTrue(flightManager.flights.size() == oldFlights.size() + 1);
	}
	
	*/
	
	/*
	@Test
	public void addFlightShouldCreateValidItineraryList() throws InvalidFlightException, InvalidItineraryException {
		// This method should be run by itself first without any other tests running

		// time conflict, city discontinuity, layover too long, cyclical

		// first add an starting flight
		Flight startFlight = null;
		// a flight in direct time conflict with another flight but city continuous
		Flight startFlightFollowingButTimeDiscont = null;
		// a flight linking correctly with another itinerary but layover too long
		Flight startFlightFollowingButLayoverTooLong = null;
		// a flight time linking correctly with another itinerary but city cont
		Flight startFlightDiscontButTimeCont = null;
		// a flight can be added to the front of the itinerary
		Flight startFlightFollowingButTimeDiscontFront = null;
		// a flight can be added to the back of the itinerary
		Flight startFlightFollowingButLayoverTooLongBack = null;
		// a flight creating a link between two discontiuous itineraries that is valid
		Flight itineraryConnector = null;
		// a flight between two discont itinerarries that is cyclic
		Flight itineraryConnectorInvalid = null;
		// a flight linking correctly (no space time conflict) with another itinerary but cyclical
		Flight startFlightFollowingButLayoverTooLongCyclic = null;
		// a flight city discont with every itinerary but time layover cont
		Flight lonerFlight = null;


		try {
		startFlight = new Flight("--", 616l, "R", "S", dateTimeFormatter.parse("2015-08-19 18:00"),
				dateTimeFormatter.parse("2015-08-19 23:00"), 700.0);
		startFlightFollowingButTimeDiscont = new Flight("XX", 616l, "S", "T", dateTimeFormatter.parse("2015-08-19 21:00"),
				dateTimeFormatter.parse("2015-08-20 5:00"), 300.0);
		startFlightFollowingButLayoverTooLong = new Flight("<<", 1056l, "S", "W", dateTimeFormatter.parse("2015-08-20 8:00"),
				dateTimeFormatter.parse("2015-08-20 15:00"), 1400.0);
		startFlightDiscontButTimeCont = new Flight("&<", 1088l, "E", "F", dateTimeFormatter.parse("2015-08-19 23:30"),
				dateTimeFormatter.parse("2015-08-20 2:00"), 150.0);
		startFlightFollowingButTimeDiscontFront = new Flight("*(", 1988l, "I", "S", dateTimeFormatter.parse("2015-08-19 12:00"),
				dateTimeFormatter.parse("2015-08-19 20:00"), 450.0);
		startFlightFollowingButLayoverTooLongBack = new Flight("^(", 1988l, "W", "X", dateTimeFormatter.parse("2015-08-20 16:00"),
				dateTimeFormatter.parse("2015-08-20 20:00"), 1800.0);
		itineraryConnector = new Flight(":)", 1337l, "S", "E", dateTimeFormatter.parse("2015-08-19 22:00"),
				dateTimeFormatter.parse("2015-08-19 23:30"), 1800.0);
		itineraryConnectorInvalid = new Flight(":(", 167l, "T", "S", dateTimeFormatter.parse("2015-08-20 7:00"),
				dateTimeFormatter.parse("2015-08-20 7:30"), 1870.0);
		startFlightFollowingButLayoverTooLongCyclic = new Flight(":)", 167l, "X", "S", dateTimeFormatter.parse("2015-08-20 21:00"),
				dateTimeFormatter.parse("2015-08-21 1:00"), 180.0);
		lonerFlight = new Flight("*_*", 1789l, "Y", "Z", dateTimeFormatter.parse("2015-08-22 23:00"),
				dateTimeFormatter.parse("2015-08-23 3:00"), 133.0);
		} catch (Exception e) {}
		
		ArrayList<Flight> toAddFlights = new ArrayList<>();
		toAddFlights.add(startFlight);
		toAddFlights.add(startFlightFollowingButLayoverTooLong);
		toAddFlights.add(lonerFlight);
		toAddFlights.add(startFlightDiscontButTimeCont);
		toAddFlights.add(itineraryConnector);
		toAddFlights.add(startFlightFollowingButLayoverTooLongBack);
		toAddFlights.add(startFlightFollowingButTimeDiscontFront);
		toAddFlights.add(itineraryConnectorInvalid);
		toAddFlights.add(startFlightFollowingButTimeDiscont);
		toAddFlights.add(startFlightFollowingButLayoverTooLongCyclic);
		
		//add the flights to the FM
		for (Flight f : toAddFlights) {
			flightManager.addFlight(f);
		}
		
		// now check if the correct Flights have been added to flights variable
		HashMap<String[], ArrayList<Flight>> correctFlights = new HashMap<>();

		for (Flight f: toAddFlights) {
			String[] key = {f.getOrigin(), f.getDestination(), dateFormatter.format(f.getDepartureDateTime())};
			correctFlights.putIfAbsent(key, new ArrayList<Flight>());
			correctFlights.get(key).add(f);
		}
		
		// check if the keys and values of flight contain the correctFlights keys and values
		assertEquals(flightManager.flights.size(), correctFlights.size());
		for (String[] k: flightManager.flights.keySet()) {
			System.out.println(k);
		}
		for (String[] k: correctFlights.keySet()) {
			assertTrue(flightManager.flights.containsKey(k));
			assertEquals(flightManager.flights.get(k), correctFlights.get(k));
		}


		// now check if the correct Itinerary have been added to itineraries variable
		// first add every single flight itinerary to the list of itineraries we know exist
		ArrayList<Itinerary> toCheckItineraries = new ArrayList<>();
		for (Flight f : toAddFlights) {
			TreeSet<Flight> t = new TreeSet<>();
			t.add(f);
			try {
			Itinerary it = new Itinerary(t);
			toCheckItineraries.add(it);
			} catch (Exception e) {}
		}
		
		// make the Itineraries with more than one Flight and Add them
		TreeSet<Flight> t1 = new TreeSet<>();
		TreeSet<Flight> t2 = new TreeSet<>();
		TreeSet<Flight> t3 = new TreeSet<>();
		TreeSet<Flight> t4 = new TreeSet<>();
		TreeSet<Flight> t5 = new TreeSet<>();
		TreeSet<Flight> t6 = new TreeSet<>();
		TreeSet<Flight> t7 = new TreeSet<>();
		TreeSet<Flight> t8 = new TreeSet<>();
		
		t1.add(itineraryConnectorInvalid);
		t1.add(startFlightFollowingButLayoverTooLong);
		t1.add(startFlightFollowingButLayoverTooLongBack);
		
		t2.add(startFlightFollowingButTimeDiscontFront); // Done
		t2.add(startFlightFollowingButTimeDiscont);
		
		t3.add(itineraryConnectorInvalid);		// Done
		t3.add(startFlightFollowingButLayoverTooLong);

		t4.add(startFlightFollowingButLayoverTooLong); // Done
		t4.add(startFlightFollowingButLayoverTooLongBack);
		
		t5.add(startFlightFollowingButTimeDiscontFront); // Done
		t5.add(itineraryConnector);
		t5.add(startFlightDiscontButTimeCont);
		
		t6.add(startFlightFollowingButTimeDiscontFront); // Done
		t6.add(itineraryConnector);

		t7.add(itineraryConnector); // Done
		t7.add(startFlightDiscontButTimeCont);

		t8.add(startFlightFollowingButLayoverTooLongBack);
		t8.add(startFlightFollowingButLayoverTooLongCyclic);	// Done

		Itinerary it1 = new Itinerary(t1);
		Itinerary it2 = new Itinerary(t2);
		Itinerary it3 = new Itinerary(t3);
		Itinerary it4 = new Itinerary(t4);
		Itinerary it5 = new Itinerary(t5);
		Itinerary it6 = new Itinerary(t6);
		Itinerary it7 = new Itinerary(t7);
		Itinerary it8 = new Itinerary(t8);

		toCheckItineraries.add(it1);
		toCheckItineraries.add(it2);
		toCheckItineraries.add(it3);
		toCheckItineraries.add(it4);
		toCheckItineraries.add(it5);
		toCheckItineraries.add(it6);
		toCheckItineraries.add(it7);
		toCheckItineraries.add(it8);
		HashMap<String[], ArrayList<Itinerary>> correctItineraries = new HashMap<>();

		for (Itinerary i: toCheckItineraries) {
			String[] key = {i.getOrigin(), i.getDestination(), dateFormatter.format(i.getDepartureDateTime())};
			correctItineraries.putIfAbsent(key, new ArrayList<Itinerary>());
			correctItineraries.get(key).add(i);
		}

		assertEquals(flightManager.itineraries, correctItineraries);
	} */

	@Test
	public void doesThisCrapWork() {
		Flight f = null;
		Map<ArrayList<String>, ArrayList<Flight>> m = new HashMap<>();
		try {
		f = new Flight("--", 616l, "R", "S", dateTimeFormatter.parse("2015-08-19 18:00"),
				dateTimeFormatter.parse("2015-08-19 23:00"), 700.0, 100);
		} catch (InvalidFlightException e) {} catch (ParseException e) {}

		ArrayList<String> k = new ArrayList<>();
		k.add(f.getOrigin());
		k.add(f.getDestination());
		k.add(dateFormatter.format(f.getDepartureDateTime()));

		m.putIfAbsent(k, new ArrayList<Flight>());
		m.get(k).add(f);

		ArrayList<String> key = new ArrayList<>();
		key.add(f.getOrigin());
		key.add(f.getDestination());
		key.add(dateFormatter.format(f.getDepartureDateTime()));
		assertTrue(m.containsKey(k));
		assertTrue(m.containsKey(key));
		assertNotEquals(m.getOrDefault(key, null), null);
		assertEquals(m.get(k).get(0), f);
	}

	@Test
	public void addFlightShouldCreateValidItineraryListSimple() throws InvalidFlightException, InvalidItineraryException {
		// This method should be run by itself first without any other tests running

		// time conflict, city discontinuity, layover too long, cyclical

		// first add an starting flight
		Flight startFlight = null;
		// a flight linking correctly with another itinerary but layover too long
		Flight startFlightFollowingButLayoverTooLong = null;
		// a flight can be added to the back of the itinerary
		Flight startFlightFollowingButLayoverTooLongBack = null;

		try {
		startFlight = new Flight("--", 616l, "R", "S", dateTimeFormatter.parse("2015-08-19 18:00"),
				dateTimeFormatter.parse("2015-08-19 23:00"), 700.0, 100);
		startFlightFollowingButLayoverTooLong = new Flight("<<", 1056l, "S", "W", dateTimeFormatter.parse("2015-08-20 8:00"),
				dateTimeFormatter.parse("2015-08-20 15:00"), 1400.0, 100);
		startFlightFollowingButLayoverTooLongBack = new Flight(":^(", 1988l, "W", "X", dateTimeFormatter.parse("2015-08-20 16:00"),
				dateTimeFormatter.parse("2015-08-20 20:00"), 1800.0, 100);
		} catch (Exception e) {}
		
		ArrayList<Flight> toAddFlights = new ArrayList<>();
		toAddFlights.add(startFlight);
		toAddFlights.add(startFlightFollowingButLayoverTooLong);
		toAddFlights.add(startFlightFollowingButLayoverTooLongBack);
		
		//add the flights to the FM
		for (Flight f : toAddFlights) {
			flightManager.addFlight(f);
		}
		
		// now check if the correct Flights have been added to flights variable
		Map<String[], ArrayList<Flight>> correctFlights = new HashMap<>();

		for (Flight f: toAddFlights) {
			String[] key = {f.getOrigin(), f.getDestination(), dateFormatter.format(f.getDepartureDateTime())};
			correctFlights.putIfAbsent(key, new ArrayList<Flight>());
			correctFlights.get(key).add(f);
		}
		
		// check if the keys and values of flight contain the correctFlights keys and values
		assertEquals(flightManager.flights.size(), correctFlights.size());
		for (String[] k: flightManager.flights.keySet()) {
			System.out.println(Arrays.toString(k));
		}
		
		for (String[] k: correctFlights.keySet()) {
			System.out.println(Arrays.toString(k));
			assertNotEquals(flightManager.flights.getOrDefault(k, null), null);
			assertEquals(flightManager.flights.get(k), correctFlights.get(k));
		}


		// now check if the correct Itinerary have been added to itineraries variable
		// first add every single flight itinerary to the list of itineraries we know exist
		ArrayList<Itinerary> toCheckItineraries = new ArrayList<>();
		for (Flight f : toAddFlights) {
			TreeSet<Flight> t = new TreeSet<>();
			t.add(f);
			try {
			Itinerary it = new Itinerary(t);
			toCheckItineraries.add(it);
			} catch (Exception e) {}
		}
		
		// make the Itineraries with more than one Flight and Add them
		TreeSet<Flight> t1 = new TreeSet<>();
		
		t1.add(startFlightFollowingButLayoverTooLong);
		t1.add(startFlightFollowingButLayoverTooLongBack);
		
		Itinerary it1 = new Itinerary(t1);
		toCheckItineraries.add(it1);

		Map<String[], ArrayList<Itinerary>> correctItineraries = new HashMap<>();

		for (Itinerary i: toCheckItineraries) {
			String[] key = {i.getOrigin(), i.getDestination(), dateFormatter.format(i.getDepartureDateTime())};
			correctItineraries.putIfAbsent(key, new ArrayList<Itinerary>());
			correctItineraries.get(key).add(i);
		}

		assertEquals(flightManager.itineraries.size(), correctItineraries.size());
		for (String[] k :correctItineraries.keySet()) {
			assertTrue(flightManager.itineraries.containsKey(k));
			assertEquals(flightManager.itineraries.get(k), correctItineraries.get(k));
		}
	}
	/*
	@Test
	public void getItinerariesShouldReturnCorrectItineraries() {
		// should all have the same departure date
	}

	@Test
	public void getItinerariesShouldReturnEmptyItinerariesIfNoItineraryExists() {
	}

	@Test
	public void getFlightsShouldReturnCorrectFlights() {
		// should all have the same departure date
	}

	@Test
	public void getFlightsShouldReturnEmptyFlightsIfNoFlightsExists() {
	}
	
	@Test
	public void sortByPriceShouldSortCorrectly() {
		// sortByPrice Aliases
	}

	@Test
	public void sortByDurationShouldSortCorrectly() {
		// sortByDuration Aliases
	}
	*/
}
