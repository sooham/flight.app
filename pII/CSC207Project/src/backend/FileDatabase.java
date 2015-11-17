
package backend;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Angad Singh
 *
 */
public class FileDatabase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5414755056678568378L;
	private static ClientManager clientManager = new ClientManager();
	private static FlightManager flightManager = new FlightManager();
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * Imports the files from the given path. If the files are not found will 
	 * create new blank Manager class in the given directory. 
	 */
	public FileDatabase(String dir) {
		importFiles(dir);
	}

	/**
	 * This will contain the loop of the final program
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Takes the location of a directory and saves the ClientManger and
	 * ItineraryManger objects.
	 * 
	 * @param dir the path of a directory in the system.
	 */
	public static void migrateFiles(String dir) {
		try {
			FileOutputStream fileout = new FileOutputStream(dir + "/ClientManager.ser");
			FileOutputStream fileout2 = new FileOutputStream(dir + "/FlightManager.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			ObjectOutputStream out2 = new ObjectOutputStream(fileout2);
			out.writeObject(clientManager);
			out2.writeObject(flightManager);
			out2.close();
			out.close();
			fileout.close();
			fileout2.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * @return the clientManger
	 */
	public static ClientManager getClients() {
		return clientManager;
	}

	/**
	 * @return the itineraryManger
	 */
	public static FlightManager getItineraryManger() {
		return flightManager;
	}
	
	/**
	 * Imports the manager classes from a specific directory passed. 
	 * @param dir the path where the files are located. 
	 */

	public static void importFiles(String dir){
		try {
			FileInputStream filein = new FileInputStream(dir +"/clientManager.ser");
			FileInputStream filein2 = new FileInputStream(dir +"/FlightManager.ser");
			ObjectInputStream in = new ObjectInputStream(filein);
			ObjectInputStream in2 = new ObjectInputStream(filein2);
			clientManager = (ClientManager) in.readObject();
			flightManager = (FlightManager) in.readObject();
			filein.close();
		}catch (IOException i){
			migrateFiles(dir);
		}catch(ClassNotFoundException c){
			c.printStackTrace();
			return;
		}
	}
	
	/**
	 * Adds a user from a given file. Takes a directory including the file name. 
	 * File must be a csv file. Can be txt but must be separated with commas.  
	 * @param dir a string of the path to the csv file. 
	 */
	public static void getInfoFromFile(String dir){
		FileReader in = null;
		BufferedReader br = null;
		String cvsSplitBy = ",";
		
		try{
			in = new FileReader(dir);
			br = new BufferedReader(in);
			String[] values = br.readLine().split(cvsSplitBy);
			User newUser = new User(values[0],values[1],values[2],
					values[3],Integer.parseInt(values[4]),values[5]);
			FileDatabase.clientManager.addClient(newUser);
		} catch (IOException e) {
			System.out.println("The file was not found.");
		} catch(IndexOutOfBoundsException e){
			System.out.println("The file does not have enough arguements to make a client.");
		}
		
	}
	
	/**
	 * Adds a flight from a given file. Takes a directory including the file name. 
	 * File must be a csv file. Can be txt but must be separated with commas.  
	 * @param dir a string of the path to the csv file. 
	 */
	public static void addFlightFromFile(String dir) {
		FileReader in = null;
		BufferedReader br = null;
		String cvsSplitBy = ",";
		
		try{
			in = new FileReader(dir);
			br = new BufferedReader(in);
			String[] values = br.readLine().split(cvsSplitBy);
			Date departureTime = format.parse(values[3]);
			Date arrivalTime = format.parse(values[4]);
			Flight newFlight = new Flight(values[5], Long.parseLong(values[6]), values[0],
					values[1], arrivalTime, departureTime,Double.parseDouble(values[2]));
			flightManager.addFlight(newFlight);
		} 
		
		catch (IOException e) {
			System.out.println("The file was not found.");
		}
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("The date is not in the right format: yyyy-MM-dd HH:mm");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println("Missing flight data.");
		}
		
		}

}
