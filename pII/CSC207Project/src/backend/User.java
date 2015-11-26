package backend;

import java.io.Serializable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A User object. A User is a backend representation for both Client and 
 * Administrator objects. Every User has a first name, last name, email,
 * address, credit card number and its related expiry date. 
 * The expiry date is in the format 'YYYY-MM-DD'
 * 
 * <p>For phase III we do not directly instantiate User object, instead
 * we subclass User into Client and Administrator objects. User serves
 * as the object to bridge the similarities between Client and Administrator.
 */
public class User implements Serializable{

	private static final long serialVersionUID = -3786727358644943990L;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat(
																"yyyy-MM-dd");

	private String lastName;
	private String firstName;
	private String email;
	private String password;

	private String address;

	private int creditCardNumber;
	private Date expiryDate;

	private Itinerary selectedItinerary;			// TODO	: Remove
	private List<Itinerary> bookedItineraries;	// TODO : Remove
	
	/**
	 * Creates a new User instance with the given fields. Takes a last name, 
	 * first name, email, address, credit card number, its expiry date and
	 * password and creates the corresponding User.
	 *  
	 * @param lastName  this user's last name. 
	 * @param firstName  this user's first name. 
	 * @param email  this user's email. 
	 * @param address  this user's address. 
	 * @param creditCardNumber  this user's credit card number. 
	 * @param expiryDate  a Date indicating this user's credit card expiry date.
	 * @param password  the password for this user.
	 */
	public User(String lastName, String firstName, String email, String address,
	int creditCardNumber, Date expiryDate, String password) {
			this.lastName = lastName;
			this.firstName = firstName;
			this.email = email;
			this.address = address;
			this.creditCardNumber = creditCardNumber;
			this.expiryDate = expiryDate;
			this.password = password;
			this.selectedItinerary = null;							// TODO: Remove
			this.bookedItineraries = new ArrayList<Itinerary>();	// TODO: Remove
	}

	/**
	 * Getter for this User's last name.
	 * 
	 * @return  this user's last name.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Setter for this User's last name.
	 * 
	 * @param lastName  this user's new last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Getter for this User's first name.
	 * 
	 * @return this user's first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for this User's first name.
	 * 
	 * @return firstName  this user's new first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Getter for this User's email address.
	 * 
	 * @return  this user's email address.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Setter for the Client's email address.
	 * 
	 * @param email  this user's new email address.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Getter for this User's address.
	 * 
	 * @return this user's address.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Setter for this User's address.
	 * 
	 * @param address  this user's new address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Getter for the User's credit card number.
	 * 
	 * @return this user's credit card number.
	 */
	public int getCreditCardNumber() {
		return creditCardNumber;
	}
	
	/**
	 * Setter for this user's credit card number.
	 * 
	 * @param creditCardNumber  this user's new credit card number.
	 */
	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	/**
	 * Getter for this User's credit card expiry Date.
	 * 
	 * @return this user's credit card's expiry Date.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	/**
	 * Setter for this User's credit card expiry Date.
	 * 
	 * @param expiryDate  a Date representing this user's new  credit card 
	 * expiry date.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Getter for the User's Password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Adds a User (Client or Administrator) as an instantiated User into 
	 * UserManager from CSV file. The User added is also serialized and 
	 * stored in FileDatabase.
	 * 
	 * <p>Note that the CSV file must have a valid format of
	 * "LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate" for
	 * User to be created properly, otherwise will throw IOException.
	 * 
	 * @param dir  the path to CSV file. 
	 * @throws IOException if the CSV file is the wrong format
	 */
	public void addUserFromFile(String dir) throws IOException {
		FileDatabase.getInstance().addUserFromFile(dir);						
	}
	
	/**
	 * Adds a Flight as an instantiated Flight into FlightManager from CSV file.
	 * The Flight added is also serialized and stored in FileDatabase.
	 * 
	 * <p>Note that the CSV file must have a valid format of
	 * "Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination,
	 * Price" for Flight to be created properly, otherwise will throw 
	 * IOException.
	 * 
	 * @param dir  the path to CSV file. 
	 * @throws IOException if the CSV file is the wrong format
	 */
	public void addFlightFromFile(String dir) throws IOException {
		FileDatabase.getInstance().addFlightFromFile(dir);
	}

		
	/**
	 * Select an Itinerary from a List of Itinerary for this User.
	 * 
	 * @param itineraries  a List of Itinerary.
	 * @param index  an index indicating the Itinerary to select.
	 */
	public void selectItinerary(List<Itinerary> itineraries,			// TODO: Remove
	int index) {
		selectedItinerary = itineraries.get(index);
	}
	
	/**
	 * Book an Itinerary for this User.
	 * 
	 * @param selectedItinerary, an Itinerary previously selected.
	 */
	public void bookItinerary(Itinerary selectedItinerary) {			// TODO: Remove
		bookedItineraries.add(selectedItinerary);
	}
	
	/**
	 * Returns a List of Flight's given their origin, destination and departure
	 * date for this User to explore.
	 *  
	 * @param origin  the origin of the Flight to search.
	 * @param destination  the destination of the Flight to search.
	 * @param departureDate  a String of format YYYY-MM-DD indicating the
	 * departing dates for Flight to search.
	 *  
	 * @return a List of Flights fulfilling criterion, empty List if no
	 * such Flight exist.
	 */
	public List<Flight> searchFlights(String origin, String destination, 
			String departureDate) {
		return FileDatabase.getInstance().getFlightManger().getFlights( 		// TODO: Check if compatible method, called appropriately?
				origin, destination, departureDate);
	}
	
	/**
	 * Returns a List of Itineraries given their origin, destination and departure
	 * date for this User to explore.
	 *  
	 * @param origin  the origin of the Itinerary to search.
	 * @param destination  the destination of the Itinerary to search.
	 * @param departureDate  a String of format YYYY-MM-DD indicating the
	 * departing dates for Itinerary to search.
	 *  
	 * @return a List of Itinerary fulfilling criterion, empty List if no
	 * such Itinerary exist.
	 */
	public List<Itinerary> seachItineraries(String origin, String destination,
			String departureDate) {
		return FileDatabase.getInstance().getFlightManger().getItineraries(		// TODO: Check if compatiable method, called properly?
				origin, destination, departureDate);
	}
	
	// TODO: Where are the methods to sort search results?
	
	/**
	 * Returns the information of this User in String.
	 * 
	 * @return the information stored for this User.
	 */
	@Override
	public String toString(){
		return String.format(
				"%s,%s,%s,%s,%d,%s,%s",
				lastName,
				firstName,
				email,
				address,
				creditCardNumber,
				formatter.format(expiryDate)
		);
	}
}
