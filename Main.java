import java.awt.*;
import java.sql.*;
import java.util.Stack;
import javax.swing.*;

public class Main {

    private static JFrame frame;
    private static Connection connection;
    private static Stack<JPanel> pageHistory = new Stack<>();
    private static String selectedBookingType = "Travel"; 
    private static String selectedTravelOption;
    private static String selectedHotelOption;

    public static void main(String[] args) {
        try {
            // establishing database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelticketbooking", "root", "Boon@1234");
            // initializing the main frame
            frame = new JFrame("Welcome to Travel Booking System");
            frame.setSize(700, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // showing the welcome page
            showWelcomePage(); 
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
             // displaying an error message in case of a failed database connection
            JOptionPane.showMessageDialog(frame, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void showWelcomePage() {
        // creating a new panel for the welcome page
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel welcomeLabel = new JLabel("Welcome to Travel Booking System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(150, 150, 400, 50);
        panel.add(welcomeLabel);
        
        // adding login and registration buttons
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 250, 180, 50);
        panel.add(loginButton);

        //addig a registartion-button
        JButton registrationButton = new JButton("Registration");
        registrationButton.setBounds(350, 250, 180, 50);
        panel.add(registrationButton);

        // setting action listeners for buttons to navigate to respective pages
        loginButton.addActionListener(e -> showLoginPage());
        registrationButton.addActionListener(e -> showRegistrationPage());
// setting the welcome page as the content pane
        frame.setContentPane(panel);
        frame.revalidate();
    }

    private static void showLoginPage() {
        // creating a new panel for the login page
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(50, 50, 200, 25);
        panel.add(userIdLabel);

        // adding text field for user ID
        JTextField userIdText = new JTextField(20);
        userIdText.setBounds(250, 50, 200, 25);
        panel.add(userIdText);

        
        //adding a passwordlabel
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 200, 25);
        panel.add(passwordLabel);

        // adding password field
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(250, 100, 200, 25);
        panel.add(passwordField);
        //adding a loginbutton
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 150, 150, 40);
        panel.add(loginButton);

        //adding a back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 150, 150, 40);
        panel.add(backButton);

        // adding action listener to handle login process
        loginButton.addActionListener(e -> {
            String userId = userIdText.getText();
            String password = new String(passwordField.getPassword());

            if (authenticateUser(userId, password)) {
                // navigating to home page if authentication is successful
                showHomePage();
            } else {
                // displaying an error message for invalid credentials
                JOptionPane.showMessageDialog(frame, "Invalid User ID or Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // adding action listener for the back button
        backButton.addActionListener(e -> showWelcomePage());

        frame.setContentPane(panel);
        frame.revalidate();
    }

    private static void showRegistrationPage() {
        // creating a new panel for the registration page
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 200, 25);
        panel.add(usernameLabel);

        // adding text field for username
        JTextField usernameText = new JTextField(20);
        usernameText.setBounds(250, 50, 200, 25);
        panel.add(usernameText);

        //adding an emailLabel
        JLabel emailLabel = new JLabel("Email ID:");
        emailLabel.setBounds(50, 100, 200, 25);
        panel.add(emailLabel);
// adding text field for email
        JTextField emailText = new JTextField(20);
        emailText.setBounds(250, 100, 200, 25);
        panel.add(emailText);
// adding password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 200, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(250, 150, 200, 25);
        panel.add(passwordField);

        // adding text field for user ID
        JLabel userIdLabel = new JLabel("Create User ID:");
        userIdLabel.setBounds(50, 200, 200, 25);
        panel.add(userIdLabel);

        JTextField userIdText = new JTextField(20);
        userIdText.setBounds(250, 200, 200, 25);
        panel.add(userIdText);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(250, 250, 150, 40);
        panel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 250, 150, 40);
        panel.add(backButton);

        // adding action listener for the registration button
        registerButton.addActionListener(e -> {
            String username = usernameText.getText();
            String email = emailText.getText();
            String password = new String(passwordField.getPassword());
            String userId = userIdText.getText();

            if (registerUser(username, email, password, userId)) {
                // showing success message and navigating back to the welcome page
                JOptionPane.showMessageDialog(frame, "Registration successful!");
                showWelcomePage();
            } else {
                // showing error message in case registration fails
                JOptionPane.showMessageDialog(frame, "Registration failed! User ID may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // adding action listener for the back button
        backButton.addActionListener(e -> showWelcomePage());

        frame.setContentPane(panel);
        frame.revalidate();
    }
    //defining a function to authenticate user based on user id and password
    private static boolean authenticateUser(String userId, String password) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE user_id = ? AND password = ?")) {
            stmt.setString(1, userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // returning true if user is authenticated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //for registation of user
    private static boolean registerUser(String username, String email, String password, String userId) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (username, email, password, user_id) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, userId);
            stmt.executeUpdate();
            return true; // returning true if registration is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // returning false if registration fails
        }
    }

    private static void showHomePage() {
        // creating a new panel for the home page
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel welcomeLabel = new JLabel("Welcome to Travel Booking System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(150, 150, 400, 50);
        panel.add(welcomeLabel);

        // adding travel booking and hotel booking buttons
        JButton travelBookingButton = new JButton("Travel Booking");
        travelBookingButton.setBounds(150, 250, 180, 50);
        panel.add(travelBookingButton);

        JButton hotelBookingButton = new JButton("Hotel Booking");
        hotelBookingButton.setBounds(350, 250, 180, 50);
        panel.add(hotelBookingButton);

        // setting action listeners for both buttons to navigate to respective booking pages
        travelBookingButton.addActionListener(e -> {
            selectedBookingType = "Travel";
            pageHistory.push(panel);
            showTravelBookingPage();
        });

        hotelBookingButton.addActionListener(e -> {
            selectedBookingType = "Hotel";
            pageHistory.push(panel);
            showHotelBookingPage();
        });

        // setting the home page as the content pane
        frame.setContentPane(panel);
        frame.revalidate();
    }

    private static void showTravelBookingPage() {
        // creating a new panel for the travel booking page
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel boardingLabel = new JLabel("Enter Boarding Point:");
        boardingLabel.setBounds(50, 50, 200, 25);
        panel.add(boardingLabel);

        // adding text field for boarding point
        JTextField boardingText = new JTextField(20);
        boardingText.setBounds(250, 50, 200, 25);
        panel.add(boardingText);

        JLabel destinationLabel = new JLabel("Enter Destination:");
        destinationLabel.setBounds(50, 100, 200, 25);
        panel.add(destinationLabel);

        // adding text field for destination

        JTextField destinationText = new JTextField(20);
        destinationText.setBounds(250, 100, 200, 25);
        panel.add(destinationText);

        JLabel modeLabel = new JLabel("Select Mode:");
        modeLabel.setBounds(50, 150, 200, 25);
        panel.add(modeLabel);

        JComboBox<String> modeComboBox = new JComboBox<>(new String[]{"Bus", "Train", "Flight"});
        modeComboBox.setBounds(250, 150, 200, 25);
        panel.add(modeComboBox);

        JLabel bookingDateLabel = new JLabel("Enter Booking Date:");
        bookingDateLabel.setBounds(50, 200, 200, 25);
        panel.add(bookingDateLabel);

        JTextField bookingDateText = new JTextField(20);
        bookingDateText.setBounds(250, 200, 200, 25);
        panel.add(bookingDateText);

        JLabel departureDateLabel = new JLabel("Enter Departure Date:");
        departureDateLabel.setBounds(50, 250, 200, 25);
        panel.add(departureDateLabel);

        JTextField departureDateText = new JTextField(20);
        departureDateText.setBounds(250, 250, 200, 25);
        panel.add(departureDateText);

        JLabel nameLabel = new JLabel("Enter Name:");
        nameLabel.setBounds(50, 300, 200, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(250, 300, 200, 25);
        panel.add(nameText);

        JLabel phoneLabel = new JLabel("Enter Phone Number:");
        phoneLabel.setBounds(50, 350, 200, 25);
        panel.add(phoneLabel);

        JTextField phoneText = new JTextField(20);
        phoneText.setBounds(250, 350, 200, 25);
        panel.add(phoneText);

        // Available  Options label and list positionend under the phone number thing
        JLabel availabilityLabel = new JLabel("Available Options:");
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 20));
        availabilityLabel.setBounds(200, 400, 300, 40);
        panel.add(availabilityLabel);

        if (selectedBookingType.equals("Travel")) {
            String[] options = {"Option 1 - 10:00 AM - Rs7500", "Option 2 - 12:00 PM - Rs9000", "Option 3 - 02:00 PM - Rs12000"};
            JList<String> availableOptions = new JList<>(options);
            availableOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            availableOptions.setBounds(100, 440, 500, 100);
            panel.add(availableOptions);

            JButton selectButton = new JButton("Select Option");
            selectButton.setBounds(250, 560, 200, 40);
            panel.add(selectButton);

            selectButton.addActionListener(e -> {
                String selectedOption = availableOptions.getSelectedValue();
                if (selectedOption != null) {
                    selectedTravelOption = selectedOption;
                    JOptionPane.showMessageDialog(frame, "You selected: " + selectedTravelOption);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a travel option.");
                }
            });
        }

        // Back button if the user wanted to go back
        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 620, 150, 40);
        panel.add(backButton);

        backButton.addActionListener(e -> {
            if (!pageHistory.isEmpty()) {
                panel.setVisible(false); // Hiding the current page
                JPanel previousPage = pageHistory.pop(); // going back the page
                frame.setContentPane(previousPage);
                frame.revalidate();
            }
        });

        // Submit button is under , last button in the page
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(180, 620, 150, 40);
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            String boarding = boardingText.getText();
            String destination = destinationText.getText();
            String mode = (String) modeComboBox.getSelectedItem();
            String bookingDate = bookingDateText.getText();
            String departureDate = departureDateText.getText();
            String name = nameText.getText();
            String phone = phoneText.getText();

            if (boarding.isEmpty() || destination.isEmpty() || mode == null || bookingDate.isEmpty() || departureDate.isEmpty() || name.isEmpty() || phone.isEmpty() || selectedTravelOption == null) {
                JOptionPane.showMessageDialog(frame, "All fields are required and a travel option must be selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO travel_bookings (boarding, destination, mode, booking_date, departure_date, name, phone, selected_option) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                stmt.setString(1, boarding);
                stmt.setString(2, destination);
                stmt.setString(3, mode);
                stmt.setString(4, bookingDate);
                stmt.setString(5, departureDate);
                stmt.setString(6, name);
                stmt.setString(7, phone);
                stmt.setString(8, selectedTravelOption);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Travel booking saved successfully!");
                pageHistory.push(panel);
                showPaymentPage();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to save travel booking: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setContentPane(panel);
        frame.revalidate();
    }

    private static void showHotelBookingPage() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel cityLabel = new JLabel("Enter City:");
        cityLabel.setBounds(50, 50, 200, 25);
        panel.add(cityLabel);

        JTextField cityText = new JTextField(20);
        cityText.setBounds(250, 50, 200, 25);
        panel.add(cityText);

        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(50, 100, 200, 25);
        panel.add(dateLabel);

        JTextField dateText = new JTextField(20);
        dateText.setBounds(250, 100, 200, 25);
        panel.add(dateText);

        JLabel nameLabel = new JLabel("Enter Name:");
        nameLabel.setBounds(50, 150, 200, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(250, 150, 200, 25);
        panel.add(nameText);

        JLabel phoneLabel = new JLabel("Enter Phone Number:");
        phoneLabel.setBounds(50, 200, 200, 25);
        panel.add(phoneLabel);

        JTextField phoneText = new JTextField(20);
        phoneText.setBounds(250, 200, 200, 25);
        panel.add(phoneText);

        // Available Hotels are the labels of the positioned below Phone Number field
        JLabel availabilityLabel = new JLabel("Available Hotels:");
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 20));
        availabilityLabel.setBounds(50, 250, 300, 40);
        panel.add(availabilityLabel);

        // List of the available hotels below the "Available Hotels" label
        String[] hotelOptions = {"James Marriott - Rs15000", "Hilton Hotel - Rs18000", "The Ritz - Rs22000"};
        JList<String> hotelList = new JList<>(hotelOptions);
        hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelList.setBounds(50, 300, 500, 100);
        panel.add(hotelList);

        // Selecting the Hotel button bdelow the list of the available hotels
        JButton selectHotelButton = new JButton("Select Hotel");
        selectHotelButton.setBounds(250, 420, 200, 40);
        panel.add(selectHotelButton);

        selectHotelButton.addActionListener(e -> {
            String selectedHotel = hotelList.getSelectedValue();
            if (selectedHotel != null) {
                selectedHotelOption = selectedHotel;
                JOptionPane.showMessageDialog(frame, "You selected: " + selectedHotelOption);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a hotel option.");
            }
        });

        // Back button for going back.
        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 480, 150, 40);
        panel.add(backButton);

        backButton.addActionListener(e -> {
            if (!pageHistory.isEmpty()) {
                panel.setVisible(false); // Hiding the current page
                JPanel previousPage = pageHistory.pop(); // Goin back to the previous page
                frame.setContentPane(previousPage);
                frame.revalidate();
            }
        });

        // Submitt  button is with back button
        // make sure to have the back button with submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 480, 150, 40);
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            String city = cityText.getText();
            String date = dateText.getText();
            String name = nameText.getText();
            String phone = phoneText.getText();

            if (city.isEmpty() || date.isEmpty() || name.isEmpty() || phone.isEmpty() || selectedHotelOption == null) {
                JOptionPane.showMessageDialog(frame, "All fields are required and a hotel must be selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO hotel_bookings (city, date, name, phone, selected_hotel) VALUES (?, ?, ?, ?, ?)")) {
                stmt.setString(1, city);
                stmt.setString(2, date);
                stmt.setString(3, name);
                stmt.setString(4, phone);
                stmt.setString(5, selectedHotelOption);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Hotel booking saved successfully!");
                pageHistory.push(panel);
                showPaymentPage();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to save hotel booking: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setContentPane(panel);
        frame.revalidate();
    }

    private static void showPaymentPage() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel cardLabel = new JLabel("Enter Card Number:");
        cardLabel.setBounds(50, 50, 200, 25);
        panel.add(cardLabel);

        JTextField cardText = new JTextField(20);
        cardText.setBounds(250, 50, 200, 25);
        panel.add(cardText);

        JLabel expiryLabel = new JLabel("Enter Expiry Date (MM/YY):");
        expiryLabel.setBounds(50, 100, 200, 25);
        panel.add(expiryLabel);

        JTextField expiryText = new JTextField(20);
        expiryText.setBounds(250, 100, 200, 25);
        panel.add(expiryText);

        JLabel cvvLabel = new JLabel("Enter CVV:");
        cvvLabel.setBounds(50, 150, 200, 25);
        panel.add(cvvLabel);

        JTextField cvvText = new JTextField(20);
        cvvText.setBounds(250, 150, 200, 25);
        panel.add(cvvText);

        // Back button added here again
        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 200, 150, 40);
        panel.add(backButton);

        backButton.addActionListener(e -> {
            if (!pageHistory.isEmpty()) {
                panel.setVisible(false); // hinfin the page
                JPanel previousPage = pageHistory.pop(); // Going back
                frame.setContentPane(previousPage);
                frame.revalidate();
            }
        });

        // LAST FINAL SUBMIT BUTTON
        JButton submitButton = new JButton("Submit Payment");
        submitButton.setBounds(250, 200, 200, 40);
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            String cardNumber = cardText.getText();
            String expiryDate = expiryText.getText();
            String cvv = cvvText.getText();

            if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(frame, "Payment processed successfully!");
            pageHistory.push(panel);
            showHomePage();
        });

        frame.setContentPane(panel);
        frame.revalidate();
    }
}
