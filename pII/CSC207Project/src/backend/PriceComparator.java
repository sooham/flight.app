package backend;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A comparator for comparing Flights and Itinerary by price.
 * This is possible as Itinerary is a subclass of Flight objects.
 * 
 * @param T a subclass of Flight object to compare by price
 */
public class PriceComparator<T extends Flight> implements Comparator<T>, 
Serializable {

	private static final long serialVersionUID = -7067868135052897832L;

	/**
	 * Instantiates this PriceComparator.
	 */
	public PriceComparator() {}
	
	/**
	 * Returns a number corresponding to the relative ordering of o1 and o2.
	 * If o1 is less expensive than o2, returns a positive int, else if o2 
	 * is less expensive than o1 returns a negative int, otherwise returns 0.
	 * 
	 * @param o1  a subclass of Flight
	 * @param o2  a subclass of Flight
	 * @return an int corresponding to relative price of o1 and o2.
	 */
	public int compare(T o1, T o2) {
		double diff = o2.getPrice() - o1.getPrice();

		if (diff > 0) {
			return 1;
		} else if (diff < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Checks if another is equal to this PriceComparator.
	 * 
	 * @param obj  any Object
	 * @return true iff obj is a PriceComparator
	 */
	public boolean equals(Object obj) {
		// This method does not matter for the app, but needs to be implemented
		// by the contract.
		return (obj instanceof PriceComparator);
	}
}
