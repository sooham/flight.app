package backend;

import java.io.Serializable;

public class Admin extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1763989618297575346L;

	public Admin(String LastName,
			String firstName, String email, String address,
			int creditCardNumber, String expiryDate) {
		super(LastName, firstName, email, address, creditCardNumber,
				expiryDate);
	}
	
	public String viewClientInfo(User theClient) throws MissingUserException,
	InvalidUserException {
		String result = null;
		if (theClient == null) {
			throw new MissingUserException("This user does not exist.");
		}
		else if (theClient instanceof Admin) {
			throw new InvalidUserException("Cannot view another admin's info.");
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

}
	
