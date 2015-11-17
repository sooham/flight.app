/**
 * 
 */
package backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author Angad Singh
 *
 */
public class FlightManager {
	
	public HashMap<String[], ArrayList<Itinerary>>Itineraries; 
	public ArrayList<Flight> flights = new ArrayList<Flight>();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public FlightManager() {
		// Key(Origin, Destination, yyyy-MM-dd HH:mm)
		this.Itineraries = new HashMap<String[], ArrayList<Itinerary>>();
	}
	
	public void addFlight(Flight newFlight){
		this.flights.add(newFlight);
		addIteraries(newFlight);
	}
	
	public void addIteraries(Flight f){
		HashSet<String[]> oldKeys = new HashSet<String[]>(Itineraries.keySet());
		for (String[] key: oldKeys){
			ArrayList<Itinerary> value = new ArrayList<Itinerary>();
			
			for(Itinerary i: Itineraries.get(key)){
				Itinerary newI = null;
				try{
					newI = i.addFlight(f);
					value.add(newI);
				}catch (InvalidItineraryException e){
				
				}
				if(!value.isEmpty()){
					String[] newKey = {value.get(0).getOrigin(),value.get(0).getDestination()
							,format.format(value.get(0).getDepartureDateTime())};
					Itineraries.put(newKey, value);
				}
			}
		}
		//Adds the single Flight Itinerary to the HashMap
		try {
			ArrayList<Itinerary> newList = new ArrayList<Itinerary>();
			TreeSet fl = new TreeSet();
			fl.add(f);
			Itinerary I2 = new Itinerary(fl);
			newList.add(I2);
			String[] newKey  = {f.getOrigin(),  f.getDestination(), format.format(f.getDepartureDateTime())};
			Itineraries.put(newKey,newList);
		} catch (InvalidItineraryException e) {
		
		}catch (InvalidFlightException c) {
			
		}
		
		
		
	}
	
	public ArrayList<Itinerary> getItineraries(String origin, String destination, String departureDate){
		ArrayList<String> key = new ArrayList<String>();
		key.add(origin);
		key.add(destination);
		key.add(departureDate);
		return this.Itineraries.get(key);
		
	}
	
	public Flight getFlight(String origin, String destination, String departureDate) throws ParseException{
		Date date = format.parse(departureDate);
		Flight f = null; 
		for (Flight f2: this.flights){
			if (f2.getOrigin() == origin && f2.getDestination() == destination
					&& f2.getDepartureDateTime() == date){
				f = f2;
				break;
			}
		}
		return f; 
	}
}
