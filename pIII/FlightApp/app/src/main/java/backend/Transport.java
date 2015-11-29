package backend;

import java.util.Date;

/**
 * An interface for classes related to paid transportation.
 * All such classes have an origin, destination, departure date time,
 * arrival date time, duration and price.
 */
public interface Transport {
    public String getOrigin();
    public String getDestination();
    public Date getDepartureDateTime();
    public Date getArrivalDateTime();
    public long getDuration();
    public double getPrice();
}
