package csc207.flightapp;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import backend.Itinerary;
import backend.Flight;
import backend.InvalidFlightException;
import backend.InvalidItineraryException;

public class ItineraryTest {
    private static Itinerary testItinerary;
    private static SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    @BeforeClass
    public static void setUpBeforeClass() throws InvalidItineraryException,
            InvalidFlightException {
        TreeSet<Flight> f = new TreeSet<>();
        try {
            f.add(new Flight("AA", 401l, "C", "D",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 17:00"),
                            1500.00d, 100)
            );
            f.add(new Flight("CE", 42l, "A", "B",
                            formatter.parse("2015-08-18 7:00"),
                            formatter.parse("2015-08-18 18:00"),
                            2100.00d, 50)
            );
            f.add(new Flight("EM", 706l, "B", "C",
                            formatter.parse("2015-08-18 23:00"),
                            formatter.parse("2015-08-19 6:00"),
                            100.00d, 300)
            );
        } catch (ParseException e) {}
        testItinerary = new Itinerary(f);
    }

    // constructor
    @Test(expected=InvalidItineraryException.class)
    public void ItineraryConstructorShouldThrowErrorWhenEmpty() throws
            InvalidItineraryException, InvalidFlightException {
        TreeSet<Flight> f = new TreeSet<>();
        new Itinerary(f);
    }

    @Test(expected=InvalidItineraryException.class)
    public void ItineraryConstructorShouldThrowErrorWithCycle() throws
            InvalidItineraryException, InvalidFlightException {
        TreeSet<Flight> f = new TreeSet<>();
        try {
            f.add(new Flight("", 12l, "A", "B",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 13:00"),
                            0.0d, 100)
            );
            f.add(new Flight("", 34l, "B", "A",
                            formatter.parse("2015-08-19 14:00"),
                            formatter.parse("2015-08-19 15:00"),
                            0.0d, 100)
            );
        } catch (ParseException e) {}
        new Itinerary(f);
    }

    @Test(expected=InvalidItineraryException.class)
    public void ItineraryConstructorShouldErrorWhenCyclic() throws
            InvalidItineraryException, InvalidFlightException {
        TreeSet<Flight> cyclicTS = new TreeSet<>();
        try {
            cyclicTS.add(new Flight("", 12l, "A", "B",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 13:00"),
                            0.0d, 100)
            );
            cyclicTS.add(new Flight("", 0l, "B", "C",
                            formatter.parse("2015-08-19 14:00"),
                            formatter.parse("2015-08-19 15:00"),
                            0.0d, 200)
            );
            cyclicTS.add(new Flight("", 0l, "C", "A",
                            formatter.parse("2015-08-19 16:00"),
                            formatter.parse("2015-08-19 17:00"),
                            0.0d, 300)
            );
            cyclicTS.add(new Flight("", 0l, "A", "E",
                            formatter.parse("2015-08-19 18:00"),
                            formatter.parse("2015-08-19 19:00"),
                            0.0d, 200)
            );
        } catch (ParseException e) {}
        new Itinerary(cyclicTS);
    }

    @Test(expected=InvalidItineraryException.class)
    public void ItineraryConstructorShouldErrorWhenLayoverTooLong()
            throws InvalidFlightException, InvalidItineraryException {
        TreeSet<Flight> longlayoverTS = new TreeSet<>();
        try {
            longlayoverTS.add(new Flight("", 12l, "A", "B",
                            formatter.parse("2015-08-19 5:59"),
                            formatter.parse("2015-08-19 7:59"),
                            0.0d, 100)
            );
            longlayoverTS.add(new Flight("", 14l, "B", "C",
                            formatter.parse("2015-08-19 14:00"),
                            formatter.parse("2015-08-19 15:00"),
                            0.0d, 100)
            );
            longlayoverTS.add(new Flight("", 17l, "C", "D",
                            formatter.parse("2015-08-19 16:00"),
                            formatter.parse("2015-08-19 17:00"),
                            0.0d, 100)
            );
            longlayoverTS.add(new Flight("", 20l, "D", "E",
                            formatter.parse("2015-08-19 18:00"),
                            formatter.parse("2015-08-19 19:00"),
                            0.0d, 100)
            );
        } catch (ParseException e) {}
        new Itinerary(longlayoverTS);
    }

