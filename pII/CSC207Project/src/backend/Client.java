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
	private Itenary selectedItinerary;
	private ArrayList<Itenary> bookedItineraries;

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
		this.selectedItinerary = new Itenary();
		this.bookedItineraries = new ArrayList<Itenary>();
	}
	
	/**
	 * 
	 * @return
	 */

	public String getLastName() {
		return LastName;
	}
	
	/**
	 * 
	 * @param lastName
	 */

	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	/**
	 * 
	 * @return
	 */

	public String getFirstNames() {
		return FirstNames;
	}

	/**
	 * 
	 * @param firstNames
	 */
	
	public void setFirstNames(String firstNames) {
		FirstNames = firstNames;
	}
	
	/**
	 * 
	 * @return
	 */

	public String getEmail() {
		return Email;
	}
	
	/**
	 * 
	 * @param email
	 */

	public void setEmail(String email) {
		Email = email;
	}
	
	/**
	 * 
	 * @return
	 */

	public String getAddress() {
		return Address;
	}
	
	/**
	 * 
	 * @param address
	 */

	public void setAddress(String address) {
		Address = address;
	}
	
	/**
	 * 
	 * @return
	 */

	public int getCreditCardNumber() {
		return CreditCardNumber;
	}
	
	/**
	 * 
	 * @param creditCardNumber
	 */

	public void setCreditCardNumber(int creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}
	
	/**
	 * 
	 * @return
	 */

	public Calendar getExpiryDate() {
		return ExpiryDate;
	}
	
	/**
	 * 
	 * @param expiryDate
	 */

	public void setExpiryDate(Calendar expiryDate) {
		ExpiryDate = expiryDate;

	}
		
	/**
	 * 
	 * @param listItineraries
	 * @param index
	 */
	
	public void selectItinerary(ArrayList<Itenary> listItineraries, int index) {
		this.selectedItinerary = listItineraries.get(index);
	}
	
	/**
	 * 
	 * @param selectedItinerary
	 */
	
	public void bookItinerary(Itenary selectedItinerary) {
		this.bookedItineraries.add(selectedItinerary);
	}
	
}
