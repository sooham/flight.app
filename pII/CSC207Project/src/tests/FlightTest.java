package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import backend.Flight;
import backend.InvalidFlightException;

public class FlightTest {

	private Flight testFlight;


	@Before
	public void setUp() throws InvalidFlightException{
	testFlight = new Flight("American Airlines", 123l, "A",
	"B", new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 11, 12, 30), 1500.0, 300);
	}

	@Test(expected=InvalidFlightException.class) 
	public void flightClassShouldThrowErrorWithCycle() throws InvalidFlightException{
	new Flight("Emirates", 516l, "W",
	"W", new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 11, 12, 30), 0.0, 500);
	}
		
	@Test(expected=InvalidFlightException.class) 
	public void flightClassShouldThrowErrorWithWrongTiming() throws InvalidFlightException{
	new Flight("Emirates", 516l, "W",
	"X", new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 10, 6, 30), 0.0, 500);
	}
	
	@Test(expected=InvalidFlightException.class) 
	public void flightClassShouldThrowErrorWithNegativePrice() throws InvalidFlightException{
	new Flight("Emirates", 516l, "W",
	"X", new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 10, 8, 30), -10.0, 500);
	}

	@Test(expected=InvalidFlightException.class) 
	public void flightClassShouldThrowErrorWithNegativeSeats() throws InvalidFlightException{
	new Flight("Emirates", 516l, "W",
	"X", new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 10, 8, 30), 30.0, -200);
	}

	@Test
	public void getAirlineShouldReturnCorrectValue() {
		assertEquals(testFlight.getAirline(), "American Airlines");
	}

	@Test
	public void setAirlineShouldChangeValue() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setAirline("China Eastern");
		assertEquals(f.getAirline(), "China Eastern");
	}

	@Test
	public void getNumberShouldReturnCorrectValue() {
		assertEquals(testFlight.getNumber(), 123l);
	}
	
	@Test
	public void setNumberShouldChangeValue() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setNumber(513l);
		assertEquals(f.getNumber(), 513l);
	}

	@Test
	public void getOriginShouldReturnCorrectValue() {
		assertEquals(testFlight.getOrigin(), "A");
	}

	@Test
	public void setOriginShouldChangeValue() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setOrigin("R");
		assertEquals(f.getOrigin(), "R");
	}

	@Test(expected=InvalidFlightException.class)
	public void setOriginShouldThrowErrorIfCycle() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setOrigin("T");
	}

	@Test
	public void getDestinationShouldReturnCorrectValue() {
		assertEquals(testFlight.getDestination(), "B");
	}

	@Test
	public void setDestinationShouldChangeValue() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setDestination("U");
		assertEquals(f.getDestination(), "U");
	}

	@Test(expected=InvalidFlightException.class)
	public void setDestinationShouldThrowErrorIfCycle() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setDestination("S");
	}

	@Test
	public void getDepartureDateTimeShouldReturnCorrectValue() {
		assertEquals(testFlight.getDepartureDateTime(), new Date(2015, 11, 10, 7, 30));
	}
	
	@Test
	public void setDepartureDateTimeShouldChangeValue() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setDepartureDateTime(new Date(2016, 8, 9, 02, 15));
		assertEquals(f.getDepartureDateTime(), new Date(2016, 8, 9, 02, 15));
	}

	@Test(expected=InvalidFlightException.class)
	public void setDepartureDateTimeShouldThrowErrorIfNegativeTime() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setDepartureDateTime(new Date(2016, 9, 1, 10, 30));
	}

	@Test
	public void getArrivalDateTimeShouldReturnCorrectValue() {
		assertEquals(testFlight.getArrivalDateTime(), new Date(2015, 11, 11, 12, 30));
	}

	@Test
	public void setArrivalDateTimeShouldChangeValue() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setArrivalDateTime(new Date(2016, 8, 19, 02, 15));
		assertEquals(f.getArrivalDateTime(), new Date(2016, 8, 19, 02, 15));
	}

	@Test(expected=InvalidFlightException.class)
	public void setArrivalDateTimeShouldThrowErrorIfNegativeTime() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setArrivalDateTime(new Date(2015, 12, 10, 00, 30));
	}

	@Test
	public void getPriceShouldReturnCorrectValue() {
		assertTrue(testFlight.getPrice() == (double) 1500.00);
	}

	@Test
	public void setPriceShouldChangePriceIfCorrect() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setPrice(250.12);
		assertTrue(f.getPrice() == 250.12);
	}
	
	@Test
	public void setPriceShouldNotChangeIfIncorrect() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setPrice(-23.0);
		assertTrue(f.getPrice() == 100.0);
	}

	@Test
	public void getNumSeatsShouldReturnCorrectValue() {
		assertEquals(testFlight.getNumSeats(), 300);
	}

	@Test
	public void setNumSeats() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 50);
		f.setNumSeats(70);
		assertEquals(f.getNumSeats(), 70);
		assertEquals(f.getNumEmptySeats(), 70);
		f.setNumSeats(-1);
		assertEquals(f.getNumSeats(), 70);
		assertEquals(f.getNumEmptySeats(), 70);
		f.setNumSeats(10);
		assertEquals(f.getNumSeats(), 10);
		assertEquals(f.getNumEmptySeats(), 10);
		f.setNumEmptySeats(-1);
		f.setNumEmptySeats(6);
		f.setNumEmptySeats(12);
		assertEquals(f.getNumSeats(), 10);
		assertEquals(f.getNumEmptySeats(), 6);
		f.setNumSeats(6);
		assertEquals(f.getNumSeats(), 6);
		assertEquals(f.getNumEmptySeats(), 2);
		f.setNumSeats(30);
		assertEquals(f.getNumSeats(), 30);
		assertEquals(f.getNumEmptySeats(), 26);
	}
	
	@Test
	public void getNumEmptySeatsReturnsCorrectValue() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 70);
		assertTrue(!f.isFull());
		assertEquals(f.getNumSeats(), 70);
		assertEquals(f.getNumEmptySeats(), 70);
		f.bookSeat();
		assertTrue(!f.isFull());
		assertEquals(f.getNumSeats(), 70);
		assertEquals(f.getNumEmptySeats(), 69);
		f.setNumSeats(2);
		assertTrue(!f.isFull());
		assertEquals(f.getNumSeats(), 2);
		assertEquals(f.getNumEmptySeats(), 1);
		f.bookSeat();
		assertTrue(f.isFull());
		assertEquals(f.getNumSeats(), 2);
		assertEquals(f.getNumEmptySeats(), 0);
		f.bookSeat();
		assertTrue(f.isFull());
		assertEquals(f.getNumSeats(), 2);
		assertEquals(f.getNumEmptySeats(), 0);
	}

	@Test
	public void bookSeatBooksASingleSeatWhenFlightNotFull() throws InvalidFlightException {
		Flight f = new Flight("Emirates", 303l, "S", "T", new Date(2016, 8, 19, 01, 00), 
				new Date(2016, 8, 19, 03, 00), 100.0, 70);
		assertTrue(!f.isFull());
		assertEquals(f.getNumSeats(), 70);
		assertEquals(f.getNumEmptySeats(), 70);
		f.bookSeat();
		assertTrue(!f.isFull());
		assertEquals(f.getNumSeats(), 70);
		assertEquals(f.getNumEmptySeats(), 69);
	}

	public void comparingFlightsShouldReturnCorrectValue() throws InvalidFlightException {
		Date early = new Date(2014, 12, 8, 0, 0);
		Date late = new Date(2016, 1, 19, 0, 50);
		Date same = testFlight.getDepartureDateTime();

		Flight earlyFlight = new Flight("Emirates", 69l, "B", "C", early, early, 10000.0, 100);
		Flight lateFlight = new Flight("China Eastern", 34l, "M", "O", late, late, 0.0, 200);
		Flight sameFlight = new Flight("Delta", 2000l, "Q", "Z", same, late, 0.0, 300);

		assertEquals(testFlight.compareTo(earlyFlight), testFlight.getDepartureDateTime().compareTo(early));
		assertEquals(testFlight.compareTo(lateFlight), testFlight.getDepartureDateTime().compareTo(late));
		assertEquals(testFlight.compareTo(sameFlight), testFlight.getDepartureDateTime().compareTo(same));
	}

	public void getDurationShouldReturnCorrectValue() throws InvalidFlightException {
		Date early = new Date(2014, 12, 8, 0, 0);
		Date late = new Date(2015, 11, 20, 7, 30);
		Date same = testFlight.getDepartureDateTime();

		Flight earlyFlight = new Flight("Emirates", 69l, "B", "C", early, early, 10000.0, 100);
		Flight lateFlight = new Flight("China Eastern", 34l, "M", "O", late, late, 0.0, 200);
		Flight sameFlight = new Flight("Delta", 2000l, "Q", "Z", same, late, 0.0, 300);

		assertTrue(earlyFlight.getDuration() == 0l);
		assertTrue(lateFlight.getDuration() == 0l);
		assertTrue(sameFlight.getDuration() == 14400l);
	}
}
