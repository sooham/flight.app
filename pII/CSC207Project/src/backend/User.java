package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class User implements Serializable{

	private static final long serialVersionUID = -3786727358644943990L;
	private String LastName;
	private String FirstName;
	private String Email;
	private String Address;
	private int CreditCardNumber;
	private SimpleDateFormat ExpiryDate;
	private Itinerary selectedItinerary;
	private ArrayList<Itinerary> bookedItineraries;

	public User(String LastName,
			String FirstName, String Email, String Address,
			int CreditCardNumber, SimpleDateFormat ExpiryDate) {
		
		this.LastName = LastName;
		this.FirstName = FirstName;
		this.Email = Email;
		this.Address = Address;
		this.CreditCardNumber = CreditCardNumber;
		this.ExpiryDate = ExpiryDate;
		this.selectedItinerary = new Itinerary();
		this.bookedItineraries = new ArrayList<Itinerary>();
	}
	
	public void getInfoFromFile(String dir){
		FileReader in = null;
		BufferedReader br = null;
		String cvsSplitBy = ",";
		
		try{
			in = new FileReader(dir);
			br = new BufferedReader(in);
			String[] values = br.readLine().split(cvsSplitBy);
			this.LastName = values[0];
			this.FirstName = values[1];
			this.Email = values[2];
			this.Address = values[3];
			this.CreditCardNumber = Integer.parseInt(values[4]);
			this.ExpiryDate = new SimpleDateFormat(values[5]);
			
		} catch (IOException e) {
			System.out.println("The file was not found.");
		}
		
	}
	
	/**
	 * Getter for the Client's last name.
	 * @return LastName, a String representation of the Client's last name. 
	 */
	
	public String getLastName() {
		return LastName;
	}
	
	/**
	 * Setter for the Client's last name.
	 * @param lastName, a String representation of the Client's last name.
	 */
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	/**
	 * Getter for the Client's first names.
	 * @return FirstName, a String representation of the Client's first names.
	 */

	public String getFirstName() {
		return FirstName;
	}

	/**
	 * Setter for the Client's first names.
	 * @param firstNames, a String representation of the Client's first names.
	 */
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	/**
	 * Getter for the Client's email address.
	 * @return Email, a String representation of the Client's email
	 * address.
	 */

	public String getEmail() {
		return Email;
	}
	
	/**
	 * Setter for the Client's email address.
	 * @param email, a String representation of the Client's email.
	 * address.
	 */

	public void setEmail(String email) {
		Email = email;
	}
	
	/**
	 * Getter for the Client's email address.
	 * @return Address, a String representation of the Client's address.
	 */

	public String getAddress() {
		return Address;
	}
	
	/**
	 * Setter for the Client's address.
	 * @param address, a String representation of the Client's address.
	 */

	public void setAddress(String address) {
		Address = address;
	}
	
	/**
	 * Getter for the Client's credit card number.
	 * @return CreditCardNumber, an int representation of the Client's
	 * credit card number.
	 */

	public int getCreditCardNumber() {
		return CreditCardNumber;
	}
	
	/**
	 * Setter for the Client's credit card number.
	 * @param creditCardNumber, an int representation of the Client's
	 * credit card number.
	 */

	public void setCreditCardNumber(int creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}
	
	/**
	 * Getter for the Client's credit card expiry date.
	 * @return ExpiryDate, a Calendar (date) representation of the client's
	 * credit card expiry date.
	 */

	public SimpleDateFormat getExpiryDate() {
		return ExpiryDate;
	}
	
	/**
	 * Setter for the Client's credit card expiry date
	 * @param expiryDate, a Calendar (date) representation of the client's
	 * credit card expiry date.
	 */

	public void setExpiryDate(SimpleDateFormat expiryDate) {
		ExpiryDate = expiryDate;

	}
		
	/**
	 * Select an Itinerary from a list of Itinerary.
	 * @param listItineraries, an ArrayList of Itinerary
	 * @param index, an int index for the ArrayList
	 */
	
	public void selectItinerary(ArrayList<Itinerary> listItineraries, int index) {
		this.selectedItinerary = listItineraries.get(index);
	}
	
	/**
	 * Book an itinerary selected using the selectItinerary method.
	 * @param selectedItinerary, an Itinerary previously selected.
	 */
	
	public void bookItinerary(Itinerary selectedItinerary) {
		this.bookedItineraries.add(selectedItinerary);
	}
	
	public ArrayList<Flight> viewFlights(String origin, String departure, 
			Calendar departureDate){
		// uses a method from FlightManager
	}
	
	public ArrayList<Itinerary> viewItineraries(String origin, String departure, 
			Calendar departureDate){
		// uses a method from IternaryManager
	}
	
	public ArrayList<Flight> sortFlights(String origin, String departure, 
			Calendar departureDate){
		// uses a method from FlightManager
	}
	
	public ArrayList<Itinerary> sortItineraries(String origin, String departure, 
			Calendar departureDate){
		// uses a method from IternaryManager
	}
	
	//Methods from the Admin. 
	public void editUserInfo() {
		
	}
	
	public String viewClientInfo() {
		return "Insert client info code here.";
	}
	
	public void addFlight(Flight toAdd) {
		
	}
	
	public void addFlightFromFile() {
		
	}

}
