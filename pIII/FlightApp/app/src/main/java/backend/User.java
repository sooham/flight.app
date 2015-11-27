package backend;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A User object. A User is a backend representation for both Client and
 * Administrator objects Every User has a first, last name, email, address,
 * credit card number, expiry date.
 * Where the expiry date will be in the format yyyy-MM-dd.
 *
 * <p>All User will be stored and therefore are Serializable.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -3786727358644943990L;

    private String lastName;
    private String firstName;

    private String email;
    private String password;

    private String address;
    private int creditCardNumber;
    private Date expiryDate;

    private Itinerary selectedItinerary;
    private ArrayList<Itinerary> bookedItineraries;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss");

    /**
     * Takes a last name, first name, email, address, credit card number and
     *  the expiry date. Creates a new User class, with the given fields.
     *
     * @param LastName a string of the user's last name.
     * @param firstName a string of the user's first name.
     * @param email a string of the user's email.
     * @param address a string of the user's address.
     * @param creditCardNumber a integer of the user's credit cards number.
     * @param expiryDate a string in yyyy-MM-dd of the date of expiry.
     */
    public User(String LastName,
                String firstName, String email, String address,
                int creditCardNumber, String expiryDate) {
        try{
            this.lastName = LastName;
            this.firstName = firstName;
            this.email = email;
            this.address = address;
            this.creditCardNumber = creditCardNumber;
            this.expiryDate = format.parse(expiryDate);
            this.selectedItinerary = null;
            this.bookedItineraries = new ArrayList<Itinerary>();
        }catch(ParseException e) {
            System.out.println(
                    "The date is not in the right format: yyyy-MM-dd HH:mm"
            );
        }
    }

    /**
     * Adds a client to the clientManager in FileDatabase.
     * Takes client information from a file. File should be a csv file.
     *
     * @param dir the path to client file.
     */
    public void getInfoFromFile(String dir){
        //FileDatabase.getInfoFromFile(dir);
    }

    /**
     * Adds a flight to the flightManager in FileDatabase.
     * Takes flight information from a file. File should be a csv file.
     *
     * @param dir the path to flight csv file.
     */
    public void addFlightFromFile(String dir){
        //
        // FileDatabase.addFlightFromFile(dir);
    }

    /**
     * Getter for the Client's last name.
     *
     * @return LastName, a String representation of the Client's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the Client's last name.
     *
     * @param lastName, a String representation of the Client's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the Client's first names.
     *
     * @return FirstName, a String representation of the Client's first names.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the Client's first names.
     *
     * @param firstName, a String representation of the Client's first names.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the Client's email address.
     *
     * @return Email, a String representation of the Client's email
     * address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for the User's Password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the Client's email address.
     *
     * @param email, a String representation of the Client's email.
     * address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for the Client's email address.
     *
     * @return Address, a String representation of the Client's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for the Client's address.
     *
     * @param address, a String representation of the Client's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for the Client's credit card number.
     *
     * @return CreditCardNumber, an int representation of the Client's
     * credit card number.
     */
    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Setter for the Client's credit card number.
     *
     * @param creditCardNumber, an int representation of the Client's
     * credit card number.
     */
    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * Getter for the Client's credit card expiry date.
     *
     * @return ExpiryDate, a Calendar (date) representation of the client's
     * credit card expiry date.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Setter for the Client's credit card expiry date
     *
     * @param expiryDate, a Calendar (date) representation of the client's
     * credit card expiry date.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;

    }

    /**
     * Select an Itinerary from a list of Itinerary.
     *
     * @param listItineraries, an ArrayList of Itinerary
     * @param index, an int index for the ArrayList
     */
    public void selectItinerary(ArrayList<Itinerary> listItineraries,
                                int index) {
        this.selectedItinerary = listItineraries.get(index);
    }

    /**
     * Book an itinerary selected using the selectItinerary method.
     *
     * @param selectedItinerary, an Itinerary previously selected.
     */
    public void bookItinerary(Itinerary selectedItinerary) {
        this.bookedItineraries.add(selectedItinerary);
    }

    /**
     * Find a Flight given its origin, destination and departureDate
     *
     * @param origin
     * @param destination the destination of the flight.
     * @param departureDate a string representation of the date. Should be in
     *  yyyy-MM-dd HH:mm.
     *
     * @return a Flight
     */
    /*
    public Flight viewFlights(String origin, String destination,
                              String departureDate){
        Flight value = null;
        try {
            value = FileDatabase.getFlightManger().getFlights(
                    origin, destination, departureDate).get(0);
        } catch (IndexOutOfBoundsException e) {

        }

        return value;
    }
    */

    /**
     * Returns a list itineraries bases on the origin, destination and the
     * departure date.
     *
     * @param origin the origin of the required itineraries.
     * @param destination the destination of the required itineraries.
     * @param departureDate the date and time of departure. In the format
     * yyyy-MM-d HH:ss
     * @return an ArrayList contain all valid itineraries.
     */
    public ArrayList<Itinerary> viewItineraries(String origin,
                                                String destination, String departureDate){
        return new ArrayList<Itinerary>();
                //FileDatabase.getFlightManger().getItineraries(
                //origin, destination, departureDate);
    }

    /**
     * Returns the information stored for the client as a string .
     * @return the information stored for the client with the given email in
     *         this format:
     *         LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
     *         (the ExpiryDate is stored in the format YYYY-MM-DD)
     */
    @Override
    public String toString(){
        return lastName + "," + firstName+ ","+ email+"," + address + ","
                + String.valueOf(creditCardNumber) + "," +
                format.format(expiryDate);
    }
}
