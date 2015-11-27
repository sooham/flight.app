package backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * An Admin object. Every Admin has a first name, last name, email,
 * address, credit card number and its related expiry date. 
 * The expiry date is in the format 'YYYY-MM-DD'
 * 
 * <p>An Admin can search Flight and Itinerary, book Itineraries,
 * show results sorted by price or total travel time, view and edit its
 * own information.
 * 
 * <p>In addition, Admin users are granted special permissions.
 * An Admin can load Client and Flight object into the app (FileDataabase)
 * from CSV files, view and book Itinerary for Client, view and edit Client and
 * Flight information.
 */
public class Admin extends User implements Serializable {
	
	private static final long serialVersionUID = -1763989618297575346L;

	/**
	 * Creates a new Admin instance with the given fields. Takes a last name, 
	 * first name, email, address, credit card number, its expiry date and
	 * password and creates the corresponding Admin.
	 *  
	 * @param lastName  this admin's last name. 
	 * @param firstName  this admin's first name. 
	 * @param email  this admin's email. 
	 * @param address  this admin's address. 
	 * @param creditCardNumber  this admin's credit card number. 
	 * @param expiryDate  a Date indicating this admin's credit card 
	 * 															expiry date.
	 * @param password  the password for this admin.
	 */
	public Admin(String LastName, String firstName, String email,
	String address, int creditCardNumber, Date expiryDate, String password) {
		super(LastName, firstName, email, address, creditCardNumber,
				expiryDate, password);
	}
	
	/**
	 * Creates a new Admin instance with the given fields. Takes a last name, 
	 * first name, email, address, credit card number, its expiry date and
	 * creates the corresponding Admin.
	 *  
	 * @param lastName  this admin's last name. 
	 * @param firstName  this admin's first name. 
	 * @param email  this admin's email. 
	 * @param address  this admin's address. 
	 * @param creditCardNumber  this admin's credit card number. 
	 * @param expiryDate  a Date indicating this admin's credit card 
	 * 															expiry date.
	 */
	public Admin(String LastName, String firstName, String email,
	String address, int creditCardNumber, Date expiryDate) {
		super(LastName, firstName, email, address, creditCardNumber,
				expiryDate);
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
	 * Adds a Flight as an instantiated Flight into FlightManager from CSV 
	 * file. The Flight added is also serialized and stored in FileDatabase.
	 * 
	 * <p>Note that the CSV file must have a valid format of
	 * "Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination,
	 * Price,NumSeats" for Flight to be created properly, otherwise will 
	 * throw IOException.
	 * 
	 * @param dir  the path to CSV file. 
	 * @throws IOException if the CSV file is the wrong format
	 */
	public void addFlightFromFile(String dir) throws IOException {
		FileDatabase.getInstance().addFlightFromFile(dir);
	}

	/**
	 * This User adds an Itinerary to a Client's booked Itineraries.
	 * 
	 * @param client  the Client for which to book Itinerary.
	 * @param itinerary  the Itinerary to book for the client
	 */
	public void bookFor(Client client, Itinerary itinerary) {
		client.bookItinerary(itinerary);
	}
	
}