package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.Date;
import backend.Flight;
import backend.InvalidFlightException;

public class FlightTest {

	private Random rand = new Random();
	private static String[] airlines = {
			"AA", "Air India", "Quanta", "United", "Emirates Airlines",
			"Cathay Pacific", "Long ass airline name", ""
	};
	private int randStringIndex;
	private long randFlightNum;
	private Flight testFlight;


	@Before
	public void setUp() throws InvalidFlightException{
	
	randFlightNum = rand.nextLong();
	randStringIndex = rand.nextInt(airlines.length);

	testFlight = new Flight(airlines[randStringIndex], randFlightNum, "Paris",
	"NY", new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 11, 12, 30), 1500.0, 300);
	}

	@Test(expected=InvalidFlightException.class) 
	public void flightClassShouldThrowErrorWithCycle() throws InvalidFlightException{
	new Flight("", randFlightNum, "ABCDE",
	"ABCDE", new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 11, 12, 30), 0.0, 500);
	}
		
	@Test(expected=InvalidFlightException.class) 
	public void flightClassShouldThrowErrorWithWrongTiming() throws InvalidFlightException{
	new Flight("", randFlightNum, "",
	"A", new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 10, 6, 30), 0.0, 600);
	}
	
	@Test
	public void getAirlineShouldReturnCorrectValue() {
		assertTrue(testFlight.getAirline() == airlines[randStringIndex]);
	}

	@Test
	public void getNumberShouldReturnCorrectValue() {
		assertTrue(testFlight.getNumber() == randFlightNum);
	}
	
	@Test
	public void getOriginShouldReturnCorrectValue() {
		assertTrue(testFlight.getOrigin() == "Paris");
	}

	@Test
	public void getDestinationShouldReturnCorrectValue() {
		assertTrue(testFlight.getDestination() == "NY");
	}

	@Test
	public void getDepartureDateTimeShouldReturnCorrectValue() {
		assertEquals(testFlight.getDepartureDateTime(), new Date(2015, 11, 10, 7, 30));
	}

	@Test
	public void getArrivalDateTimeShouldReturnCorrectValue() {
		assertEquals(testFlight.getArrivalDateTime(), new Date(2015, 11, 11, 12, 30));
	}

	@Test
	public void getPriceShouldReturnCorrectValue() {
		assertTrue(testFlight.getPrice() == (double) 1500.00);
	}

	@Test
	public void setPriceShouldChangePrice() {
		testFlight.setPrice(1337.99);
		assertTrue(testFlight.getPrice() == (double) 1337.99);
	}
	
	@Test
	public void getNumSeatsShouldReturnCorrectValue() {
		assertTrue(testFlight.getNumSeats() == 300);
	}

	public void comparingFlightsShouldReturnCorrectValue() throws InvalidFlightException {
		Date early = new Date(2014, 12, 8, 0, 0);
		Date late = new Date(2016, 1, 19, 0, 50);
		Date same = testFlight.getDepartureDateTime();

		Flight earlyFlight = new Flight("", 1001, "NY", "Abu Dhabi", early, early, 10000.0, 100);
		Flight lateFlight = new Flight("", 34, "Jersey", "Shanghai", late, late, 0.0, 200);
		Flight sameFlight = new Flight("", 2000, "Apple", "Toronto", same, late, 0.0, 300);

		assertTrue(testFlight.compareTo(earlyFlight) ==  testFlight.getDepartureDateTime().compareTo(early));
		assertTrue(testFlight.compareTo(lateFlight) == testFlight.getDepartureDateTime().compareTo(late));
		assertTrue(testFlight.compareTo(sameFlight) == testFlight.getDepartureDateTime().compareTo(same));
	}

	public void getDurationShouldReturnCorrectValue() throws InvalidFlightException {
		Date early = new Date(2014, 12, 8, 0, 0);
		Date late = new Date(2015, 11, 20, 7, 30);
		Date same = testFlight.getDepartureDateTime();

		Flight earlyFlight = new Flight("", 1001, "NY", "Abu Dhabi", early, early, 10000.0, 100);
		Flight lateFlight = new Flight("", 34, "Jersey", "Shanghai", late, late, 0.0, 200);
		Flight sameFlight = new Flight("", 2000, "Apple", "Toronto", same, late, 0.0, 300);

		assertTrue(earlyFlight.getDuration() == 0l);
		assertTrue(lateFlight.getDuration() == 0l);
		assertTrue(sameFlight.getDuration() == 14400l);
	}
}
