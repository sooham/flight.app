package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Christopher Cannistraro
 *
 */
public class ClientManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<User> clients;
	
	/**
	 * Creates a new ClientManager object
	 */
	public ClientManager(){
		this.clients = new ArrayList<User>();
	}
	
	/**
	 * Adds a client to the ClientManager 
	 * @param c,  a client that is added into ClientManager
	 */
	public void addClient(Client c){
		this.clients.add(c);
	}
	
	/**
	 * Returns a ArrayList of Clients based on a searchItem 
	 * that is of a certain type
	 * @param searchItem, a string with the item to search for
	 * @param type, the type of object that the string is representing
	 * @return specificClients, an ArrayList of Clients that fits the 
	 * search item
	 */
	public ArrayList<User> getClient(String searchItem, String type){
		ArrayList<User> specificClients = new ArrayList<User>();
		for (User c: this.clients){
			if (type == "last name"){
				String lastname = c.getLastName();
				if (lastname == searchItem){
					specificClients.add(c);
				}
			}
			else if (type == "first name"){
				String firstname = c.getFirstName();
				if (firstname == searchItem){
					specificClients.add(c);
				}
			}
            else if (type == "email"){
            	String email = c.getEmail();
				if (email == searchItem){
					specificClients.add(c);
				}
				
			}
            else if (type == "address"){
            	String address = c.getAddress();
				if (address == searchItem){
					specificClients.add(c);
				}
			}
		}
		return specificClients;
	}
	
	/**
	 * Returns a ArrayList of Clients based on the CreditCardNumber
	 * and ExpiryDate
	 * @param CreditCardNumber, the credit card number to search for
	 * @param ExpiryDate, the expiration date to search for
	 * @return specificClients, an ArrayList of Clients that fits the 
	 * CreditCardNumber and ExpiryDate
	 */
	public ArrayList<User> getClient(int CreditCardNumber,
			Date ExpiryDate){
		ArrayList<User> specificClients = new ArrayList<User>();
		for (User c: this.clients){
			int creditcardnum = c.getCreditCardNumber();
			Date expdate = c.getExpiryDate();
			if (creditcardnum == CreditCardNumber && expdate == ExpiryDate){
				specificClients.add(c);
			}
		}
		return specificClients;
	}
	
	/**
	 * Returns the index of the user in the the client manager. If the client is not there 
	 * returns -1. 
	 * @param lastName a string of the last name of the user.
	 * @param firstName a string of the first name of the user.
	 * @param email a string of the unique email address for the user. 
	 * @param address a string of the billing address for the user.
	 * @param creditCardNum a credit card number. 
	 * @param expiryDate the expiry date of the credit card number. 
	 * @return returns index representing the client in the clientManager. if the client is 
	 * not there returns -1
	 */
	public int getClientIndex(String lastName, String firstName, String email, String address,
			int creditCardNum, String expiryDate){
		User client = new User(lastName, firstName, email, address, creditCardNum, expiryDate);
		return getClientIndex(client);	
	}
	
	/**
	 * Returns the index of the user in the the client manager. If the client is not there 
	 * returns -1. 
	 * @param client a client object. 
	 * @return returns index representing the client in the clientManager. if the client is 
	 * not there returns -1
	 */
	public int getClientIndex(User client){
		return this.clients.indexOf(client);
	}

	/**
	 * @return the clients
	 */
	public ArrayList<User> getClients() {
		return clients;
	}
	
}
