package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.TreeSet;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Random;

import backend.Itinerary;
import backend.Flight;
import backend.InvalidFlightException;
import backend.InvalidItineraryException;

public class ItineraryTest {
	private Itinerary goodItinerary;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private Random rand = new Random();

	@Before
	public void setUp() throws InvalidItineraryException, InvalidFlightException {
		TreeSet<Flight> f = new TreeSet<>();
		try {
		f.add(new Flight("ABCDE", rand.nextLong(), "Shanghai", "London", formatter.parse("2015-08-19 10:00"),
				formatter.parse("2015-08-19 17:00"), 1500.00d));
		f.add(new Flight("China Eastern", rand.nextLong(), "Qatar", "Tehran", formatter.parse("2015-08-18 7:00"),
				formatter.parse("2015-08-18 18:00"), 2100.00d));
		f.add(new Flight("Emirates", rand.nextLong(), "Tehran", "Shanghai", formatter.parse("2015-08-18 23:00"),
				formatter.parse("2015-08-19 6:00"), 100.00d));
		} catch (ParseException e) {}
		
		goodItinerary = new Itinerary(f);
	}

	@Test(expected=InvalidFlightException.class) 
	public void ItineraryClassShouldThrowErrorWithCycle() throws InvalidItineraryException, InvalidFlightException{
		TreeSet<Flight> a = new TreeSet<>();
		try {
			a.add(new Flight("", 0l, "A", "B", formatter.parse("2015-08-19 12:00"), formatter.parse("2015-08-19 13:00"), 0.0d));
			a.add(new Flight("", 0l, "B", "A", formatter.parse("2015-08-19 14:00"), formatter.parse("2015-08-19 15:00"), 0.0d));
		} catch (ParseException e) {}
		new Itinerary(a);
	}
		
	@Test(expected=InvalidFlightException.class) 
	public void ItineraryClassShouldThrowErrorWithWrongTiming() throws InvalidItineraryException, InvalidFlightException{
		TreeSet<Flight> a = new TreeSet<>();
		try {
			a.add(new Flight("", 0l, "A", "B", formatter.parse("2015-08-19 12:00"), formatter.parse("2015-08-19 13:00"), 0.0d));
			a.add(new Flight("", 0l, "B", "C", formatter.parse("2015-08-19 1:00"), formatter.parse("2015-08-19 3:00"), 0.0d));
		} catch (ParseException e) {}
		new Itinerary(a);
	}
	
	@Test
	public void getAirlineShouldReturnCorrectValue() {
		assertTrue(goodItinerary.getAirline() == "China Eastern");
	}
	
	@Test
	public void getOriginShouldReturnCorrectValue() {
		assertTrue(goodItinerary.getOrigin() == "Qatar");
	}

	@Test
	public void getDestinationShouldReturnCorrectValue() {
		assertTrue(goodItinerary.getDestination() == "London");
	}

	@Test
	public void getDepartureDateTimeShouldReturnCorrectValue() {
		try {
			assertEquals(goodItinerary.getDepartureDateTime(), formatter.parse("2015-08-18 7:00"));
		} catch (ParseException e) {}
	}

	@Test
	public void getArrivalDateTimeShouldReturnCorrectValue() {
		try {
			assertEquals(goodItinerary.getArrivalDateTime(), formatter.parse("2015-08-19 17:00"));
		} catch (ParseException e) {}
	}

	@Test
	public void getPriceShouldReturnCorrectValue() {
		assertTrue(goodItinerary.getPrice() == (double) 3700.00);
	}
	
	@Test(expected=InvalidItineraryException.class)
	public void ItineraryConstructorShouldErrorWhenCyclic() throws InvalidItineraryException, InvalidFlightException {
		TreeSet<Flight> cyclicTS = new TreeSet<>();
		try {
			cyclicTS.add(new Flight("", 0l, "A", "B", formatter.parse("2015-08-19 12:00"), formatter.parse("2015-08-19 13:00"), 0.0d));
			cyclicTS.add(new Flight("", 0l, "B", "C", formatter.parse("2015-08-19 14:00"), formatter.parse("2015-08-19 15:00"), 0.0d));
			cyclicTS.add(new Flight("", 0l, "C", "A", formatter.parse("2015-08-19 16:00"), formatter.parse("2015-08-19 17:00"), 0.0d));
			cyclicTS.add(new Flight("", 0l, "A", "E", formatter.parse("2015-08-19 18:00"), formatter.parse("2015-08-19 19:00"), 0.0d));
		} catch (ParseException e) {}
		new Itinerary(cyclicTS);
		
	}
	
	@Test(expected=InvalidItineraryException.class)
	public void ItineraryConstructorShouldErrorWhenDiscontinuous() throws InvalidFlightException, InvalidItineraryException {
		TreeSet<Flight> discontTS = new TreeSet<>();
		try {
			discontTS.add(new Flight("", 0l, "A", "B", formatter.parse("2015-08-19 12:00"), formatter.parse("2015-08-19 13:00"), 0.0d));
			discontTS.add(new Flight("", 0l, "B", "C", formatter.parse("2015-08-19 14:00"), formatter.parse("2015-08-19 15:00"), 0.0d));
			discontTS.add(new Flight("", 0l, "D", "E", formatter.parse("2015-08-19 16:00"), formatter.parse("2015-08-19 17:00"), 0.0d));
			discontTS.add(new Flight("", 0l, "E", "F", formatter.parse("2015-08-19 18:00"), formatter.parse("2015-08-19 19:00"), 0.0d));
		} catch (ParseException e) {}
		new Itinerary(discontTS);
	}
	
