package backend;

/**
 * An exception raised when a invalid flight is attempted to be added 
 * to an itenary.  
 *
 */
public class InvalidFlightException extends  Exception {

	public InvalidFlightException() {
		super();
	}

	public InvalidFlightException(String message) {
		super(message);
	}

}
