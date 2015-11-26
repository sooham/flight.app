package backend;

import java.io.Serializable;

/**
 * An Exception dealing with invalid Flight objects.
 */
public class InvalidFlightException extends Exception implements Serializable {

    private static final long serialVersionUID = -4418834740486965859L;

    /**
     * Throws an InvalidFlightException.
     */
    public InvalidFlightException() {
    }

    /**
     * Throws an InvalidFlightException with given message.
     *
     * @param message  the error message
     */
    public InvalidFlightException(String message) {
        super(message);
    }
}