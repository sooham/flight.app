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
	
	/**
	 * This method is unavailable to clients and is meant to be Admin only.
	 */
	
	@Override
	public void addFlightFromFile(String dir) {}
	
	/**
	 * This method is unavailable to clients and is meant to be Admin only.
	 */
	
	@Override
	public void addUserFromFile(String dir) {}
	
	/**
	 * Allows the client to view all of his personal and billing information.
	 * @return Personal and billing information (String)
	 */
	
	public String viewInfo() {
		return String.format(this.getLastName(), ", ",
				this.getFirstName(), ", ",
				this.getEmail(), ", ",
				this.getAddress(), ", ",
				String.valueOf(this.getCreditCardNumber()), ", ",
				String.valueOf(this.getExpiryDate()));
	}
	
	/**
	 * Allows the client to edit his personal and billing info
	 * @param index The information to edit, going from 0 to 6,
	 * it follows the constructor order, last one being the password
	 * @param o what will the information be set, must be of the same type
	 */
	
	public void editInfo(int index, Object o) {
		if (!(index > 6) && !(index < 0)) {
			if (index == 0) {
				if (o.getClass() == this.getLastName().getClass()) {
					this.setLastName((String) o);
				}
			}
			else if (index == 1) {
				if (o.getClass() == this.getFirstName().getClass()) {
					this.setFirstName((String) o);
				}
			}
			else if (index == 2) {
				if (o.getClass() == this.getEmail().getClass()) {
					this.setEmail((String) o);
				}
			}
			else if (index == 3) {
				if (o.getClass() == this.getAddress().getClass()) {
					this.setAddress((String) o);
				}
			}
			else if (index == 4) {
				if (o instanceof Integer) {
					this.setCreditCardNumber((int) o);
				}
			}
			else if (index == 5) {
				if (o.getClass() == this.getExpiryDate().getClass()) {
					this.setExpiryDate((Date) o);
				}
			}
			else if (index == 6) {
				if (o.getClass() == this.getPassword().getClass()) {
					this.setPassword((String) o); //Add Password setter for User
				}
			}
		}
	}
	

}
