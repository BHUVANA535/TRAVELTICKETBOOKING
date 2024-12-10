### Report on the Travel Ticket Booking Project

This is a travel booking system using Object-Oriented Programming (OOP) principles with a Graphical User Interface (GUI) integrated into a SQL database for the management of bookings, payments, notifications, and user accounts. It supports multiple types of bookings, such as train, bus, flight, and hotel bookings, and provides user registration, login, and payment history management functionalities.
OOP Class Design and Relationships

 ## **Classes and Their Functionality**
**User Class:** The User class represents a user in the system. The class contains attributes such as userID, userName, password, email, and associated lists like bookingHistory, paymentHistory, and notifications. It enables users to register, login, manage bookings, view payment history, and receive notifications.

**Booking Class:** A base class for all types of booking. It has attributes such as bookingID, bookingDate, status, price, and a searching for availability method.
TrainBooking, BusBooking, FlightBooking, HotelBooking Classes : Each of these is a sub class of Booking and denote one type of booking. They inherited properties and methods common to both but added more details such as trainNumber, seatNumber, type of train, or point of boarding for train.

**Payment Class:** This class manages payment information such as cardNumber, expiryDate, and cvv. The User class uses this class to save payment history.
Notification Class: This class represents a notification that is sent to the user. It includes notificationID, userID, message, and sentDate. Notifications are linked to users and are created whenever some events (like booking confirmation) take place.

**SearchBar Class:** It deals with the process of searching and filtering through bookings with parameters like searchType, startDate, endDate, startLocation, and destination. It helps users find available options.

**Availability Class:** It is basically the representation of the available options to travel by train, bus, flight, or hotel. It contains availability, price, ratings, and booking status information.
UserInfo Class: It includes the details of a user and takes care of maintaining information regarding the user's interaction with the system. It also maintains a list of related Notifications.


### Relationship between classes:
**Inheritance:**
There are classes such as TrainBooking, BusBooking, FlightBooking, and HotelBooking inheriting from the Booking class. This way, they share common functionality like the booking ID, date, price, etc., and can include specific details particular to each of their respective types of booking, such as trainNumber for train bookings.

**Aggregation:**
Payment is aggregated by TrainBooking, BusBooking, FlightBooking, and HotelBooking. The booking types refer to Payment objects in order to store payment details but do not own them. Several bookings can share one payment instance.

Availability is aggregated by TrainBooking, BusBooking, FlightBooking, and HotelBooking. All these classes use Availability in order to determine whether given travel options (train, bus, flight, or hotel) are available for booking.
The type of booking: TrainBooking, BusBooking, FlightBooking, and HotelBooking aggregate the user's info because these booking types use references of the user's information but do not own them.

**Composition:**
The composition includes composing a notification inside UserInfo. This means when a user is deleted, all those notifications associated with that particular user are deleted too. User info is accountable for both creating and managing notifications.

**Association:**
Availability is associated with SearchBar. SearchBar class is used to find the available travel options based on filters that a user wants, like date and location.

### GUI and SQL Database Integration

**GUI (Graphical User Interface)**
The GUI provides an interface for users to interact with the system. It includes:

**Search Bar:** A user-friendly component that allows users to search for available travel options (train, bus, flight, hotel) by specifying parameters like travel dates, start locations, and destinations. The GUI interacts with the SearchBar class to filter available bookings.

**Booking Form:** Users are able to see and select various travelling choices. The form accepts information of booking like seat, dates for travel and payment mode details.
Login/Registration: User has been enabled for signing up and log in option on account. The system has included log in functionality to verify user by engaging with User class


### SQL Database
The application is built based on the SQL database that captures the details in following categories

**users Table:** Stores user account details including username, email, password, and user_id. The table is used for authenticating users during login and storing their registration information .

**travel_bookings Table:** Used to store the travel booking information, detailing the traveling medium like train, bus, or a flight, date of travel, destination and passenger information like name, phone number.

**hotel_bookings Table:** Used to store the details on hotel bookings including the city, date, the selected hotel, and all the passenger information.

**Payments Table:** This table holds the information related to payments, such as card_number, expiry_date, and cvv. The data is aggregated with the booking.

**Queries:** The SQL database is queried to fetch the information regarding the booking and payment history, availability, and user information. Users can query the database for their previous bookings, payments, and notifications.
Conclusion
This project shows how OOP principles are used to manage a travel booking system that integrates GUI elements with a SQL database. The design allows for easy management of bookings, payments, and notifications, with the flexibility to extend or modify it in the future. The relationships between classes are reflected as inheritance, aggregation, composition, and association, thus a well-structured and maintainable system design. The GUI is seamless to the user, and the SQL database ensures data persistence and efficient retrieval of booking and user-related information.

