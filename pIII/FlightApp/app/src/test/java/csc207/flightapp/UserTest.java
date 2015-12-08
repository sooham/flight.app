package csc207.flightapp;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import backend.User;
import backend.Flight;
import backend.Itinerary;
import backend.InvalidFlightException;
import backend.InvalidItineraryException;

public class UserTest {
    private static User testUser1;
    private static User testUser2;
    private static User testUser3;
    private static SimpleDateFormat dateformatter = new SimpleDateFormat
            ("yyyy-MM-dd");
    private static SimpleDateFormat datetimeformatter = new SimpleDateFormat
            ("yyyy-MM-dd HH:mm");

    @BeforeClass
    public static void setUp() throws Exception, ParseException{
        // test if all instantiation works
        testUser1 = new User("Rafiz", "Sooham", "soohamrafiz@gmail.com",
                "972 Shaw St", 1337, dateformatter.parse("2016-1-1"),
                "Sooham1995");

        testUser2 = new User("Min", "Rachel Wong", "rwmin@gmail.com",
                "255 Adelaide West", 1337, dateformatter.parse("2015-11-9 " +
                "0:0"));

        testUser3 = new User("George@Lucas.com", "StarWars");

        assertEquals(testUser1.getLastName(), "Rafiz");
        assertEquals(testUser1.getFirstName(), "Sooham");
        assertEquals(testUser1.getEmail(), "soohamrafiz@gmail.com");
        assertEquals(testUser1.getAddress(), "972 Shaw St");
        assertEquals(testUser1.getCreditCardNumber(), 1337);
        assertEquals(testUser1.getExpiryDate(), dateformatter.parse("2016-1-1" +
                " " +
                "0:0"));
        assertEquals(testUser1.getPassword(), "Sooham1995");
        assertEquals(testUser1.getBookedItineraries(), new ArrayList<Itinerary>());
        assertEquals(testUser2.getPassword(), "");
        assertEquals(testUser3.getLastName(), "");
        assertEquals(testUser3.getFirstName(), "");
        assertEquals(testUser3.getAddress(), "");
        assertEquals(testUser3.getCreditCardNumber(), 0);
        assertEquals(testUser3.getExpiryDate(), dateformatter.parse
                ("0000-00-00"));
    }

    // setters

    @Test
    public void testSetterFieldsWorkCorrectly() throws
            InvalidItineraryException, InvalidFlightException, ParseException {
        testUser1.setLastName("A");
        testUser1.setFirstName("B");
        testUser1.setAddress("C");
        testUser1.setCreditCardNumber(007);
        testUser1.setExpiryDate(dateformatter.parse("2015-12-6 0:0"));
        testUser1.setPassword("D");

        //  make a flight
        Flight example = new Flight("X", 123l, "A", "B",
                datetimeformatter.parse("2015-11-10 7:30"), datetimeformatter
                .parse
                        ("2015-11-11 12:30"), 1500.0, 300);

        // make the itinerary
        TreeSet<Flight> single = new TreeSet<>();
        single.add(example);
        Itinerary singleItinerary = new Itinerary(single);

        // make new booked itineraries and then add
        ArrayList<Itinerary> newBookedItineraries = new ArrayList<Itinerary>();
        newBookedItineraries.add(singleItinerary);

        testUser1.setBookedItineraries(newBookedItineraries);

        assertEquals(testUser1.getLastName(), "A");
        assertEquals(testUser1.getFirstName(), "B");
        assertEquals(testUser1.getEmail(), "soohamrafiz@gmail.com");
        assertEquals(testUser1.getAddress(), "C");
        assertEquals(testUser1.getCreditCardNumber(), 007);
        assertEquals(testUser1.getExpiryDate(), dateformatter.parse
                ("2015-12-6" +
                " 0:0"));
        assertEquals(testUser1.getBookedItineraries(),
                new ArrayList<Itinerary>(newBookedItineraries));
        assertEquals(testUser1.getPassword(), "D");
    }

    // bookItinerary
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
        TreeSet<Flight> ts5 = new TreeSet<>(); // contains a full itinerary

        ts1.add(f1);

        ts2.add(f2);

        ts3.add(f3);

        ts4.add(f1);
        ts4.add(f3);

        ts5.add(f1);
        ts5.add(f2);

        Itinerary it1 = new Itinerary(ts1);
        Itinerary it2 = new Itinerary(ts2);
        Itinerary it3 = new Itinerary(ts3);
        Itinerary it4 = new Itinerary(ts4); // contains two same itineraries
        Itinerary it5 = new Itinerary(ts5); // contains two same itineraries


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
        assertEquals(testUser1.getBookedItineraries().size(), oldSize);
        assertTrue(!testUser1.getBookedItineraries().contains(it2));

        // an itinerary that contains one full
        assertTrue(it5.isFull());
        testUser1.bookItinerary(it5);
        assertEquals(testUser1.getBookedItineraries().size(), oldSize);
        assertTrue(!testUser1.getBookedItineraries().contains(it5));

        // an itinerary that contains a previous itinerary
        testUser1.bookItinerary(it4);
        assertTrue(testUser1.getBookedItineraries().contains(it4));

    }

    // equals
    @Test
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

    // toString
    @Test
    public void toStringShouldReturnCorrectString() {
        // TODO: What if one of the fields in null?

        String expectedString= "Rafiz,Sooham,soohamrafiz@gmail.com,972 Shaw" +
                " " +
                "St,1337,2016-01-01";

        assertEquals(testUser1.toString(), expectedString);
    }
}
