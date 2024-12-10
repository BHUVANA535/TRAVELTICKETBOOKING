import java.util.ArrayList;
import java.util.List;

public class BusBooking extends Booking {
    private String busNumber;          // bus number for the booking
    private String busType;            // type of the bus
    private String boardingPoint;      // point where the bus will pick up passengers
    private String droppingPoint;      // point where the bus will drop off passengers

    // defining a constructor to initialize all the bus booking details
    public BusBooking(String bookingID, String bookingDate, String departureDate, double price, String status,
                      String busNumber, String busType, String boardingPoint, String droppingPoint) {
        super(bookingID, bookingDate, departureDate, price, status);  // calling the parent class constructor
        //initializing each of them
        this.busNumber = busNumber;  
        this.busType = busType;     
        this.boardingPoint = boardingPoint;  
        this.droppingPoint = droppingPoint; 
    }

    // defining a method for searching available buses based on type, destination, and date
    @Override
    public List<Availability> searchAvailability(String type, String destination, String date) {
        List<Availability> availableBuses = new ArrayList<>();  // this is a list to store available buses

        // defining a check if the bus type matches and the destination matches the dropping point
        if (type.equalsIgnoreCase("Bus") && destination.equals(droppingPoint)) {
            // adding an available bus to the list
            availableBuses.add(new Availability("Bus", destination, date, false, 50.0, boardingPoint, droppingPoint, 4.5, "No additional info"));
        }

        return availableBuses;  // returning the list of available buses
    }

    // defining a method for canceling a bus booking by calling the cancelBooking method from the parent class
    public void cancelBusBooking() {
        System.out.println("Bus booking for bus " + busNumber + " has been cancelled.");  
        this.cancelBooking();  // calling the parent class method to update the status
    }

    // defining a method for getting the bus number
    public String getBusNumber() {
        return busNumber;  // returning the bus number
    }

    // defining a method for setting the bus number
    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;  // setting the bus number
    }

    // defining a method for getting the bus type
    public String getBusType() {
        return busType;  // returning the bus type
    }

    // defining a method for setting the bus type
    public void setBusType(String busType) {
        this.busType = busType;  // setting the bus type
    }

    // defining a method for getting the boarding point
    public String getBoardingPoint() {
        return boardingPoint;  // returning the boarding point
    }

    // defining a method for setting the boarding point
    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;  // setting the boarding point
    }

    // defining a method for getting the dropping point
    public String getDroppingPoint() {
        return droppingPoint;  // returning the dropping point
    }

    // defining a method for setting the dropping point
    public void setDroppingPoint(String droppingPoint) {
        this.droppingPoint = droppingPoint;  // setting the dropping point
    }
}
