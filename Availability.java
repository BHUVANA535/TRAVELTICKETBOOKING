import java.util.ArrayList;
import java.util.List;

public class Availability {
    private String type;            // defining the type of availability (e.g., bus, flight)
    private String destination;     // defining the destination of the trip
    private String date;            // defining the date of the trip
    private boolean isBooked;       // defining if the booking is confirmed or not
    private double price;           // defining the price of the ticket
    private String boardingPoint;   // defining the boarding point location
    private String droppingPoint;   // defining the dropping point location
    private double ratings;         // defining the ratings for the service
    private String place;           // defining the place associated with the booking

    // to store available bookings, defining a static list
    private static List<Availability> availableBookings = new ArrayList<>();

    // defining a constructor for Availability class, initializing all properties
    public Availability(String type, String destination, String date, boolean isBooked,
                        double price, String boardingPoint, String droppingPoint, double ratings, String place) {
        this.type = type;
        this.destination = destination;
        this.date = date;
        this.isBooked = isBooked;
        this.price = price;
        this.boardingPoint = boardingPoint;
        this.droppingPoint = droppingPoint;
        this.ratings = ratings;
        this.place = place;
    }

    // defining a method for checking availability of the booking
    public boolean checkAvailability() {
        return !isBooked;  // if the booking is not yet made, returns true 
    }

    // defining a method for searching available bookings based on different basis like type, destination, and date
    public static List<Availability> searchAvailability(String type, String destination, String date) {
        List<Availability> results = new ArrayList<>();  // defining a list to hold search results
        for (Availability booking : availableBookings) {
            // checking if the type, destination, and date match the search criteria
            if (booking.getType().equalsIgnoreCase(type) &&
                booking.getDestination().equalsIgnoreCase(destination) &&
                booking.getDate().equals(date)) {
                results.add(booking);  // adding all the matching bookings to the result list
            }
        }
        return results;  // returning the list of available bookings
    }

    // overriding toString method to provide a detailed string representation of the Availability object
    @Override
    public String toString() {
        return "Type: " + type +
               ", Destination: " + destination +
               ", Date: " + date +
               ", Price: $" + price +
               ", Ratings: " + ratings + " stars" +
               (boardingPoint.isEmpty() ? "" : ", Boarding Point: " + boardingPoint) +
               (droppingPoint.isEmpty() ? "" : ", Dropping Point: " + droppingPoint);
    }

    // defining getters to access all properties
    public String getType() {
        return type;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public String getDroppingPoint() {
        return droppingPoint;
    }

    public double getRatings() {
        return ratings;
    }

    public String getPlace() {
        return place;
    }

    // defining setters to modify all properties
    public void setType(String type) {
        this.type = type;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public void setDroppingPoint(String droppingPoint) {
        this.droppingPoint = droppingPoint;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
