package backend;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class ItenaryTest {
	Flight flight1;
	Flight flight2;
	Flight flight3;
	Calendar c1;
	Calendar c2;
	Calendar c3;
	Calendar c4;
	Calendar c5;
	Calendar c6;

	@Before
	public void setUp() throws Exception {
		this.c1 = new GregorianCalendar(2013,1,28,13,30);
		this.c2 = new GregorianCalendar(2013,1,28,14,00);
		this.c3 = new GregorianCalendar(2013,1,28,14,30);
		this.c4 = new GregorianCalendar(2013,1,28,15,00);
		this.c5 = new GregorianCalendar(2013,1,28,15,30);
		this.c6 = new GregorianCalendar(2013,1,28,16,00);
		this.flight1 = new Flight("France", "Canada", 100.0, c1, c2, "Airline", 1002342);
		this.flight2 = new Flight("Canada", "Germany", 100.0, c3, c4, "Airline", 1002342453);
		this.flight3 = new Flight("Germany", "Italy", 100.0, c5, c6, "Airline", 104532342);
	}

	@Test
	public void testAddFlightsEnd() throws InvalidFlightException {
		Itenary i = new Itenary();
		i.addFlightEnd(flight1);
		i.addFlightEnd(flight2);
		i.addFlightEnd(flight3);
		assertEquals(i.getOrigin(),"France");
		assertEquals(i.getDestination(), "Italy");
	}
	
	@Test
	public void testAddFlightsBeginning() throws InvalidFlightException {
		Itenary i = new Itenary();
		i.addFlightBeginning(flight3);
		i.addFlightBeginning(flight2);
		i.addFlightBeginning(flight1);
		assertEquals(i.getOrigin(),"France");
		assertEquals(i.getDestination(), "Italy");
	}
	
	@Test
	public void testDuration() throws InvalidFlightException {
		Itenary i = new Itenary();
		i.addFlightBeginning(flight2);
		i.addFlightBeginning(flight1);
		assertEquals(i.getDuration(), 1.5, 0.0);
	}
	
	@Test
	public void testCreatingItenaryFromList() throws InvalidFlightException {
		ArrayList<Flight> l = new ArrayList<Flight>(){{
			add(flight2);
			add(flight1);
			add(flight3);
			}};
		Itenary i = new Itenary(l);
		assertEquals(i.getOrigin(),"France");
		assertEquals(i.getDestination(), "Italy");
	}
	

}
