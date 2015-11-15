
package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Angad Singh
 *
 */
public class FileDatabase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5414755056678568378L;
	private static ClientManager clientManger = null;
	private static ItenaryManger itineraryManger = null;

	/**
	 * 
	 */
	public FileDatabase(String dir) {
		// TODO Auto-generated constructor stub
		try{
			importFiles(dir);
		}catch (IOException i){
			System.out.println("Files not found. Creating new empty mangers. ");
			migrateFiles(dir);
		}
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
	 * @param dir the location of a directory in the system.
	 */
	public static void migrateFiles(String dir) {
		try {
			FileOutputStream fileout = new FileOutputStream(dir + "/clientManger.ser");
			FileOutputStream fileout2 = new FileOutputStream(dir + "/Itineraries.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			ObjectOutputStream out2 = new ObjectOutputStream(fileout2);
			out.writeObject(clientManger);
			out2.writeObject(itineraryManger);
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
		return clientManger;
	}

	/**
	 * @return the itineraryManger
	 */
	public static ItenaryManger getItineraryManger() {
		return itineraryManger;
	}

	public static void importFiles(String dir){
		try {
			FileInputStream filein = new FileInputStream(dir +"/clientManger.ser");
			FileInputStream filein2 = new FileInputStream(dir +"/Itineraries.ser");
			ObjectInputStream in = new ObjectInputStream(filein);
			ObjectInputStream in2 = new ObjectInputStream(filein2);
			this.clientManger = (ClientManger) in.readObject();
			this.itineraryManger = (ItineraryManger) in.readObject();
			filein.close();
		}catch (IOException i){
			i.printStackTrace();
			return; 
		}catch(ClassNotFoundException c){
			c.printStackTrace();
			return;
		}
	}

}