    @Test(expected=InvalidItineraryException.class)
    public void ItineraryClassShouldThrowErrorWithWrongTiming() throws
            InvalidItineraryException, InvalidFlightException {
        TreeSet<Flight> f = new TreeSet<>();
        try {
            f.add(new Flight("", 13l, "A", "B",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 13:00"),
                            0.0d, 100)
            );
            f.add(new Flight("", 23l, "B", "C",
                            formatter.parse("2015-08-19 1:00"),
                            formatter.parse("2015-08-19 3:00"),
                            0.0d, 100)
            );
        } catch (ParseException e) {}
        new Itinerary(f);
    }

    @Test(expected=InvalidItineraryException.class)
    public void ItineraryClassShouldThrowErrorWithConflictingTiming() throws
            InvalidItineraryException, InvalidFlightException {
        TreeSet<Flight> f = new TreeSet<>();
        try {
            f.add(new Flight("", 13l, "A", "B",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 14:00"),
                            0.0d, 100)
            );
            f.add(new Flight("", 23l, "B", "C",
                            formatter.parse("2015-08-19 13:00"),
                            formatter.parse("2015-08-19 15:00"),
                            0.0d, 100)
            );
        } catch (ParseException e) {}
        new Itinerary(f);
    }

    @Test(expected=InvalidItineraryException.class)
    public void ItineraryClassShouldThrowErrorWithDiscontinuousPaths() throws
            InvalidItineraryException, InvalidFlightException {
        TreeSet<Flight> f = new TreeSet<>();
        try {
            f.add(new Flight("", 13l, "A", "B",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 14:00"),
                            0.0d, 100)
            );
            f.add(new Flight("", 23l, "C", "D",
                            formatter.parse("2015-08-19 15:00"),
                            formatter.parse("2015-08-19 16:00"),
                            0.0d, 100)
            );
        } catch (ParseException e) {}
        new Itinerary(f);
    }

    @Test(expected=InvalidItineraryException.class)
    public void ItineraryConstructorShouldErrorWhenDiscontinuous() throws
            InvalidFlightException, InvalidItineraryException {
        TreeSet<Flight> discontTS = new TreeSet<>();
        try {
            discontTS.add(new Flight("", 13l, "A", "B",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 13:00"),
                            0.0d, 100)
            );
            discontTS.add(new Flight("", 12l, "B", "C",
                            formatter.parse("2015-08-19 14:00"),
                            formatter.parse("2015-08-19 15:00"),
                            0.0d, 100)
            );
            discontTS.add(new Flight("", 89l, "D", "E",
                            formatter.parse("2015-08-19 16:00"),
                            formatter.parse("2015-08-19 17:00"),
                            0.0d, 100)
            );
            discontTS.add(new Flight("", 23l, "E", "F",
                            formatter.parse("2015-08-19 18:00"),
                            formatter.parse("2015-08-19 19:00"),
                            0.0d, 100)
            );
        } catch (ParseException e) {}
        new Itinerary(discontTS);
    }

    // getOrigin
    @Test
    public void getOriginShouldReturnCorrectValue() {
        assertEquals(testItinerary.getOrigin(), "A");
    }

    // getDestination
    @Test
    public void getDestinationShouldReturnCorrectValue() {
        assertEquals(testItinerary.getDestination(), "D");
    }

    // getDepartureDatetime
    @Test
    public void getDepartureDateTimeShouldReturnCorrectValue() {
        try {
            assertEquals(
                    testItinerary.getDepartureDateTime(),
                    formatter.parse("2015-08-18 7:00")
            );
        } catch (ParseException e) {}
    }

    // getArrivalDateTime
    @Test
    public void getArrivalDateTimeShouldReturnCorrectValue() {
        try {
            assertEquals(
                    testItinerary.getArrivalDateTime(),
                    formatter.parse("2015-08-19 17:00")
            );
        } catch (ParseException e) {}
    }

    // getPrice
    @Test
    public void getPriceShouldReturnCorrectValue() {
        assertTrue(testItinerary.getPrice() == 3700.00);
    }

    // getFlights
    @Test
    public void getFlightShouldReturnTreeSetOfCorrectOrdering()
            throws InvalidFlightException {
        ArrayList<Flight> f = new ArrayList<>();
        try {
            f.add(new Flight("CE", 42l, "X", "B",
                            formatter.parse("2015-08-18 7:00"),
                            formatter.parse("2015-08-18 18:00"),
                            2100.00d, 50)
            );
            f.add(new Flight("EM", 706l, "B", "C",
                            formatter.parse("2015-08-18 23:00"),
                            formatter.parse("2015-08-19 6:00"),
                            100.00d, 300)
            );
            f.add(new Flight("AA", 401l, "C", "Q",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 17:00"),
                            1500.00d, 100)
            );
        } catch (ParseException e) {}
        assertEquals(testItinerary.getFlights(), f);
    }

