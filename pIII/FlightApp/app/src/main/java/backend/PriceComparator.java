package backend;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A comparator for comparing Flights and Itinerary by price.
 * This is possible as Itinerary is a subclass of Flight objects.
 *
 */
public class PriceComparator<T extends Transport> implements Comparator<T>,
        Serializable {

    private static final long serialVersionUID = -7067868135052897832L;

    /**
     * Instantiates this PriceComparator.
     */
    public PriceComparator() {}

    /**
     * Compares its two arguments for order.
     * Returns a negative integer, zero, or a positive integer as
     * o1 is less expensive than, equal to, or more expensive than o2.
     *
     * @param o1  a Transport.
     * @param o2  a Transport.
     * @return an int corresponding to relative price of o1 and o2.
     */
    public int compare(T o1, T o2) {
        double diff = o1.getPrice() - o2.getPrice();

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
