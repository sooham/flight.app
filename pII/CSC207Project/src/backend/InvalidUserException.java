package backend;

import java.io.Serializable;

/**
 * An Exception raised when a user attempts to call a method not
 * designated to them.
 */
public class InvalidUserException extends Exception 
implements Serializable {
	
	private static final long serialVersionUID = 7739349043796182316L;

	/**
	 * Throws an InvalidSuerException.
	 */
	public InvalidUserException() {
		super();
	}

	/**
	 * Throws an InvalidUserException with given message.
	 * 
	 * @param message  the error message
	 */
	public InvalidUserException(String message) {
		super(message);
	}

}
