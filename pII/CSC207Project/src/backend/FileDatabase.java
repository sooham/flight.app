
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
	private static ClientManager clients = null;
	private static ItenaryManger itenaryManger = null;

	/**
	 * 
	 */
	public FileDatabase() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Takes the location of a directory and saves the ClientManger and
	 * IernaryManger objects.
	 * 
	 * @param dir the location of a directory in the system.
	 */
	public void migrateFiles(String dir) {
		try {
			FileOutputStream fileout = new FileOutputStream(dir + "/clients.ser");
			FileOutputStream fileout2 = new FileOutputStream(dir + "/Itenaries.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			ObjectOutputStream out2 = new ObjectOutputStream(fileout2);
			out.writeObject(clients);
			out2.writeObject(itenaryManger);
			out2.close();
			out.close();
			fileout.close();
			fileout2.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public void importFiles(String dir){
		try {
			FileInputStream filein = new FileInputStream(dir +"/clients.ser");
			FileInputStream filein2 = new FileInputStream(dir +"/Itenaries.ser");
			ObjectInputStream in = new ObjectInputStream(filein);
			ObjectInputStream in2 = new ObjectInputStream(filein2);
			this.clients = (ClientManger) in.readObject();
			this.itenaryManger = (ItenaryManger) in.readObject();
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
