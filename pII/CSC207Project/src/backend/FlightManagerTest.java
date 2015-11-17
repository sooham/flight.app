/**
 * 
 */
package backend;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Angad Singh
 *
 */
public class FlightManagerTest {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	Flight f1 = null; 
	Flight f2 = null; 
	Flight f3 = null; 
	
	Date d1 = null;
	Date d2 = null; 
	Date d3 = null; 
	Date d4 = null; 
	Date d5 = null; 
	Date d6 = null; 
	
	@Before
	public void setUp() throws Exception {
		d1= format.parse("2011-02-21 12:00");
		d2= format.parse("2011-02-21 12:30");
		d3= format.parse("2011-02-21 13:00");
		d4= format.parse("2011-02-21 13:30");
		d5= format.parse("2011-02-21 14:00");
		d6= format.parse("2011-02-21 14:30");
		
		f1 = new Flight("Airline", 10000232L, "Canada", "France", d1,d2, 100.0);
		f2 = new Flight("Airline", 11000232L, "France", "Germany", d3,d4, 100.0);
		f3 = new Flight("Airline", 10300232L, "Germany", "Spain", d5,d6, 100.0);
		
		
	}

	@Test
	public void test() {
		FlightManager manager = new FlightManager();
		manager.addFlight(f1);
		manager.addFlight(f2);
		manager.addFlight(f3);
		for(String[] key: manager.Itineraries.keySet()){
			System.out.println(key[0] +" " +key[1] +" " + key[2]);
			for(Itinerary i:manager.Itineraries.get(key)){
				System.out.println("       "+i);
			}
		}
		
		assertEquals(1,1, 0.0);
		
	}

}
