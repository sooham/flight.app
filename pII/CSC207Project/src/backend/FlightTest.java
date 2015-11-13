/**
 * 
 */
package backend;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Angad Singh
 *
 */
public class FlightTest {
	Flight flight1;
	Flight flight2;
	Flight flight3;
	Calendar c1;
	Calendar c2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.c1 = new GregorianCalendar(2013,1,28,13,24,56);
		this.c2 = new GregorianCalendar(2013,1,28,14,24,56);
		this.flight1 = new Flight("France", "Canada", 100.0, c1, c2, "Airline", 1002342);
		this.flight2 = new Flight("France", "Canada", 100.0, c1, c2, "Airline", 1002342);
		this.flight3 = new Flight("Germany", "Italy", 100.0, c1, c2, "Airline", 104532342);
	}

	@Test
	public void testCalulateDuration() {
		assertTrue(flight1.calulateDuration()==1.0);	
	}
	
	@Test
	public void testFlightEquals(){
		assertTrue(flight1.equals(flight2));
	}

}
