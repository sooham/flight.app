package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * A UserManager object. A UserManager is a singleton object used to
 * handle User objects.
 * 
 * <p>This class provides methods to search and view stored User information,
 * create new Users etc.
 */
public class UserManager implements Serializable{
	
	private static final long serialVersionUID = 1772117296747939656L;

	public static List<User> users; // The List of Users in the manager
	
	// Singleton Instance
	private static UserManager singletonInstance;
	
	
	/**
	 * Constructs an empty UserManager.
	 */
	private UserManager(){
		users = new ArrayList<User>();
	}
	
	/**
	 * Returns the singleton instance of this class. If the singleton has
	 * not been instantiated, this method constructs the singleton and
	 * returns it.
	 * 
	 * @return the singleton UserManager.
	 */
	public static UserManager getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new UserManager();
		}
		return singletonInstance;
	}

	/**
	 * Adds a User to this UserManager. 
	 * 
	 * @param u  User that is to be added
	 */
	public void addUser(User u){
		users.add(u);
	}
	
	/**
	 * Returns a List of User matching the given field from this
	 * UserManager.
	 * 
	 * <p> Given a string, this method searches every User for a matching
	 * field (starting with the given string) and returns all those users.
	 * 
	 * This allows UserManager to easily search User by address, email or
	 * name while typing in, helpful for Android Client searching 
	 * functionality.
	 * 
	 * @param value  a partially complete (or complete) String that could
	 * 	be a first or last name, email or address.
	 * @return an List of User that meets search criterion.
	 */
	public List<User> getUser(String value) {
		List<User> matchingUsers = new ArrayList<User>();

		for (User u: users) {
			// go through every field of user and see if any field
			// starts with the given value.
			if (u.getFirstName().startsWith(value) ||
				u.getLastName().startsWith(value) ||
				u.getEmail().startsWith(value) ||
				u.getAddress().startsWith(value)) {
				matchingUsers.add(u);
			}
		}

		return matchingUsers;
	}
	
	/**
	 * Returns a List of User based on the credit card number						TODO: Do we really need this?
	 * and its expiry date from this UsertManager.
	 * 
	 * @param creditCardNumber  the credit card number to search for
	 * @param expiryDate  the expiration date to search for
	 * @return a List of User that meets search criterion. 
	 */
	public List<User> getUser(int creditCardNumber, Date expiryDate) {
		List<User> matchingUsers = new ArrayList<User>();

		for (User u: users) {
			if (u.getCreditCardNumber() == creditCardNumber &&
				u.getExpiryDate() == expiryDate) {
				matchingUsers.add(u);
			}
		}

		return matchingUsers;
	}

	/**
	 * Returns the index of the User in this UserManager. If the User is		// TODO: Do we need this?
	 * not there returns -1. 
	 * 
	 * @param user  a User object. 
	 * @return returns index representing the User in this UserManager.
	 * If the User does not exist returns -1.
	 */
	public int getUserIndex(User user) {
		return users.indexOf(user);
	}
	
	/**
	 * Returns the index of the User with given fields in this			TODO: Do we need this?
	 * UserManager. If the User is not there returns -1. 
	 * 
	 * @param lastName  the last name of the user.
	 * @param firstName  the first name of the user.
	 * @param email  the unique email address for the user. 
	 * @param address  the billing address for the user.
	 * @param creditCardNum  the credit card number for the user.
	 * @param expiryDate  the expiry-date of the credit card number. 
	 * @return the index representing the user in this UserManager. If User
	 * does not exist return -1. 
	 */
	public int getUserIndex(String lastName, String firstName, String email,
			String address, int creditCardNum, Date expiryDate){
		User user = new User(
			lastName, firstName, email, address, creditCardNum, expiryDate
			);
		return getUserIndex(user);	
	}
	

	/**
	 * Returns the List of User from this UserManager.
	 * 
	 * @return the users
	 */
	public List<User> getClients() {
		return users;
	}
	
}
