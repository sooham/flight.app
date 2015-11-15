package backend;

import java.io.Serializable;

public class Admin extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2379924511936602176L;
	String username;
	String password;
	
	public Admin(String username, String password) {
		super(username, password);
	}
	
	public void editUserInfo() {
		
	}
	
	public String viewClientInfo() {
		return "Insert client info code here.";
	}
	
	public void addFlight(Flight toAdd) {
		
	}
	
	public void addFlightFromFile() {
		
	}
	
}
