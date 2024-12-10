import java.util.ArrayList;
import java.util.List;

//defining a trainbooking class that extends bookig due to inheritance
public class TrainBooking extends Booking {
    //we're setting them as private as they are confidential information
    private String trainNumber;
    private String seatNumber;
    private String trainType;
    private String boardingPoint;
    private String droppingPoint;


    // defining constructor initializing train booking details
    public TrainBooking(String bookingID, String bookingDate, double price, String trainNumber, 
                    String trainType, String boardingPoint, String droppingPoint, String status) {
        super(bookingID, bookingDate, status, price, "AdditionalInfo");
        this.trainNumber = trainNumber;
        this.trainType = trainType;
        this.boardingPoint = boardingPoint;
        this.droppingPoint = droppingPoint;
    }


    //defining a method for selecting seat for the booking
    public void selectSeat(String seatNumber) {
        this.seatNumber = seatNumber;
        System.out.println("Seat selected: " + seatNumber);
    }

    @Override
    // this is a method for searching availability based on train type, destination, and date
    public List<Availability> searchAvailability(String type, String destination, String date) {
        List<Availability> availableTrains = new ArrayList<>();
        
        if (type.equalsIgnoreCase("Train") && destination.equals(droppingPoint)) {
            availableTrains.add(new Availability("Train", destination, date, false, 100.0, boardingPoint, droppingPoint, 4.2, "Available"));
        }
        return availableTrains;
    }

    // defining method for canceling the train booking
    public void cancelTrainBooking() {
        System.out.println("Train booking for train " + trainNumber + " has been canceled.");
        this.setStatus("Cancelled"); 
    }


    //setters and getters for modifying and accessing the data respectively
    // getter and setter for trainNumber
    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    // getter and setter for seatNumber
    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    // getter and setter for trainType
    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    // getter and setter for boardingPoint
    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    // getter and setter for droppingPoint
    public String getDroppingPoint() {
        return droppingPoint;
    }

    public void setDroppingPoint(String droppingPoint) {
        this.droppingPoint = droppingPoint;
    }
}
