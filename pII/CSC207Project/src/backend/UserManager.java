package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * A UserManager object. A UserManager is a singleton object used to
 * handle User objects.
 * 
 * <p>This class provides methods to search and view stored User information,
 * create new Users etc.
 */
public class UserManager implements Serializable {
	
	private final long serialVersionUID = 1772117296747939656L;

	public List<User> users; // The List of Users in the manager
	
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
	 * Adds a User to this UserManager, if this User already exists in this
	 * UserManager, edits that User's information. 
	 * 
	 * @param u  User that is to be added or edited
	 * TODO: Rename this method to update
	 */
	public void addUser(User u) {
		User existing;
		if ((existing = getUserWithEmail(u.getEmail())) != null) {
			// then the User is being updated
			users.remove(existing);
		}
		users.add(u);
	}

	/**
	 * Returns true if and only if a User with given login credentials 
	 * exists in this UserManager. To get the User with given credentials
	 * use UserManager.getUserWithEmail.
	 * 
	 * @return true if a User exists in this UserManager with given login
	 * information
	 */
	public boolean loginCredentialsCorrect(String email, String password) {
		for (User u: users) {
			if (u.getEmail().equals(email) && 
				u.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Returns a User with the given email from this UserManager. If the User
	 * does not exist then return null.
	 * 
	 * @param email the email of the User 
	 * @return the User with the given email or null 
	 */
	public User getUserWithEmail(String email) {
		for (User u: users) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}

		return null;
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
	 * Returns the List of User from this UserManager.
	 * 
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	
	/**
	 * Called by ObjectInputStream when reading UserManager class object
	 * from stream. The readResolve method needs to be defined to prevent
	 * Deserialization of UserManager class resulting in multiple instances
	 * of UserManager being created.
	 * 
	 * @return the singleton instance for UserManager class
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return singletonInstance;
	}
	
}
