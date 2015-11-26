package backend;

public class Admin extends User {
	
	public Admin(String LastName,
			String firstName, String email, String address,
			int creditCardNumber, String expiryDate) {
		super(LastName, firstName, email, address, creditCardNumber,
				expiryDate);
	}

}
