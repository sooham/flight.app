package csc207.flightapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.junit.BeforeClass;
import org.junit.Test;

import backend.Flight;
import backend.FlightManager;
import backend.InvalidFlightException;
import backend.InvalidItineraryException;
import backend.Itinerary;

public class FlightManagerTest {
    private static FlightManager flightManager;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");
    private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        flightManager = FlightManager.getInstance();
        assertTrue(flightManager.itineraries.isEmpty());
        assertTrue(flightManager.flights.isEmpty());
    }

    @Test
    public void updateFlightShouldWorkCorrectlyWithASingleNewFlight() {
        // add a single new Flight to this FM (disjoint from all other flights)
        // check if the flight manager flights hash map is correct
        // check if the itineraries hashmap is correct
        // give that flight in again with a different origin
        // check hashmaps
        // give that flight in again with a different destination
        // check hashmaps
        // give that flight in again with a different departuredatetime
        // check hashmaps
    }

    @Test
    public void updateFlightWorksCorrectlyWithMultipleFlights() throws
            InvalidFlightException, InvalidItineraryException, ParseException {
        // time conflict, city discontinuity, layover too long, cyclical

        // initial flight
        Flight f1 = new Flight("", 1l, "C", "D",
                dateTimeFormatter.parse("2015-08-19 1:00"),
                dateTimeFormatter.parse("2015-08-19 2:00"),
                0.0,
                100
        );
        // a flight in direct time conflict with f1
        Flight f2 = new Flight("", 2l, "D", "E",
                dateTimeFormatter.parse("2015-08-19 1:30"),
                dateTimeFormatter.parse("2015-08-19 3:00"),
                0.0,
                100
        );
        // a flight linking correctly with f1, but layover too long
        Flight f3 = new Flight("", 3l, "D", "E",
                dateTimeFormatter.parse("2015-08-19 8:01"),
                dateTimeFormatter.parse("2015-08-19 9:00"),
                0.0,
                100
        );
        // a flight in correct timing of f1 but city discontinuous with f1
        Flight f4 = new Flight("", 4l, "O", "F",
                dateTimeFormatter.parse("2015-08-19 3:00"),
                dateTimeFormatter.parse("2015-08-19 4:00"),
                0.0,
                100
        );
        // a flight can be added to the front of f1
        Flight f5 = new Flight("", 5l, "B", "C",
                dateTimeFormatter.parse("2015-08-18 23:00"),
                dateTimeFormatter.parse("2015-08-19 1:00"),
                0.0,
                100
        );
        // a flight that can be added to the end of f4
        Flight f6 = new Flight("", 6l, "F", "G",
                dateTimeFormatter.parse("2015-08-19 5:00"),
                dateTimeFormatter.parse("2015-08-19 6:00"),
                0.0,
                100
        );
        // a flight that joins f1 and f4 correctly
        Flight f7 = new Flight("", 7l, "D", "O",
                dateTimeFormatter.parse("2015-08-19 2:00"),
                dateTimeFormatter.parse("2015-08-19 3:00"),
                0.0,
                100
        );
        // a flight city discontinuous and layover too long to f2 and
        // contains a city in f2
        Flight f8 = new Flight("", 8l, "X", "D",
                dateTimeFormatter.parse("2015-08-19 10:00"),
                dateTimeFormatter.parse("2015-08-19 11:00"),
                0.0,
                100
        );
        // a flight connecting f2 and f8 correctly, making a cyclical itinerary
        Flight f9 = new Flight("", 9l, "E", "X",
                dateTimeFormatter.parse("2015-08-19 4:00"),
                dateTimeFormatter.parse("2015-08-19 9:00"),
                0.0,
                100
        );
        // a flight to put at the end of f1 but contains a city in f5
        Flight f10 = new Flight("", 10l, "D", "B",
                dateTimeFormatter.parse("2015-08-19 3:00"),
                dateTimeFormatter.parse("2015-08-19 4:00"),
                0.0,
                100
        );
        // a flight city discont and time discont to all itineraries above
        Flight f11 = new Flight("", 11l, "M", "N",
                dateTimeFormatter.parse("2015-08-20 1:00"),
                dateTimeFormatter.parse("2015-08-20 2:00"),
                0.0,
                100
        );

        // add these flight to the FM
        ArrayList<Flight> toAddFlight = new ArrayList<Flight>();
        toAddFlight.add(f1);
        toAddFlight.add(f2);
        toAddFlight.add(f3);
        toAddFlight.add(f4);
        toAddFlight.add(f5);
        toAddFlight.add(f6);
        toAddFlight.add(f7);
        toAddFlight.add(f8);
        toAddFlight.add(f9);
        toAddFlight.add(f10);
        toAddFlight.add(f11);

        for (Flight f: toAddFlight) {
            flightManager.update(f);
        }
        // then check the hashmaps are correct
        // first check if the size of the flights HashMap is correct
        assertEquals(flightManager.flights.size(), 10);

        // make a copy of the expected flights HashMap
        Map<ArrayList<String>, ArrayList<Flight>> expectedFlightsMap = new
                HashMap<>();
        // fill the expected map
        ArrayList<String> f1Key = new ArrayList<>();
        f1Key.add(f1.getOrigin());
        f1Key.add(f1.getDestination());
        f1Key.add(dateFormatter.format(f1.getDepartureDateTime()));
        ArrayList<Flight> f1Value = new ArrayList<>();
        f1Value.add(f1);
        expectedFlightsMap.put(f1Key, f1Value);

        ArrayList<String> f2Key = new ArrayList<>();
        f2Key.add(f2.getOrigin());
        f2Key.add(f2.getDestination());
        f2Key.add(dateFormatter.format(f2.getDepartureDateTime()));
        ArrayList<Flight> f2Value = new ArrayList<>();
        f2Value.add(f2);
        f2Value.add(f3);
        expectedFlightsMap.put(f2Key, f2Value);

        for (Flight f: toAddFlight.subList(3, toAddFlight.size())) {
            ArrayList<String> fKey = new ArrayList<>();
            fKey.add(f.getOrigin());
            fKey.add(f.getDestination());
            fKey.add(dateFormatter.format(f.getDepartureDateTime()));
            ArrayList<Flight> fValue = new ArrayList<>();
            fValue.add(f);
            expectedFlightsMap.put(fKey, fValue);

        }
        // now check if the maps are the same
        assertEquals(flightManager.flights, expectedFlightsMap);

        // now check if itineraries HashMap is the expected value

        // first create the Itineraries
        // deal with the single flight Itineraries
        ArrayList<Itinerary> singleFlightItineraries = new ArrayList<Itinerary>();

        for (Flight f: toAddFlight) {
            TreeSet<Flight> singleTS = new TreeSet<>();
            singleTS.add(f);
            Itinerary it = new Itinerary(singleTS);
            singleFlightItineraries.add(it);
        }

        Map<ArrayList<String>, ArrayList<Itinerary>> expectedItinerariesMap =
                new
                HashMap<>();

        ArrayList<String> it1Key = new ArrayList<>();
        it1Key.add(singleFlightItineraries.get(0).getOrigin());
        it1Key.add(singleFlightItineraries.get(0).getDestination());
        it1Key.add(dateFormatter.format(
                singleFlightItineraries.get(0).getDepartureDateTime()));
        ArrayList<Itinerary> it1Value = new ArrayList<Itinerary>();
        it1Value.add(singleFlightItineraries.get(0));
        expectedItinerariesMap.put(it1Key, it1Value);

        ArrayList<String> it2Key = new ArrayList<>();
        it2Key.add(singleFlightItineraries.get(1).getOrigin());
        it2Key.add(singleFlightItineraries.get(1).getDestination());
        it2Key.add(dateFormatter.format(
                singleFlightItineraries.get(1).getDepartureDateTime()));
        ArrayList<Itinerary> it2Value = new ArrayList<Itinerary>();
        it2Value.add(singleFlightItineraries.get(1));
        it2Value.add(singleFlightItineraries.get(2));
        expectedItinerariesMap.put(it2Key, it2Value);

        for (Itinerary it: singleFlightItineraries.subList(
                3, singleFlightItineraries.size())) {
            ArrayList<String> itKey = new ArrayList<>();
            itKey.add(it.getOrigin());
            itKey.add(it.getDestination());
            itKey.add(dateFormatter.format(it.getDepartureDateTime()));
            ArrayList<Itinerary> itValue = new ArrayList<Itinerary>();
            itValue.add(it);
            expectedItinerariesMap.put(itKey, itValue);
        }

        // now add the multiple flight itineraries into this map
        ArrayList<Itinerary> multipleFlightItinerary = new ArrayList<>();

        // f5 f1 f7 f4 f6
        TreeSet<Flight> TS1 = new TreeSet<>();
        TS1.add(f5);
        TS1.add(f1);
        TS1.add(f7);
        TS1.add(f4);
        TS1.add(f6);
        Itinerary IT1 = new Itinerary(TS1);
        multipleFlightItinerary.add(IT1);

        // f5 f1 f7 f4
        TreeSet<Flight> TS2 = new TreeSet<>();
        TS2.add(f5);
        TS2.add(f1);
        TS2.add(f7);
        TS2.add(f4);
        Itinerary IT2 = new Itinerary(TS2);
        multipleFlightItinerary.add(IT2);

        // f1 f7 f4 f6
        TreeSet<Flight> TS3 = new TreeSet<>();
        TS3.add(f1);
        TS3.add(f7);
        TS3.add(f4);
        TS3.add(f6);
        Itinerary IT3 = new Itinerary(TS3);
        multipleFlightItinerary.add(IT3);

        // f5 f1 f7
        TreeSet<Flight> TS4 = new TreeSet<>();
        TS4.add(f5);
        TS4.add(f1);
        TS4.add(f7);
        Itinerary IT4 = new Itinerary(TS4);
        multipleFlightItinerary.add(IT4);

        // f1 f7 f4
        TreeSet<Flight> TS5 = new TreeSet<>();
        TS5.add(f1);
        TS5.add(f7);
        TS5.add(f4);
        Itinerary IT5 = new Itinerary(TS5);
        multipleFlightItinerary.add(IT5);

        // f7 f4 f6
        TreeSet<Flight> TS6 = new TreeSet<>();
        TS6.add(f7);
        TS6.add(f4);
        TS6.add(f6);
        Itinerary IT6 = new Itinerary(TS6);
        multipleFlightItinerary.add(IT6);

        // f5 f1
        TreeSet<Flight> TS7 = new TreeSet<>();
        TS7.add(f5);
        TS7.add(f1);
        Itinerary IT7 = new Itinerary(TS7);
        multipleFlightItinerary.add(IT7);

        // f1 f7
        TreeSet<Flight> TS8 = new TreeSet<>();
        TS8.add(f1);
        TS8.add(f7);
        Itinerary IT8 = new Itinerary(TS8);
        multipleFlightItinerary.add(IT8);

        // f7 f4
        TreeSet<Flight> TS9 = new TreeSet<>();
        TS9.add(f7);
        TS9.add(f4);
        Itinerary IT9 = new Itinerary(TS9);
        multipleFlightItinerary.add(IT9);

        // f4 f6
        TreeSet<Flight> TS10 = new TreeSet<>();
        TS10.add(f4);
        TS10.add(f6);
        Itinerary IT10 = new Itinerary(TS10);
        multipleFlightItinerary.add(IT10);

        // f2 f9
        TreeSet<Flight> TS11 = new TreeSet<>();
        TS11.add(f2);
        TS11.add(f9);
        Itinerary IT11 = new Itinerary(TS11);
        multipleFlightItinerary.add(IT11);

        // f9 f8
        TreeSet<Flight> TS12 = new TreeSet<>();
        TS12.add(f9);
        TS12.add(f8);
        Itinerary IT12 = new Itinerary(TS12);
        multipleFlightItinerary.add(IT12);

        // f1 f10
        TreeSet<Flight> TS13 = new TreeSet<>();
        TS13.add(f1);
        TS13.add(f10);
        Itinerary IT13 = new Itinerary(TS13);
        multipleFlightItinerary.add(IT13);

        // now add all the multiple flight to the expected Itinerary Map
        for (Itinerary it: multipleFlightItinerary) {
            ArrayList<String> key = new ArrayList<>();
            key.add(it.getOrigin());
            key.add(it.getDestination());
            key.add(dateFormatter.format(it.getDepartureDateTime()));
            ArrayList<Itinerary> value = new ArrayList<Itinerary>();
            value.add(it);
            expectedItinerariesMap.put(key, value);
        }

        assertEquals(flightManager.itineraries.size(),
                expectedItinerariesMap.size());
        assertEquals(flightManager.itineraries, expectedItinerariesMap);

        // TODO: Complete the below
        // edit a couple of flights
        // edit a single flight to form a new itinerary (f11)
        // edit a flight to invalidate itinerary (f1, f3, f8)
        // add them to the FM
        // check the HashMaps
    }

    @Test
    public void getItinerariesShouldReturnCorrectItineraries() {
        // should all have the same departure date
        // origin, destination, and all of them are empty
    }

    @Test
    public void getItinerariesShouldReturnEmptyIfNoItineraryExists() {
    }

    @Test
    public void getFlightsShouldReturnCorrectFlights() {
        // should all have the same departure date
        // origin, destination and all flight are empty
    }

    @Test
    public void getFlightsShouldReturnEmptyIfNoFlightsExists() {
    }

    @Test
    public void sortByPriceShouldSortCorrectly() {
        // sortByPrice Aliases
    }

    @Test
    public void sortByDurationShouldSortCorrectly() {
        // sortByDuration Aliases
    }
}

