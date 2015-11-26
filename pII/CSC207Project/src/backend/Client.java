package backend;

import java.io.Serializable;
import java.util.Date;

public class Client extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7643440842230713877L;

	public Client(String LastName,
			String firstName, String email, String address,
			int creditCardNumber, Date expiryDate, String password) {
		super(LastName, firstName, email, address, creditCardNumber,
				expiryDate, password);
	}
	
	@Override
	public void addFlightFromFile(String dir) throws InvalidUserException{
		throw new InvalidUserException("Access denied, User must be Admin to" +
	" perform this action.");
	}
	
	
	

}
