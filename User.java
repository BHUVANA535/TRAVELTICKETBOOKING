import java.util.ArrayList;
import java.util.List;

public class User {
    //making these attributes private for confidentiality
    private String userID;
    private String userName;
    private String password;
    private String email;
    private List<Booking> bookingHistory;
    private List<Payment> paymentHistory;
    private List<Notification> notifications; 

    // defining this constructor for initializing user details and empty history lists
    public User(String userID, String userName, String password, String email) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.bookingHistory = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
        this.notifications = new ArrayList<>(); 
    }

    // rhis method is adding a notification to the user
    public void addNotification(String notificationID, String message) {
        Notification notification = new Notification(notificationID, this.userID, message);
        this.notifications.add(notification);
        notification.sendNotification();
    }

    //the below method is returning the list of notifications for the user
    public List<Notification> getNotifications() {
        return this.notifications;
    }

    // the following method is deleting the user's account and clearing all details
    public void deleteAccount() {
        System.out.println("Account " + this.userName + " has been deleted.");
        this.userID = null;
        this.userName = null;
        this.password = null;
        this.email = null;
        this.notifications.clear(); 
    }

    // defining a method for validating the user's login using the provided username and password
    public boolean login(String username, String password) {
        if (this.userName.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    // this method is registering a new user with username, password, and email
    public void register(String username, String password, String email) {
        this.userName = username;
        this.password = password;
        this.email = email;
    }

    // this is a method for resetting the password if the provided email matches the registered email
    public void resetPassword(String email) {
        if (this.email.equals(email)) {
            System.out.println("Password reset link sent to " + email);
        } else {
            System.out.println("Email not registered.");
        }
    }

    // method is adding a booking to the user's booking history
    public void addBookingToHistory(Booking booking) {
        this.bookingHistory.add(booking);
    }

    // method is returning the user's booking history
    public List<Booking> viewBookingHistory() {
        return this.bookingHistory;
    }

    // method is canceling a booking with the given bookingID
    public void cancelBooking(String bookingID) {
        for (Booking booking : bookingHistory) {
            if (booking.getBookingID().equals(bookingID)) {
                bookingHistory.remove(booking);
                System.out.println("Booking " + bookingID + " has been canceled.");
                return;
            }
        }
        System.out.println("Booking not found.");
    }

    // method is creating and printing a notification message for the user
    public void createNotification(String message) {
        System.out.println("Notification for " + this.userName + ": " + message);
    }

    // method is adding a payment record to the user's payment history
    public void addPaymentHistory(Payment payment) {
        this.paymentHistory.add(payment);
    }

    // method is returning the user's payment history
    public List<Payment> getPaymentHistory() {
        return this.paymentHistory;
    }

    // method is returning the user's userID
    public String getUserID() {
        return userID;
    }

    // method is returning the user's userName
    public String getUserName() {
        return userName;
    }

    // method is returning the user's email
    public String getEmail() {
        return email;
    }

    // method is returning the user's booking history
    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }
}