    // getDuration
    @Test
    public void getDurationShouldReturnCorrectTime() {
        assertEquals(testItinerary.getDuration(), 2040l);
    }

    // isValid
    @Test
    public void isValidShouldReturnTrueWithCorrectItinerary() {
        assertTrue(testItinerary.isValid());
    }

    @Test
    public void isValidShouldReturnTrueWithLayOverOfSixHours()
            throws InvalidItineraryException, InvalidFlightException {
        TreeSet<Flight> correctLayOverTS = new TreeSet<>();
        try {
            correctLayOverTS.add(new Flight("", 13l, "A", "B",
                            formatter.parse("2015-08-19 5:00"),
                            formatter.parse("2015-08-19 6:00"),
                            0.0d, 100)
            );
            correctLayOverTS.add(new Flight("", 89l, "B", "C",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 13:00"),
                            0.0d, 100)
            );
            correctLayOverTS.add(new Flight("", 32l, "C", "D",
                            formatter.parse("2015-08-19 19:00"),
                            formatter.parse("2015-08-19 20:00"),
                            0.0d, 100)
            );
            correctLayOverTS.add(new Flight("", 38l, "D", "E",
                            formatter.parse("2015-08-20 2:00"),
                            formatter.parse("2015-08-20 3:00"),
                            0.0d, 100)
            );
        } catch (ParseException e) {}
        Itinerary correctLayover = new Itinerary(correctLayOverTS);
        assertTrue(correctLayover.isValid());
    }

    // addFlight
    @Test
    public void addFlightShouldReturnNewUnaliasedItinerary()
            throws InvalidFlightException, InvalidItineraryException {
        Itinerary newItinerary = null;
        try {
            newItinerary = testItinerary.addFlight(
                    new Flight("BA", 38327l, "D", "E",
                            formatter.parse("2015-08-19 19:00"),
                            formatter.parse("2015-08-19 23:00"),
                            300.00d, 100)
            );
        } catch (ParseException e) {}
        assertNotSame(newItinerary.getFlights(), testItinerary.getFlights());
        assertNotSame(newItinerary, testItinerary);
    }

    // equals
    @Test
    public void equalsShouldReturnCorrectValue() throws
            InvalidItineraryException, InvalidFlightException {
        TreeSet<Flight> f = new TreeSet<>();
        TreeSet<Flight> g = new TreeSet<>();
        try {
            f.add(new Flight("AA", 401l, "E", "M",
                            formatter.parse("2015-08-19 13:00"),
                            formatter.parse("2015-08-20 18:00"),
                            300.00d, 200)
            );
            f.add(new Flight("CE", 42l, "X", "A",
                            formatter.parse("2015-08-18 9:00"),
                            formatter.parse("2015-08-18 19:00"),
                            2106.00d, 150)
            );
            f.add(new Flight("EM", 706l, "A", "E",
                            formatter.parse("2015-08-18 22:00"),
                            formatter.parse("2015-08-19 10:00"),
                            100.20d, 310)
            );
            g.add(new Flight("AA", 401l, "E", "M",
                            formatter.parse("2015-08-19 13:00"),
                            formatter.parse("2015-08-20 18:00"),
                            300.00d, 200)
            );
            g.add(new Flight("CE", 32l, "X", "A",
                            formatter.parse("2015-08-18 9:00"),
                            formatter.parse("2015-08-18 19:00"),
                            2106.00d, 150)
            );
            g.add(new Flight("EM", 706l, "A", "E",
                            formatter.parse("2015-08-18 22:00"),
                            formatter.parse("2015-08-19 10:00"),
                            100.20d, 310)
            );
        } catch (ParseException e) {}
        Itinerary another = new Itinerary(f);
        Itinerary another2 = new Itinerary(g);
        assertTrue(another.equals(testItinerary));
        assertTrue(!another2.equals(testItinerary));
    }

