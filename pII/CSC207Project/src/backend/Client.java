package backend;

import java.io.Serializable;

public class Client extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7643440842230713877L;

	public Client(String LastName,
			String firstName, String email, String address,
			int creditCardNumber, String expiryDate) {
		super(LastName, firstName, email, address, creditCardNumber,
				expiryDate);
	}
	
	@Override
	public void addFlightFromFile(String dir) throws InvalidUserException{
		throw new InvalidUserException("Access denied, User must be Admin to" +
	" perform this action.");
	}
	
	
	

}
