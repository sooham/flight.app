package backend;

import java.util.Comparator;
import java.io.Serializable;

/**
 * A comparator for comparing Flights and Itinerary by duration.
 * This is possible as Itinerary is a subclass of Flight objects.
 *
 */
public class DurationComparator<T extends Transport> implements Comparator<T>,
        Serializable {

    private static final long serialVersionUID = 7448765617847258913L;

    /**
     * Instantiates this DurationComparator.
     */
    public DurationComparator() {}

    /**
     * Compares its two arguments for order.
     * Returns a negative integer, zero, or a positive integer as
     * o1 is shorter than, equal to, or longer than o2.
     *
     * @param o1  a Transport.
     * @param o2  a Transport.
     * @return an int corresponding to relative travel time of o1 and o2.
     */
    public int compare(T o1, T o2) {
        double diff = o1.getDuration() - o2.getDuration();

        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Checks if another is equal to this DurationComparator.
     *
     * @param obj  any Object
     * @return true iff obj is a DurationComparator
     */
    public boolean equals(Object obj) {
        // This method does not matter for the app, but needs to be implemented
        // by the contract.
        return (obj instanceof DurationComparator);
    }
}
