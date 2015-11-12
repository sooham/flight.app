package backend;

import java.io.Serializable;
import java.util.Calendar;

public class Client extends User implements Serializable {

	private String LastName;
	private String FirstNames;
	private String Email;
	private String Address;
	private int CreditCardNumber;
	private Calendar ExpiryDate;

	public Client(String LastName, String FirstNames, String Email,
			String Address, int CreditCardNumber, Calendar ExpiryDate) {
		this.LastName = LastName;
		this.FirstNames = FirstNames;
		this.Email = Email;
		this.Address = Address;
		this.CreditCardNumber = CreditCardNumber;
		this.ExpiryDate = ExpiryDate;
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
	
	
	
	
	
}
