package backend;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

/**
 * A ClientManager object. A ClientManager is a singleton object used to
 * handle Client (Not Implemented), in phase II the ClientManager works with
 * the User class.
 *
 * <p>This class provides methods to search and view stored client information.
 */
public class ClientManager implements Serializable{

    private static final long serialVersionUID = 1772117296747939656L;

    public static ArrayList<User> clients; // The ArrayList of Clients

    /**
     * Creates a new ClientManager object
     */
    public ClientManager(){
        this.clients = new ArrayList<User>();
    }

    /**
     * Adds a User to this ClientManager
     *
     * @param u  User that is to be added
     */
    public void addClient(User u){
        clients.add(u);
    }

    /**
     * Returns a ArrayList of Users based on a search of a key
     * that is of a certain value from this ClientManager.
     *
     * @param key  the string name for the item to search for.
     * @param value  the value of key being searched for.
     * @return an ArrayList of User  that meet the search criterion
     */
    public ArrayList<User> getClient(String key, String value){
        ArrayList<User> specificClients = new ArrayList<User>();
        for (User u: clients){
            if (key == "last name"){
                String lastname = u.getLastName();
                if (lastname == value){
                    specificClients.add(u);
                }
            }
            else if (key == "first name"){
                String firstname = u.getFirstName();
                if (firstname == value){
                    specificClients.add(u);
                }
            }
            else if (key == "email"){
                String email = u.getEmail();
                if (email == value){
                    specificClients.add(u);
                }
            }
            else if (key == "address"){
                String address = u.getAddress();
                if (address == value){
                    specificClients.add(u);
                }
            }
        }
        return specificClients;
    }

    /**
     * Returns a ArrayList of User based on the CreditCardNumber
     * and ExpiryDate from this ClientManager.
     *
     * @param CreditCardNumber  the credit card number to search for
     * @param ExpiryDate  the expiration date to search for
     * @return specificClients   an ArrayList of User that fits the
     * CreditCardNumber and ExpiryDate
     */
    public ArrayList<User> getClient(int CreditCardNumber,
                                     Date ExpiryDate){
        ArrayList<User> specificClients = new ArrayList<User>();
        for (User u: clients){
            int creditCardNum = u.getCreditCardNumber();
            Date expDate = u.getExpiryDate();
            if (creditCardNum == CreditCardNumber && expDate == ExpiryDate){
                specificClients.add(u);
            }
        }
        return specificClients;
    }

    /**
     * Returns the index of the User with given fields in this
     * client manager. If the User is not there returns -1.
     *
     * @param lastName  a String of the last name of the user.
     * @param firstName  a String of the first name of the user.
     * @param email  a String of the unique email address for the user.
     * @param address  a String of the billing address for the user.
     * @param creditCardNum  a credit card number.
     * @param expiryDate  the expiry-date of the credit card number.
     * @return the index representing the user in this ClientManager. If User
     * does not exist return -1.
     */
    public int getClientIndex(String lastName, String firstName, String email,
                              String address, int creditCardNum, String expiryDate){
        User user = new User(
                lastName, firstName, email, address, creditCardNum, expiryDate
        );
        return getClientIndex(user);
    }

    /**
     * Returns the index of the User in this ClientManager. If the client is
     * not there returns -1.
     *
     * @param user  a User object.
     * @return returns index representing the User in this ClientManager.
     * If the User does not exist returns -1.
     */
    public int getClientIndex(User user){
        return clients.indexOf(user);
    }

    /**
     * Returns the ArrayList of User from this ClientManager.
     *
     * @return the clients
     */
    public ArrayList<User> getClients() {
        return clients;
    }

}
