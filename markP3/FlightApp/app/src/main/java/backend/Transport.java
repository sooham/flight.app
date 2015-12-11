package backend;

import java.util.Date;

/**
 * An interface for classes related to paid transportation.
 * All such classes have an origin, destination, departure date time,
 * arrival date time, duration and price.
 */
public interface Transport {
    String getOrigin();
    String getDestination();
    Date getDepartureDateTime();
    Date getArrivalDateTime();
    long getDuration();
    double getPrice();
}
