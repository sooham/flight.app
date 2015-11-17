/**
 * 
 */
package backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author Angad Singh
 *
 */
public class ItineraryManager {
	
	public HashMap<String[], ArrayList<Itinerary>>Itineraries; 
	public ArrayList<Flight> flights = new ArrayList<Flight>();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public ItineraryManager() {
		// Key(Origin, Destination, yyyy-MM-dd HH:mm)
		this.Itineraries = new HashMap<String[], ArrayList<Itinerary>>();
	}
	
	public void addFlight(Flight newFlight){
		this.flights.add(newFlight);
		addIteraries(newFlight);
	}
	
	private void generateItneraries(){
		this.Itineraries.clear();
		for (int x =0; x<this.flights.size(); x++){
			String[] key = new String[3];
			key[0]=(flights.get(x).getOrigin());
			key[1]=(flights.get(x).getDestination());
			key[2]=(format.format(flights.get(x).getDepartureDateTime()));
			
			ArrayList<Itinerary> value = new ArrayList<Itinerary>();
			ArrayList<Itinerary> value2 = new ArrayList<Itinerary>();
			try {
				Itinerary newFlight = new Itinerary();
				newFlight.addFlightBeginning(flights.get(x));
				value.add(newFlight);
				for (Itinerary i: value){
					for (Flight flight2: this.flights){
						value2 = value; 
						for (Itinerary i2: value2){
							i2.addFlightEnd(flight2);
						}
					}
					value.addAll(value2);
				}
			} catch (InvalidFlightException e) {

			}
			this.Itineraries.put(key, value);
		}
	}
	
	private void addIteraries(Flight f){
		for (String[] key: this.Itineraries.keySet()){
			ArrayList<Itinerary> value = new ArrayList<Itinerary>();
			
			for(Itinerary i: this.Itineraries.get(key)){
				Itinerary newI = i;
				try{
					newI.addFlight(f);
					value.add(newI);
				}catch (IfInvalidItineraryException){
					
				}
			if(!value.isEmpty()){
				String[] newKey = new String[3];
				newKey[0] = value.get(1).getOrigin();
				newKey[1] = value.get(1).getDestination();
				newKey[2] = format.format(value.get(1).getDepartureTime());
			}
		}
		ArrayList<Itinerary> newList = new ArrayList<Itinerary>();
		TreeSet fl = new TreeSet();
		fl.add(f);
		Itinerary I2 = new Itinerary(fl);
		newList.add(I2);
		String[] newKey  = {f.getOrigin(),  f.getDestination(), format.format(f.getDepartureDateTime())};
		this.Itineraries.put(newKey,newList);
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
