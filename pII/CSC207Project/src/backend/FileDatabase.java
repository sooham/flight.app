
package backend;

import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The FileDataBase class. This class is a singleton which stores all 
 * User, Flight and Itinerary objects for the backend of the FlightApp 
 * and deals with autosaving the app upon detecting changes.
 * 
 * <p>Client and Admin will be stored in a UserManager, while the
 * Flights and Itinerary will be stored in a FlightManager. This choice is
 * made because Client, Admin subclass User and Flight and Itinerary subclass
 * Flight.
 */

public class FileDatabase implements Serializable {

	private static final long serialVersionUID = -5414755056678568378L;

	// the managers
	private UserManager userManager = UserManager.getInstance();
	private FlightManager flightManager = FlightManager.getInstance();

	// date time formats
	private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
												 	"yyyy-MM-dd HH:mm");
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(
												 	"yyyy-MM-dd");

	// the singleton instance
	private static FileDatabase singletonInstance;
	
	// name of serialized files
	private String flightManagerFile = "FlightManager.ser";			
	private String userManagerFile = "UserManager.ser";			
	
	// name of passwords file
	private String passwordsFile = "passwords.txt";

	/**
	 * Constructs this FileDataBase. Deserializes the manager .ser files 
	 * from the given path, if they exist and loads them to the FlightApp,
	 * otherwise this method will create new blank Manager serialized files
	 * in the given directory. 
	 * 
	 * @param dir  the path to the directory contain or to contain app
	 * persistent storage.
	 */
	private FileDatabase(String dir) {
		deserializeManagers(dir);
	}

	/**
	 * Creates the singleton instance of this class. If the singleton has
	 * already been instantiated, this method does nothing. 
	 * 
	 * @param dir  the path to the directory contain or to contain app
	 * persistent storage.
	 */
	public static void createInstance(String dir) {
		if (singletonInstance == null) {
			singletonInstance = new FileDatabase(dir);
		}
	}

	/**
	 * Returns the singleton instance of this class. If the singleton has
	 * not been created before  this method returns null.
	 * 
	 * @return the singleton FlightManager
	 */
	public static FileDatabase getInstance() {
		return singletonInstance;
	}
	
	/**
	 * Returns the UserManager for this FileDatabase.
	 * 
	 * @return the clientManger
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * Returns the FlightManager for this FileDatabase.
	 * 
	 * @return the flightManager
	 */
	public FlightManager getFlightManger() {
		return flightManager;
	}

	/**
	 * Imports the manager classes from the given directory path. If Manager
	 * serialized files do not exist, the method will populate the UserManager
	 * with Users from passwords.txt, creating the starting Users for the app
	 * then will serialize them into files.
	 * 
	 * @param dir  the path to directory where serialized files are stored.
	 */
	public void deserializeManagers(String dir) {
		try (
			FileInputStream userFile = new 
				FileInputStream(dir + userManagerFile);
			BufferedInputStream buffer = new BufferedInputStream(userFile);
			ObjectInputStream userStream = new ObjectInputStream(buffer);

			FileInputStream flightFile = new 
					FileInputStream(dir + flightManagerFile);
			BufferedInputStream buffer2 = new BufferedInputStream(flightFile);
			ObjectInputStream flightStream = new ObjectInputStream(buffer2);
			) {
			userManager = (UserManager) userStream.readObject();
			flightManager = (FlightManager) flightStream.readObject();
		} catch (FileNotFoundException e) {
			// Serialized files do not exist
			populateUserManager(dir + passwordsFile);
			serializeManagers(dir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Takes the path of a directory and serializes this FileDatabase's
	 *  UserManger and FlightManager objects. This function is used for
	 *  autosaving when changes are made to the FlightApp at runtime.
	 * 
	 * @param dir  the path of a directory in the system.
	 */
	public void serializeManagers(String dir) {
		try (
			FileOutputStream userFile = new FileOutputStream(
					dir + userManagerFile);
			BufferedOutputStream buffer = new BufferedOutputStream(userFile);
			ObjectOutputStream userStream = new ObjectOutputStream(buffer);
			
			FileOutputStream flightFile = new FileOutputStream(
					dir + flightManagerFile);
			BufferedOutputStream buffer2 = new BufferedOutputStream(flightFile);
			ObjectOutputStream flightStream = new ObjectOutputStream(buffer2);
			) {
			userStream.writeObject(userManager);
			flightStream.writeObject(flightManager);
		} catch(Exception e) {
			// Could be FileNotFound or IOException
			e.printStackTrace();
		}
	}
	
	// TODO: Make a general, CSV file reading helper function

	/**
	 * Populates this FlightManager's UserManager with all initial Users
	 * given in passwords.txt file. 
	 * 
	 * <p>All Users created will only have email and password fields set,
	 * other billing and personal information can be added later in the app
	 * by Admin or by the User itself.
	 * 
	 * <p> The format of passwords.txt is "Email,Password,Flag" where
	 * flag is A or C for Admin or Client respectively.
	 * 
	 * @param dir  the path to the passwords.txt file
	 */
	private void populateUserManager(String dir) {
		String comma = ",";

		try (FileReader in = new FileReader(dir);
			 BufferedReader br = new BufferedReader(in)
			) {
			
			// go through the CSV file line by line
			String line;
			while((line = br.readLine()) != null) {
				String[] loginInfo = line.split(comma);

				// create a new User
				User newUser;
				if (loginInfo[2] == "A") {
					newUser = new Admin(loginInfo[0], loginInfo[1]);
				} else {
					newUser = new Client(loginInfo[0], loginInfo[1]);
				}
				
				// add the User to the UserManager
				userManager.addUser(newUser);
			}
		} catch(FileNotFoundException e) {
			System.out.println("The file " + dir + 
			" was not found, or cannot be read.");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e){
			System.out.println(
				"The CSV file given at " + dir + " is not formatted correctly."
			);
			e.printStackTrace();
		}
	}

	/**
	 * Adds or edits a Client to UserManager from a given CSV file.
	 * 
	 * <p>The CSV file has to follow the format 
	 * "LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate" per
	 * line for every Client to instantiate.
	 * 
	 * <p>If a Client in the CSV file already exists at runtime, then this
	 * method will overwrite that Client's information.
	 * 
	 * @param dir  the path to the CSV file. 
	 */
	public void addClientFromFile(String dir) {
		String comma = ",";

		try (FileReader in = new FileReader(dir);
			 BufferedReader br = new BufferedReader(in)
			) {
			
			// go through the CSV file line by line
			String line;
			while((line = br.readLine()) != null) {
				// create a new Client
				String[] values = line.split(comma);
				Client newClient = new Client(values[0],
										values[1],
										values[2],
										values[3],
										Integer.parseInt(values[4]), 
										dateFormatter.parse(values[5])
										);
				
				// check if this Client already exists in UserManager
				// if so, edit the client, otherwise just add
				userManager.addUser(newClient);
			}
		} catch(FileNotFoundException e) {
			System.out.println("The file " + dir + 
			" was not found, or cannot be read.");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e){
			System.out.println(
				"The CSV file given at " + dir + " is not formatted correctly."
			);
			e.printStackTrace();
		} catch(ParseException e) {
			System.out.println(
				"The CSV file given at " + dir + " is not formatted correctly."
			);
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds or edits a Flight to FlightManager from a given CSV file. 
	 * 
	 * <p>The CSV file has to follow the format 
	 * "Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination,
	 * Price,NumSeats" per line for every Flight to instantiate.
	 * 
	 * @param dir  the path to the CSV file. 
	 */
	public void addFlightFromFile(String dir) {
		String comma = ",";

		try (FileReader in = new FileReader(dir);
			 BufferedReader br = new BufferedReader(in)
			) {
			
			// go through the CSV file line by line
			String line;
			while((line = br.readLine()) != null) {
				//Create a new Flight object. 
				String[] values = line.split(comma);
				
				try {
				Flight newFlight = new Flight(
											values[3],
											Long.parseLong(values[0]),
											values[4],
											values[5],
											dateTimeFormatter.parse(values[1]),
											dateTimeFormatter.parse(values[2]),
											Double.parseDouble(values[6]),
											Integer.parseInt(values[7])
											);
				// add or edit the Flight information in FlightManager
				flightManager.update(newFlight);
				} catch(InvalidFlightException e) {
					System.out.println("A Flight in the CSV was invalid.");
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("The file " + dir + 
			" was not found, or cannot be read.");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e){
			System.out.println(
				"The CSV file given at " + dir + " is not formatted correctly."
			);
			e.printStackTrace();
		} catch(ParseException e) {
			System.out.println(
				"The CSV file given at " + dir + " is not formatted correctly."
			);
			e.printStackTrace();
		}
	}
}
