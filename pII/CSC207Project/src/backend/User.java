package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import backend.Flight;

public abstract class User implements Serializable{
	
	protected String username;
	protected String password;

	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public void logIn(String test_username, String test_password){
		
	}
	
	public ArrayList<Flight> viewFlights(String origin, String departure, 
			Calendar departureDate){
		// uses a method from FlightManager
	}
	
	public ArrayList<Iternary> viewItineraries(String origin, String departure, 
			Calendar departureDate){
		// uses a method from IternaryManager
	}
	
	public ArrayList<Flight> sortFlights(String origin, String departure, 
			Calendar departureDate){
		// uses a method from FlightManager
	}
	
	public ArrayList<Iternary> sortItineraries(String origin, String departure, 
			Calendar departureDate){
		// uses a method from IternaryManager
	}

}
