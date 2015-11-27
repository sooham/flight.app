package backend;

import java.io.Serializable;
import java.util.Date;

public class Admin extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1763989618297575346L;

	public Admin(String LastName,
			String firstName, String email, String address,
			int creditCardNumber, Date expiryDate, String password) {
		super(LastName, firstName, email, address, creditCardNumber,
				expiryDate, password);
		
	}
	
	/**
	 * Allows the admin to view all of the client's information in the form
	 * of a string. The information is seen the following way:
	 * LastName, firstName, email, address, creditCardNumber, expiryDate
	 * @param theClient The client that will have his information viewed
	 * @return A string containing all client information.
	 * @throws MissingUserException if the user in question does not exist.
	 * @throws InvalidUserException if the user in question is an admin
	 */
	
	public String viewClientInfo(User theClient) throws MissingUserException,
	InvalidUserException {
		String result = null;
		if (theClient == null) {
			throw new MissingUserException("This user does not exist.");
		}
		else if (theClient instanceof Admin || !(theClient instanceof Client)) {
			throw new InvalidUserException("Invalid user.");
		}
		else {
			result = 
					String.format(theClient.getLastName(), ", ",
					theClient.getFirstName(), ", ",
					theClient.getEmail(), ", ",
					theClient.getAddress(), ", ",
					String.valueOf(theClient.getCreditCardNumber()), ", ",
					String.valueOf(theClient.getExpiryDate()));
		}
		return result;
		
		}
	
	/**
	 * Allows the admin to edit a client's information by calling the client's own
	 * information edit method
	 * @param theClient The client with info to be editted
	 * @param index The information to be editted
	 * @param o The information to be placed
	 * @throws InvalidUserException if User is an admin or not an actual user
	 * @throws MissingUserException if User does not exist
	 */
	
	public void editClientInfo(User theClient, int index, Object o) throws
	InvalidUserException, MissingUserException {
		if (theClient == null) {
			throw new MissingUserException("User does not exist.");
		}
		else if (theClient instanceof Admin || !(theClient instanceof Client)) {
			throw new InvalidUserException("Invalid user.");
		}
		else {
			((Client) theClient).editInfo(index, o);
		}
	}
}
	
