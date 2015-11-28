package backend;

import java.util.Comparator;
import java.io.Serializable;

/**
 * A comparator for comparing Flights and Itinerary by duration.
 * This is possible as Itinerary is a subclass of Flight objects.
 *
 * @param T a subclass of Flight object to compare by duration
 */
public class DurationComparator<T extends Flight> implements Comparator<T>,
        Serializable {

    private static final long serialVersionUID = 7448765617847258913L;

    /**
     * Instantiates this DurationComparator.
     */
    public DurationComparator() {}

    /**
     * Returns a number corresponding to the relative ordering of o1 and o2.
     * If o1 is shorter than o2, returns a positive int, else if o2
     * is shorter than o1 returns a negative int, otherwise returns 0.
     *
     * @param o1  a subclass of Flight
     * @param o2  a subclass of Flight
     * @return an int corresponding to relative duration of o1 and o2.
     */
    public int compare(T o1, T o2) {
        double diff = o2.getDuration() - o1.getDuration();

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