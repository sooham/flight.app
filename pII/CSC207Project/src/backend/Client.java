package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Client extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3786727358644943990L;
	private String LastName;
	private String FirstNames;
	private String Email;
	private String Address;
	private int CreditCardNumber;
	private Calendar ExpiryDate;
	private Itinerary selectedItinerary;
	private ArrayList<Itinerary> bookedItineraries;

	public Client(String username, String password, String LastName,
			String FirstNames, String Email, String Address,
			int CreditCardNumber, Calendar ExpiryDate) {
		
		super(username, password);
		this.LastName = LastName;
		this.FirstNames = FirstNames;
		this.Email = Email;
		this.Address = Address;
		this.CreditCardNumber = CreditCardNumber;
		this.ExpiryDate = ExpiryDate;
		this.selectedItinerary = new Itinerary();
		this.bookedItineraries = new ArrayList<Itinerary>();
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
	 * @return FirstNames, a String representation of the Client's first names.
	 */

	public String getFirstNames() {
		return FirstNames;
	}

	/**
	 * Setter for the Client's first names.
	 * @param firstNames, a String representation of the Client's first names.
	 */
	
	public void setFirstNames(String firstNames) {
		FirstNames = firstNames;
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

	public Calendar getExpiryDate() {
		return ExpiryDate;
	}
	
	/**
	 * Setter for the Client's credit card expiry date
	 * @param expiryDate, a Calendar (date) representation of the client's
	 * credit card expiry date.
	 */

	public void setExpiryDate(Calendar expiryDate) {
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
	
}
