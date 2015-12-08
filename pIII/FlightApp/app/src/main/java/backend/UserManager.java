package backend;


import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A UserManager object. A UserManager is a singleton object used to
 * handle User objects.
 *
 * <p>This class provides methods to search and view stored User information,
 * create new Users etc.
 */
public class UserManager implements Serializable {

    private final long serialVersionUID = 1772117296747939656L;

    public ArrayList<User> users; // The List of Users in the manager TODO: make
    // private

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
     * TODO: Unit test
     */
    public void addUser(User u) {
        if (users.contains(u)) {
            // could have different contact information but is still equals()
            users.remove(u);
        }
        users.add(u);
    }

    /**
     * Returns true if and only if a User with given login credentials
     * exists in this UserManager. To get the User with given credentials
     * use UserManager.getUserWithEmail().
     *
     * @return true if a User exists in this UserManager with given login
     * information
     */
    public boolean loginCredentialsCorrect(String email, String password) {
        if (getUserWithEmail(email) != null) {
            return getUserWithEmail(email).getPassword().equals(password);
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
     * Returns the ArrayList of users from this UserManager.
     *
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}
