import java.util.ArrayList;
import java.util.List;

public class HotelBooking extends Booking {
    private String hotelName;           // defining the name of the hotel
    private String roomType;            // defining the type of room booked (e.g., Single, Double)
    private String checkInDate;         // defining the check-in date
    private String checkOutDate;        // defining the check-out date
    private int numberOfPeople;         // defining the number of people for the booking
    private String destination;         // defining the destination of the hotel

    // constructor to initialize the HotelBooking details
    public HotelBooking(String bookingID, String bookingDate, String departureDate, double price, 
                        String status, String hotelName, String roomType, String checkInDate, 
                        String checkOutDate, int numberOfPeople, String destination) {
        super(bookingID, bookingDate, departureDate, price, status);  // calling the parent class constructor to initialize common booking details
        //initializing each of them
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfPeople = numberOfPeople;
        this.destination = destination;
    }

    // overriding the searchAvailability method to check hotel availability based on destination and date
    @Override
    public List<Availability> searchAvailability(String type, String destination, String date) {
        List<Availability> availableRooms = new ArrayList<>();  // defining a list to store available hotel rooms

        // checking if the destination matches and type is "Hotel" to proceed with the availability check
        if (this.destination.equalsIgnoreCase(destination) && type.equalsIgnoreCase("Hotel")) {
            // if available, adding the room details to the list
            availableRooms.add(new Availability(type, destination, date, true, price, roomType, 
                                                 "Cozy and clean room", 4.5, "No extra info"));
        }
        
        return availableRooms;  // returning the list of available hotel rooms
    }
}
