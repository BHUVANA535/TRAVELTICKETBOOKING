package travelticketbooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

//imported the required libraries,
//defined a class for creating DataBase
public class CreateDB {
    public static void main(String[] args) throws Exception {
        //we are usig the try-catc block here to handle the exceptions
        try {
            String url = "jdbc:mysql://localhost:3306/";  // defining the URL to connect to MySQL server
            
            String databaseName = "travelticketbooking";  // defining the name of the database to create
            String userName = "root";  // defining the username for the MySQL connection
            String password = "Boon@1234";  // defining the password for the MySQL connection
    
            // defining a connection to the MySQL server
            Connection connection = DriverManager.getConnection(url, userName, password);
    
            // defining the SQL query to create the database
            String sql = "CREATE DATABASE " + databaseName;
    
            // defining a statement to execute the SQL query
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);  // executing the SQL query to create the database
            statement.close();  // closing the statement
            
            // defining a message dialog to inform the user about the success
            JOptionPane.showMessageDialog(null, databaseName + " Database has been created successfully", 
                                          "System Message", JOptionPane.INFORMATION_MESSAGE);
    
        } catch (Exception e) {
            e.printStackTrace();  // printing the stack trace in case of an exception
        }
    }
}
