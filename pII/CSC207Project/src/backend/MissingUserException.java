package backend;

import java.io.Serializable;

public class MissingUserException extends Exception implements Serializable {

	/**
	 * Throws an exception if a User does not exist.
	 */
	private static final long serialVersionUID = -5261561875379108146L;
	
	public MissingUserException() {
		super();
	}

	/**
	 * Throws an InvalidUserException with given message.
	 * 
	 * @param message  the error message
	 */
	public MissingUserException(String message) {
		super(message);
	}

}