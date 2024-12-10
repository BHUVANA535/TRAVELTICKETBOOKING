package travelticketbooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtils {
    //i'm makig these private as they are sensitive information, not for unauthorised access
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travelticketbooking2";  // defining the database URL
    private static final String USER = "root";  // defining the database user
    private static final String PASSWORD = "Rishi#123";  // defining the database password


    // defining a method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);  // connecting to the database with the provided credentials
    }

    // defining a method to execute an update query like INSERT, UPDATE, DELETE
    public static int executeUpdate(String query, Object[] parameters) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // defining a loop to set the parameters for the query
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);  // setting each parameter value in the query
            }
            return stmt.executeUpdate();  // executing the update query
        } catch (SQLException e) {
            e.printStackTrace();  // printing any SQL exceptions
            return 0;  // returning 0 to indicate failure
        }
    }

    // defining a method to execute a query and return a ResultSet
    public static ResultSet executeQuery(String query, Object[] parameters) {
        //using a try-catch block to handle exceptios
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // defining a loop to set the parameters for the query
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);  // setting each parameter value in the query
            }
            return stmt.executeQuery();  // executing the query and returning the result set
        } catch (SQLException e) {
            e.printStackTrace();  // printing any SQL exceptions
            return null;  // returning null in case of an error
        }
    }

    // defining a method to insert a booking into the database
    public static int insertBooking(String bookingID, String bookingDate, String departureDate, double price, String status) {
        String query = "INSERT INTO bookings (bookingID, bookingDate, departureDate, price, status) VALUES (?, ?, ?, ?, ?)";  // defining the insert query
        //using a try-catch block to handle exceptions
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // defining how to set each parameter in the query
            stmt.setString(1, bookingID);
            stmt.setString(2, bookingDate);
            stmt.setString(3, departureDate);
            stmt.setDouble(4, price);
            stmt.setString(5, status);

            // printing the query and its parameters for debugging purposes
            System.out.println("Executing query: " + query);
            System.out.println("With parameters: " + bookingID + ", " + bookingDate + ", " + departureDate + ", " + price + ", " + status);

            int rowsAffected = stmt.executeUpdate();  // executing the insert query
            if (rowsAffected > 0) {
                System.out.println("Booking inserted successfully.");  // confirming successful insertion
            } else {
                System.out.println("Booking insertion failed.");  // handling failure case
            }
            return rowsAffected;  // returning the number of affected rows
        } catch (SQLException e) {
            e.printStackTrace();  // printing any SQL exceptions
            return 0;  // returning 0 to indicate failure
        }
    }

    // defining a method to fetch booking details from the database based on bookingID
    public static ResultSet getBookingDetails(String bookingID) {
        String query = "SELECT * FROM bookings WHERE bookingID = ?";  // defining the query to fetch booking details
        return executeQuery(query, new Object[]{bookingID});  // calling executeQuery to fetch and return the results
    }
}
