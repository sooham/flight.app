package backend;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * A Flight. Every Flight has a number, airline name, departure and arrival
 * date and time, origin, destination, ticket price, total number of seats and travel time.
 *
 * <p>Flight objects will be persistent, hence they implement Serializable.
 * The natural ordering for Flights will be by Date, hence implements
 * Comparable.
 */
public class Flight implements Comparable<Flight>, Serializable, Transport {

    private static final long serialVersionUID = 4362700743234104218L;

    private final SimpleDateFormat dateTimeFormatter = new
                                        SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String airline;
    private long number;
    private String origin;
    private String destination;
    private Date departureDateTime;
    private Date arrivalDateTime;
    private double price;
    private int numSeats;		// the total number of seats in the Flight
    private int numEmptySeats;		// the number of empty seats in the Flight

    /**
     * Generates a new Flight object.
     *
     * @param airline  the airline.
     * @param number  a number representing this Flight.
     * @param origin  the location where this Flight begins.
     * @param destination  the location where this Flight ends.
     * @param departureDateTime  the Date this Flight departs.
     * @param arrivalDateTime  the Date this Flight arrives.
     * @param price  the ticket price for this Flight.
     * @param numSeats  the number of seats for this Flight.
     *
     * @throws InvalidFlightException if the Flight is incorrect.
     */
    public Flight(String airline, long number, String origin, String destination,
                  Date departureDateTime, Date arrivalDateTime, double price,
                  int numSeats) throws InvalidFlightException {

        if (((arrivalDateTime.getTime() - departureDateTime.getTime()) < 0)
                || origin.equals(destination) || price < 0.0 || numSeats < 0) {
            throw new InvalidFlightException("The Flight constructor inputs " +
                    "were  invalid");
        }
        this.airline = airline;
        this.number = number;
        this.origin = origin;
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.price = price;
        this.numSeats = numSeats;
        this.numEmptySeats = numSeats;
    }

    /**
     * Returns this flight's airline.
     *
     * @return the airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     *
     * Sets this flight's airline.
     *
     * @param airline  the new airline
     */
    /*
    public void setAirline(String airline) {
        this.airline = airline;
    }
    */

    /**
     * Returns this flight's number.
     *
     * @return the flightNumber
     */
    public long getNumber() {
        return number;
    }

    /**
     * Sets this flight's number.
     *
     * @param number  the new number
     */
    /*
    public void setNumber(long number) {
        this.number = number;
    }
    */

    /**
     * Returns this flight's origin city.
     *
     * @return the origin city
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of this Flight. If setting to the given origin
     * makes this Flight invalid, then leaves this Flight unchanged and
     * throws an InvalidFlightException
     * @param origin  the origin to set
     * @throws InvalidFlightException if this Flight becomes Invalid
     */
    public void setOrigin(String origin) throws InvalidFlightException {
        if (origin.equals(destination)) {
            throw new InvalidFlightException(
                    "Invalid Flight created by setting Origin."
            );
        }

        this.origin = origin;
    }

    /**
     * Returns this flight's destination city.
     *
     * @return the destination city
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination of this Flight. If the new destination
     * makes this Flight invalid, then leaves this Flight unchanged and
     * throws an InvalidFlightException
     *
     * @param destination  the destination to set
     * @throws InvalidFlightException if this Flight becomes Invalid
     */
    public void setDestination(String destination)
            throws InvalidFlightException {

        if (origin.equals(destination)) {
            throw new InvalidFlightException(
                    "Invalid Flight created by setting Destination."
            );
        }

        this.destination = destination;
    }

    /**
     * Returns this flight's departing date and time.
     *
     * @return the departureDateTime
     */
    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    /**
     * Set the departure Date and time for this Flight.
     * If setting to the new departure datetime make this Flight invalid,
     * the we leave this Flight unchanged and throw an InvalidFlightException.
     *
     * @param departureDateTime  the departureDateTime to set
     * @throws InvalidFlightException if this Flight becomes invalid.
     */
    public void setDepartureDateTime(Date departureDateTime)
            throws InvalidFlightException {

        if (arrivalDateTime.getTime() < departureDateTime.getTime()) {
            throw new InvalidFlightException(
                    "Invalid Flight created by setting DepartureDateTime."
            );
        }

        this.departureDateTime = departureDateTime;
    }

