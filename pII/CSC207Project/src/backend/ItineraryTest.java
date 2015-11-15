package backend;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ItineraryTest {
	Flight flight1;
	Flight flight2;
	Flight flight3;
	Date c1;
	Date c2;
	Date c3;
	Date c4;
	Date c5;
	Date c6;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Before
	public void setUp() throws Exception {
		
		this.c1 = (Date) format.parse("2013-01-28 13:30");
		this.c2 = (Date) format.parse("2013-01-28 14:00");
		this.c3 = (Date) format.parse("2013-01-28 14:30");
		this.c4 = (Date) format.parse("2013-01-28 15:00");
		this.c5 = (Date) format.parse("2013-01-28 15:30");
		this.c6 = (Date) format.parse("2013-01-28 16:05");
		this.flight1 = new Flight("France", "Canada", 100.0, c1, c2, "Airline", 1002342);
		this.flight2 = new Flight("Canada", "Germany", 100.0, c3, c4, "Airline", 1002342453);
		this.flight3 = new Flight("Germany", "Italy", 100.0, c5, c6, "Airline", 104532342);
	}

	@Test
	public void testAddFlightsEnd() throws InvalidFlightException {
		Itinerary i = new Itinerary();
		i.addFlightEnd(flight1);
		i.addFlightEnd(flight2);
		i.addFlightEnd(flight3);
		assertEquals(i.getOrigin(),"France");
		assertEquals(i.getDestination(), "Italy");
	}
	
	@Test
	public void testAddFlightsBeginning() throws InvalidFlightException {
		Itinerary i = new Itinerary();
		i.addFlightBeginning(flight3);
		i.addFlightBeginning(flight2);
		i.addFlightBeginning(flight1);
		assertEquals(i.getOrigin(),"France");
		assertEquals(i.getDestination(), "Italy");
	}
	
	@Test
	public void testDuration() throws InvalidFlightException {
		Itinerary i = new Itinerary();
		i.addFlightEnd(flight2);
		i.addFlightEnd(flight3);
		System.out.println(i.getArrivalTime());
		System.out.println(i.getDepartureTime());
		assertEquals(i.getDuration(), 1.5, 0.1);
	}
	
	@Test
	public void testCreatingItenaryFromList() throws InvalidFlightException {
		ArrayList<Flight> l = new ArrayList<Flight>(){{
			add(flight2);
			add(flight1);
			add(flight3);
			}};
	    Itinerary i = new Itinerary(l);
		assertEquals(i.getOrigin(),"France");
		assertEquals(i.getDestination(), "Italy");
	}
	

}
