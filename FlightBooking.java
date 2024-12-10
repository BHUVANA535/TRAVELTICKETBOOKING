import java.util.ArrayList;
import java.util.List;

public class FlightBooking extends Booking {
    //we're setting them as private as they are sensitive info
    private String flightNumber;  // defining the flight number for the booking
    private String seatNumber;    // defining the seat number selected for the flight
    private String classType;     // defining the class type (e.g., Economy, Business, First Class)
    private String departureAirport;  // defining the departure airport for the flight
    private String arrivalAirport;    // defining the arrival airport for the flight
    private String boardingPoint;    // defining the boarding point for the flight
    private String droppingPoint;    // defining the dropping point for the flight

    // Constructor defining the FlightBooking details
    public FlightBooking(String bookingID, String bookingDate, String departureDate, double price, String flightNumber, 
                         String departureAirport, String arrivalAirport, String boardingPoint, 
                         String droppingPoint, String status) {
        super(bookingID, bookingDate, departureDate, price, status);  // calling the parent class constructor to initialize booking details
        //iitializing each of them
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.boardingPoint = boardingPoint;
        this.droppingPoint = droppingPoint;
    }

    // defining a method to define the seat selection process along with the class type
    public void selectSeat(String seatNumber, String classType) {
        this.seatNumber = seatNumber;  // setting the seat number
        this.classType = classType;    // setting the class type (e.g., Economy)
        System.out.println("Seat selected: " + seatNumber + " in " + classType + " class.");
    }

    // here , we are overriding the searchAvailability method to search for available flights
    @Override
    public List<Availability> searchAvailability(String type, String destination, String date) {
        List<Availability> availableFlights = new ArrayList<>();  // defining a list to store available flights

        // Checking if the type is 'Flight' and the destination matches the arrival airport
        if (type.equalsIgnoreCase("Flight") && destination.equals(arrivalAirport)) {
            // If available, adding the flight details to the list
            availableFlights.add(new Availability("Flight", destination, date, false, 500.0, boardingPoint, droppingPoint, 4.0, "Flight Available"));
        }
        return availableFlights;  // returning the list of available flights
    }

    // defiing a method to cancel the flight booking
    public void cancelFlightBooking() {
        System.out.println("Flight booking for flight " + flightNumber + " has been canceled.");
        this.setStatus("Cancelled");  // updating the booking status to "Cancelled"
    }

    // these are the getter and setter methods for all the attributes of the flight booking

    public String getFlightNumber() {
        return flightNumber;  // defining the method to get the flight number
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;  // defining the method to set the flight number
    }

    public String getSeatNumber() {
        return seatNumber;  // defining the method to get the seat number
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;  // defining the method to set the seat number
    }

    public String getClassType() {
        return classType;  // defining the method to get the class type
    }

    public void setClassType(String classType) {
        this.classType = classType;  // defining the method to set the class type
    }

    public String getDepartureAirport() {
        return departureAirport;  // defining the method to get the departure airport
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;  // defining the method to set the departure airport
    }

    public String getArrivalAirport() {
        return arrivalAirport;  // defining the method to get the arrival airport
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;  // defining the method to set the arrival airport
    }

    public String getBoardingPoint() {
        return boardingPoint;  // defining the method to get the boarding point
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;  // defining the method to set the boarding point
    }

    public String getDroppingPoint() {
        return droppingPoint;  // defining the method to get the dropping point
    }

    public void setDroppingPoint(String droppingPoint) {
        this.droppingPoint = droppingPoint;  // defining the method to set the dropping point
    }
}