    /**
     * Returns this flight's arrival date and time.
     *
     * @return the arrivalDateTime
     */
    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    /**
     * Set the arrival Date and time for this Flight.
     * If setting to the new arrival datetime creates an Invalid Flight throws
     * an InvalidFlightException.
     *
     * @param arrivalDateTime  the arrivalDateTime to set
     * @throws InvalidFlightException if this Flight becomes Invalid
     */
    public void setArrivalDateTime(Date arrivalDateTime)
            throws InvalidFlightException {
        if (arrivalDateTime.getTime() < departureDateTime.getTime()) {
            throw new InvalidFlightException(
                    "Invalid Flight created by setting ArrivalDateTime."
            );
        }

        this.arrivalDateTime = arrivalDateTime;
    }

    /**
     * Returns the ticket price for this Flight.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of this Flight. If the price is invalid leaves
     * this Flight unchanged.
     *
     * @param price  the new price of this Flight
     */
    public void setPrice(double price) {
        if (price >= 0.0) {
            this.price = price;
        }
    }

    /**
     * Returns the number of seats for this Flight.
     *
     * @return the number of seats
     */
    public int getNumSeats() {
        return numSeats;
    }

    /**
     * Sets the number of seats for this Flight. The new number of Seats
     * must be greater than or equal the number of booked seats in this Flight.
     *
     * The number of booked seats in the Flight remains unchanged.
     *
     * @param numSeats  the new number of seats of this Flight
     */
    public void setNumSeats(int numSeats) {
        int occupiedSeats = this.numSeats - numEmptySeats;
        if (numSeats >= occupiedSeats && numSeats >= 0) {
            this.numSeats = numSeats;
        }
        setNumEmptySeats(numSeats - occupiedSeats);
    }

    /**
     * Returns the number of empty seats for this Flight.
     *
     * @return the number of empty seats
     */
    public int getNumEmptySeats() {
        return numEmptySeats;
    }

    /**
     * Sets the number of empty seats for this Flight.
     * If setting to the number of empty seats removes booked users then do
     * nothing.
     *
     * @param numEmptySeats  the new number of empty seats of this Flight
     */
    public void setNumEmptySeats(int numEmptySeats) {
        if (0 <= numEmptySeats && numEmptySeats <= numSeats) {
            this.numEmptySeats = numEmptySeats;
        }
    }

    /**
     * Returns true iff this Flight has no empty seats.
     *
     * @return true iff this Flight has no empty seats.
     */
    public boolean isFull() {
        return numEmptySeats == 0;
    }

    /**
     * Books one seat in this Flight. If the Flight is full does nothing.
     */
    public void bookSeat() {
        if (!isFull()) {
            setNumEmptySeats(numEmptySeats - 1);
        }
    }

    /**
     * Returns an integer that shows relative departure time (less, equals
     * or more than) another Flight object.
     *
     * @param other  a Flight object
     * @return an integer representing the relative departure times.
     */
    @Override
    public int compareTo(Flight other) {
        return departureDateTime.compareTo(other.getDepartureDateTime());
    }

    /**
     * Compares this flight and another Object. Returns true iff other object
     * is a Flight with identical airline and flight number.
     *
     * @param object  an Object to compare.
     * @return true iff object is a Flight and has identical airline and
     * flight number.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Flight) {
            Flight f = (Flight) object;
            return airline.equals(f.airline) && number == f.number;
        }
        return false;
    }

    /**
     * Returns the total travel time of this flight in minutes.
     *
     * @return the number of minutes between departure and arrival time.
     */
    public long getDuration() {
        final int TO_MINUTE= 60000; // milliseconds
        return (arrivalDateTime.getTime() - departureDateTime.getTime()) /
                TO_MINUTE;
    }

    /**
     * Returns a String representation of this Flight. The returning format is:
     *
     * Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination,Price
     * (the departure and arrival date and time are in the format
     * YYYY-MM-DD HH:MM; the price has exactly two decimal places)
     *
     * @return a String representation of this Flight.
     */
    @Override
    public String toString() {
        return  String.format(
                "%d,%s,%s,%s,%s,%s,%.2f",
                number,
                dateTimeFormatter.format(departureDateTime),
                dateTimeFormatter.format(arrivalDateTime),
                airline,
                origin,
                destination,
                price
                );
    }
}
