package csc207.flightapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import backend.User;
import backend.Flight;
import backend.Itinerary;
import backend.InvalidFlightException;
import backend.InvalidItineraryException;

public class UserTest {
    private User testUser1;
    private User testUser2;
    private User testUser3;
    @Before
    public void setUp() throws Exception {
        // test if all instantiation works
        testUser1 = new User("Rafiz", "Sooham", "soohamrafiz@gmail.com",
                "972 Shaw St", 1337, new Date(2016, 1, 1, 0, 0), "Sooham1995");

        testUser2 = new User("Min", "Rachel Wong", "rwmin@gmail.com",
                "255 Adelaide West", 1337, new Date(2015, 11, 9, 0, 0));

        testUser3 = new User("George@Lucas.com", "StarWars");
    }

    @Test
    public void testInstantiationShouldSetFieldsCorrectly() {
        assertEquals(testUser1.getLastName(), "Rafiz");
        assertEquals(testUser1.getFirstName(), "Sooham");
        assertEquals(testUser1.getEmail(), "soohamrafiz@gmail.com");
        assertEquals(testUser1.getAddress(), "972 Shaw St");
        assertEquals(testUser1.getCreditCardNumber(), 1337);
        assertEquals(testUser1.getExpiryDate(), new Date(2016, 1, 1, 0, 0));
        assertEquals(testUser1.getPassword(), "Sooham1995");
        assertEquals(testUser1.getBookedItineraries(), new ArrayList<Itinerary>());
        assertNull(testUser2.getPassword());
        assertNull(testUser3.getLastName());
        assertNull(testUser3.getFirstName());
        assertNull(testUser3.getAddress());
        assertEquals(testUser3.getCreditCardNumber(), 0);
        assertNull(testUser3.getExpiryDate());
    }

    @Test
    public void testSetterFieldsWorkCorrectly() throws
            InvalidItineraryException, InvalidFlightException {
        testUser1.setLastName("A");
        testUser1.setFirstName("B");
        testUser1.setAddress("C");
        testUser1.setCreditCardNumber(007);
        testUser1.setExpiryDate(new Date(2015, 12, 6, 0, 0));
        testUser1.setPassword("D");

        //  make a flight
        Flight example = new Flight("X", 123l, "A", "B",
                new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 11, 12, 30),
                1500.0, 300);

        // make the itinerary
        TreeSet<Flight> single = new TreeSet<>();
        single.add(example);
        Itinerary singleItinerary = new Itinerary(single);

        // make new booked itineraries and then add
        List<Itinerary> newBookedItineraries = new ArrayList<Itinerary>();
        newBookedItineraries.add(singleItinerary);

        testUser1.setBookedItineraries(newBookedItineraries);

        assertEquals(testUser1.getLastName(), "A");
        assertEquals(testUser1.getFirstName(), "B");
        assertEquals(testUser1.getEmail(), "soohamrafiz@gmail.com");
        assertEquals(testUser1.getAddress(), "C");
        assertEquals(testUser1.getCreditCardNumber(), 007);
        assertEquals(testUser1.getExpiryDate(), new Date(2015, 12, 6, 0, 0));
        assertEquals(testUser1.getBookedItineraries(),
                new ArrayList<Itinerary>(newBookedItineraries));
        assertEquals(testUser1.getPassword(), "D");
    }

    @Test
    public void bookItineraryShouldBookCorrectly() throws
            InvalidFlightException, InvalidItineraryException {
        Flight f1 = new Flight("Y", 123l, "C", "D",
                new Date(2015, 11, 10, 7, 30), new Date(2015, 11, 11, 12, 30),
                1500.0, 300);

        Flight f2 = new Flight("", 2l, "D", "E",
                new Date(2015, 11, 11, 13, 30), new Date(2015, 11, 11, 14, 30),
                0.0,
                10
        );

        Flight f3 = new Flight("Y", 123l, "D", "F",
                new Date(2015, 11, 11, 13, 30), new Date(2015, 11, 11, 14, 30),
                0.0,
                120
        );

        f2.setNumEmptySeats(0);

        // make some itineraries
        TreeSet<Flight> ts1 = new TreeSet<>(); // new flight
        TreeSet<Flight> ts2 = new TreeSet<>(); // contains a full flight
        TreeSet<Flight> ts3 = new TreeSet<>(); // contains new flight
        TreeSet<Flight> ts4 = new TreeSet<>(); // contains new flight multiple

        ts1.add(f1);

        ts2.add(f2);

        ts3.add(f3);

        ts4.add(f1);
        ts4.add(f3);

        Itinerary it1 = new Itinerary(ts1);
        Itinerary it2 = new Itinerary(ts2);
        Itinerary it3 = new Itinerary(ts3);
        Itinerary it4 = new Itinerary(ts4); // contains two same itineraries

        // itinerary already contained does not book
        testUser1.bookItinerary(it1);
        assertTrue(testUser1.getBookedItineraries().contains(it1));
        int oldSize = testUser1.getBookedItineraries().size();
        testUser1.bookItinerary(it1);
        assertEquals(testUser1.getBookedItineraries().size(), oldSize);

        // an itinerary that has been updated
        testUser1.bookItinerary(it3);
        assertEquals(testUser1.getBookedItineraries().size(), oldSize);

        // itinerary that is full
        assertTrue(it2.isFull());
        testUser1.bookItinerary(it2);
        assertTrue(!testUser1.getBookedItineraries().contains(it2));

        // an itinerary that contains a previous itinerary
        testUser1.bookItinerary(it4);
        assertTrue(testUser1.getBookedItineraries().contains(it4));
    }

    public void equalsShouldWorkCorrectly() {
        User doppelganger = new User("soohamrafiz@gmail.com", "ABCD");
        // email is the same
        assertTrue(testUser1.equals(testUser1));
        assertTrue(testUser1.equals(doppelganger));
        // email is not the same
        assertTrue(!testUser1.equals(testUser2));
        assertTrue(!testUser1.equals(testUser3));
        assertTrue(!testUser2.equals(testUser3));
    }
}