    // isFull
    @Test
    public void isFullReturnsCorrectValue() throws
            InvalidFlightException, InvalidItineraryException {
        // first make sure it returns correct value when all flights are empty
        assertTrue(!testItinerary.isFull());
        // make a couple of Itineraries and check if isFull is correct
        TreeSet<Flight> f = new TreeSet<>();
        TreeSet<Flight> g = new TreeSet<>();
        try {
            f.add(new Flight("", 13l, "A", "B",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 14:00"),
                            0.0d, 100)
            );
            f.add(new Flight("", 23l, "B", "C",
                            formatter.parse("2015-08-19 15:00"),
                            formatter.parse("2015-08-19 18:00"),
                            0.0d, 100)
            );
            g.add(new Flight("", 19l, "X", "Y",
                            formatter.parse("2015-08-20 6:00"),
                            formatter.parse("2015-08-20 8:00"),
                            0.0d, 100)
            );
            g.add(new Flight("", 73l, "Y", "Z",
                            formatter.parse("2015-08-20 14:00"),
                            formatter.parse("2015-08-21 1:00"),
                            0.0d, 100)
            );
        } catch (ParseException e) {}

        Itinerary it1 = new Itinerary(f);
        Itinerary it2 = new Itinerary(g);

        // fill the flights
        it1.getFlights().get(1).setNumEmptySeats(0);
        it2.getFlights().get(0).setNumEmptySeats(0);
        it2.getFlights().get(1).setNumEmptySeats(0);

        assertTrue(it1.isFull());
        assertTrue(it2.isFull());
    }

    // bookSeats
    @Test
    public void bookSeatsShouldBookASeatInEveryFlight() throws
            InvalidFlightException, InvalidItineraryException {
        // make a couple of Itineraries and check if isFull is correct

        TreeSet<Flight> f = new TreeSet<>();
        TreeSet<Flight> g = new TreeSet<>();
        TreeSet<Flight> h = new TreeSet<>();
        try {
            f.add(new Flight("", 13l, "A", "B",
                            formatter.parse("2015-08-19 12:00"),
                            formatter.parse("2015-08-19 14:00"),
                            0.0d, 100)
            );
            f.add(new Flight("", 23l, "B", "C",
                            formatter.parse("2015-08-19 15:00"),
                            formatter.parse("2015-08-19 18:00"),
                            0.0d, 5)
            );
            g.add(new Flight("", 19l, "X", "Y",
                            formatter.parse("2015-08-20 6:00"),
                            formatter.parse("2015-08-20 8:00"),
                            0.0d, 10)
            );
            g.add(new Flight("", 73l, "Y", "Z",
                            formatter.parse("2015-08-20 14:00"),
                            formatter.parse("2015-08-21 1:00"),
                            0.0d, 100)
            );
            h.add(new Flight("", 63l, "V", "W",
                            formatter.parse("2015-08-20 6:00"),
                            formatter.parse("2015-08-20 8:00"),
                            0.0d, 70)
            );
            h.add(new Flight("", 112l, "W", "X",
                            formatter.parse("2015-08-20 14:00"),
                            formatter.parse("2015-08-21 1:00"),
                            0.0d, 30)
            );
        } catch (ParseException e) {}
        Itinerary it1 = new Itinerary(f);
        Itinerary it2 = new Itinerary(g);
        Itinerary it3 = new Itinerary(h);
        // book some of these flights
        it2.getFlights().get(0).setNumEmptySeats(0);
        it3.getFlights().get(1).setNumEmptySeats(0);
        it1.bookSeat();
        it2.bookSeat();
        it3.bookSeat();
        assertEquals(it1.getFlights().get(0).getNumEmptySeats(), 99);
        assertEquals(it1.getFlights().get(1).getNumEmptySeats(), 4);
        assertEquals(it2.getFlights().get(0).getNumEmptySeats(), 0);
        assertEquals(it2.getFlights().get(1).getNumEmptySeats(), 100);
        assertEquals(it3.getFlights().get(0).getNumEmptySeats(), 70);
        assertEquals(it3.getFlights().get(1).getNumEmptySeats(), 0);
    }

    // toString
    @Test
    public void toStringShouldReturnCorrectString() {
        String expectedString = "42,2015-08-18 07:00,2015-08-18 18:00,CE,A," +
                "B\n" +
                "706,2015-08-18 23:00,2015-08-19 06:00,EM,B,C\n" +
                "401,2015-08-19 12:00,2015-08-19 17:00,AA,C,D\n" +
                "3700.00\n34:00";
        assertEquals(testItinerary.toString(), expectedString);
    }
}