	@Test(expected=InvalidItineraryException.class)
	public void ItineraryConstructorShouldErrorWhenLayoverTooLong() throws InvalidFlightException, InvalidItineraryException {
		TreeSet<Flight> longlayoverTS = new TreeSet<>();
		try {
			longlayoverTS.add(new Flight("", 0l, "A", "B", formatter.parse("2015-08-19 5:59"), formatter.parse("2015-08-19 7:59"), 0.0d));
			longlayoverTS.add(new Flight("", 0l, "B", "C", formatter.parse("2015-08-19 14:00"), formatter.parse("2015-08-19 15:00"), 0.0d));
			longlayoverTS.add(new Flight("", 0l, "C", "D", formatter.parse("2015-08-19 16:00"), formatter.parse("2015-08-19 17:00"), 0.0d));
			longlayoverTS.add(new Flight("", 0l, "D", "E", formatter.parse("2015-08-19 18:00"), formatter.parse("2015-08-19 19:00"), 0.0d));
		} catch (ParseException e) {}
		new Itinerary(longlayoverTS);
	}
	
	@Test
	public void getFlightShouldReturnTreeSetOfCorrectOrdering() throws InvalidFlightException {
		TreeSet<Flight> t = new TreeSet<>();
		try {
		t.add(new Flight("China Eastern", rand.nextLong(), "Qatar", "Tehran", formatter.parse("2015-08-18 7:00"),
				formatter.parse("2015-08-18 18:00"), 2100.00d));
		t.add(new Flight("Emirates", rand.nextLong(), "Tehran", "Shanghai", formatter.parse("2015-08-18 23:00"),
				formatter.parse("2015-08-19 6:00"), 100.00d));
		t.add(new Flight("ABCDE", rand.nextLong(), "Shanghai", "London", formatter.parse("2015-08-19 10:00"),
				formatter.parse("2015-08-19 17:00"), 1500.00d));
		} catch (ParseException e) {}
		assertEquals(goodItinerary.getFlights(), t);
	}

	@Test
	public void isValidShouldReturnTrueWithCorrectItinerary() {
		assertTrue(goodItinerary.isValid());
	}

	@Test(expected=InvalidItineraryException.class)
	public void ConstructorShouldThrowExceptionForDiscontinousItinerary() throws InvalidFlightException, InvalidItineraryException {
		TreeSet<Flight> discontTS = new TreeSet<>();
		try {
			discontTS.add(new Flight("", 0l, "A", "B", formatter.parse("2015-08-19 7:00"), formatter.parse("2015-08-19 13:00"), 0.0d));
			discontTS.add(new Flight("", 0l, "B", "C", formatter.parse("2015-08-19 12:00"), formatter.parse("2015-08-19 15:00"), 0.0d));
			discontTS.add(new Flight("", 0l, "C", "D", formatter.parse("2015-08-19 16:00"), formatter.parse("2015-08-19 17:00"), 0.0d));
			discontTS.add(new Flight("", 0l, "D", "E", formatter.parse("2015-08-19 18:00"), formatter.parse("2015-08-19 19:00"), 0.0d));
		} catch (ParseException e) {}
		new Itinerary(discontTS);
	}
	
	@Test
	public void isValidShouldReturnTrueWithLayOverOfSixHours() throws InvalidItineraryException, InvalidFlightException {
		TreeSet<Flight> correctLayOverTS = new TreeSet<>();
		try {
			correctLayOverTS.add(new Flight("", 0l, "A", "B", formatter.parse("2015-08-19 5:00"), formatter.parse("2015-08-19 06:00"), 0.0d));
			correctLayOverTS.add(new Flight("", 0l, "B", "C", formatter.parse("2015-08-19 12:00"), formatter.parse("2015-08-19 13:00"), 0.0d));
			correctLayOverTS.add(new Flight("", 0l, "C", "D", formatter.parse("2015-08-19 19:00"), formatter.parse("2015-08-19 20:00"), 0.0d));
			correctLayOverTS.add(new Flight("", 0l, "D", "E", formatter.parse("2015-08-20 2:00"), formatter.parse("2015-08-20 3:00"), 0.0d));
		} catch (ParseException e) {}
		Itinerary correctLayover = new Itinerary(correctLayOverTS);
		assertTrue(correctLayover.isValid());
	}

	@Test
	public void addFlightShouldReturnNewUnaliasedItinerary() throws InvalidFlightException, InvalidItineraryException {
		Itinerary newItinerary = null;
		try {
			newItinerary = goodItinerary.addFlight(new Flight("BA", rand.nextLong(), "London", "Barcelona", formatter.parse("2015-08-19 19:00"),
					formatter.parse("2015-08-19 23:00"), 300.00d));
		} catch (ParseException e) {}
		assertNotEquals(newItinerary.getFlights(), goodItinerary.getFlights());
		assertNotEquals(newItinerary, goodItinerary);

		try {
			newItinerary.addFlight(new Flight("SA", rand.nextLong(), "Barcelona", "Frankfurt", formatter.parse("2015-08-20 00:00"),
					formatter.parse("2015-08-20 5:00"), 600.00d));
		} catch (ParseException e) {}
		assertNotEquals(newItinerary.getFlights(), goodItinerary.getFlights());
		assertNotEquals(newItinerary, goodItinerary);
	}
}
