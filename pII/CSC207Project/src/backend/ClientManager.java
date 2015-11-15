package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import backend.Client;

/**
 * @author Christopher Cannistraro
 *
 */
public class ClientManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Client> clients;
	
	/**
	 * Creates a new ClientManager object
	 */
	public ClientManager(){
		this.clients = new ArrayList<Client>();
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
	public ArrayList<Client> getClient(String searchItem, String type){
		ArrayList<Client> specificClients = new ArrayList<Client>();
		for (Client c: this.clients){
			if (type == "last name"){
				String lastname = c.getLastName();
				if (lastname == searchItem){
					specificClients.add(c);
				}
			}
			else if (type == "first names"){
				String firstnames = c.getFirstNames();
				if (firstnames == searchItem){
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
	public ArrayList<Client> getClient(int CreditCardNumber,
			Calendar ExpiryDate){
		ArrayList<Client> specificClients = new ArrayList<Client>();
		for (Client c: this.clients){
			int creditcardnum = c.getCreditCardNumber();
			Calendar expdate = c.getExpiryDate();
			if (creditcardnum == CreditCardNumber && expdate == ExpiryDate){
				specificClients.add(c);
			}
		}
		return specificClients;
	}
	
	/**
	 * Edits a Client that is inside the ClientManager
	 * @param clientIndex, the index of the Client that is going to be edited
	 * @param newType, the change for the type of object altered
	 * @param type, the type of object that the string is representing
	 */
	public void editClient(int clientIndex, String newType, String type){
		Client c = this.clients.get(clientIndex);
		if (type == "last name"){
			c.setLastName(newType);
		}
		else if (type == "first names"){
			c.setFirstNames(newType);
		}
        else if (type == "email"){
        	c.setEmail(newType);
		}
        else if (type == "address"){
        	c.setAddress(newType);
        }
	}
	
	/**
	 * Edits a Client's credit card information that is inside the 
	 * ClientManager
	 * @param clientIndex, the index of the Client that is going to be edited
	 * @param newCreditCardNum, the new credit card number for the Client
	 * @param newExpDate, the new expiry date for the Client
	 */
	public void editClient(int clientIndex, int newCreditCardNum,
			Calendar newExpDate){
		Client c = this.clients.get(clientIndex);
		c.setCreditCardNumber(newCreditCardNum);
		c.setExpiryDate(newExpDate);
	}
}
