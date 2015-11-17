package backend;

import java.io.Serializable;

/**
 * An Exception raised when an invalid flight is found in Itinerary.
 */
public class InvalidItineraryException extends Exception 
implements Serializable {

	private static final long serialVersionUID = -6115284871023007506L;

	/**
	 * Throws an InvalidItineraryException.
	 */
	public InvalidItineraryException() {
		super();
	}

	/**
	 * Throws an InvalidItineraryException with given message.
	 * 
	 * @param message  the error message
	 */
	public InvalidItineraryException(String message) {
		super(message);
	}

}
