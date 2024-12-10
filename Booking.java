import java.util.List;  

public abstract class Booking {
    protected String bookingID;          // unique identifier for the booking
    protected String bookingDate;        // the date when the booking was made
    protected String departureDate;      // the date when the journey is set to begin
    protected double price;              // the cost of the booking
    protected String status;             // current status of the booking (e.g., "Confirmed", "Cancelled")

    // defining a constructor to initialize all the booking details
    public Booking(String bookingID, String bookingDate, String departureDate, double price, String status) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.departureDate = departureDate;
        this.price = price;
        this.status = status;
    }

    // defining a method for canceling a booking by changing its status to "Cancelled"
    public void cancelBooking() {
        this.status = "Cancelled";  // updating the booking's status
        System.out.println("Booking " + bookingID + " has been cancelled.");  // outputting a cancellation message
    }

    // defining a method for retrieving the booking ID
    public String getBookingID() {
        return bookingID;
    }

    // defining a method for retrieving the price of the booking
    public double getPrice() {
        return price;
    }

    // defining a method for retrieving the booking date
    public String getBookingDate() {
        return bookingDate;
    }

    // defining a method for retrieving the departure date
    public String getDepartureDate() {
        return departureDate;
    }

    // defining a method for retrieving the current status of the booking
    public String getStatus() {
        return status;
    }

    // defining a method for updating the status of the booking
    public void setStatus(String status) {
        this.status = status;
    }

    // defining an abstract method for searching availability, to be implemented by subclasses
    public abstract List<Availability> searchAvailability(String type, String destination, String date);
}
