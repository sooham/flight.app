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

}
