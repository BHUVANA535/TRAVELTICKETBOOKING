import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notification {
    private String notificationID;
    private String userID;
    private String message;
    private String sentDate;

    
    public Notification(String notificationID, String userID, String message) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.message = message;
        this.sentDate = new Date().toString();  // setting the sent date to the current date and time
    }

    
    public void sendNotification() {
        // for sending notification to the user
        System.out.println("Notification sent to User " + userID + ": " + message);
    }

    
    public static List<Notification> getNotifications() {
        List<Notification> notifications = new ArrayList<>();
      
        // adding notifications to the list
        notifications.add(new Notification("N001", "U001", "Your booking is confirmed!"));
        notifications.add(new Notification("N002", "U002", "Your payment was successful."));
        return notifications;  // returning the list of notifications
    }

    //getters are used to access data and setters are used to modify them
    // getter for notificationID
    public String getNotificationID() {
        return notificationID;
    }

    // setter for notificationID
    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    // getter for userID
    public String getUserID() {
        return userID;
    }

    // setter for userID
    public void setUserID(String userID) {
        this.userID = userID;
    }

    // getter for message
    public String getMessage() {
        return message;
    }

    // setter for message
    public void setMessage(String message) {
        this.message = message;
    }

    // getter for sentDate
    public String getSentDate() {
        return sentDate;
    }

    // setter for sentDate
    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }
}
