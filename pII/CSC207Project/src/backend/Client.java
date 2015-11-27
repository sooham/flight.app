package backend;

import java.io.Serializable;
import java.util.Date;

/**
 * A Client object. Every Client has a first name, last name, email,
 * address, credit card number and its related expiry date. 
 * The expiry date is in the format 'YYYY-MM-DD'
 * 
 * <p>A Client can search Flight and Itinerary, book Itineraries,
 * show results sorted by price or total travel time, view and edit its
 * own information.
 */
public class Client extends User implements Serializable {
	
	private static final long serialVersionUID = 7643440842230713877L;

	/**
	 * Creates a new Client instance with the given fields. Takes a last name, 
	 * first name, email, address, credit card number, its expiry date and
	 * password and creates the corresponding Client.
	 *  
	 * @param lastName  this client's last name. 
	 * @param firstName  this client's first name. 
	 * @param email  this client's email. 
	 * @param address  this client's address. 
	 * @param creditCardNumber  this client's credit card number. 
	 * @param expiryDate  a Date indicating this client's credit card 
	 * 															expiry date.
	 * @param password  the password for this client.
	 */
	public Client(String lastName, String firstName, String email, 
	String address, int creditCardNumber, Date expiryDate, String password) {
		super(lastName, firstName, email, address, creditCardNumber,
				expiryDate, password);
	}
	
	/**
	 * Creates a new Client instance with the given fields. Takes a last name, 
	 * first name, email, address, credit card number, its expiry date and 
	 * creates the corresponding Client.
	 *  
	 * @param lastName  this client's last name. 
	 * @param firstName  this client's first name. 
	 * @param email  this client's email. 
	 * @param address  this client's address. 
	 * @param creditCardNumber  this client's credit card number. 
	 * @param expiryDate  a Date indicating this client's credit card 
	 * 															expiry date.
	 * @param password  the password for this client.
	 */
	public Client(String lastName, String firstName, String email, 
	String address, int creditCardNumber, Date expiryDate) {
		super(lastName, firstName, email, address, creditCardNumber,
				expiryDate);
	}
}
