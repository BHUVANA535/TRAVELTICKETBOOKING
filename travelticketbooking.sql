create database travelticketbooking;
use travelticketbooking;
CREATE TABLE travel_bookings (
    id INT PRIMARY KEY, boarding VARCHAR(255),destination VARCHAR(255),                
    mode VARCHAR(50),                       
    booking_date DATE,                      
    departure_date DATE,                    
    name VARCHAR(255),                       
    phone VARCHAR(15),                       
    selected_option VARCHAR(255)             
);
select * from travel_bookings;
CREATE TABLE hotel_bookings (
    id INT PRIMARY KEY,                    -- Unique booking ID
    city VARCHAR(255),                      -- City of the hotel booking
    date DATE,                              -- Date of the booking
    name VARCHAR(255),                      -- Name of the person making the booking
    phone VARCHAR(15),                      -- Phone number of the person
    selected_hotel VARCHAR(255)             -- Name of the selected hotel with price
);
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_id VARCHAR(100) NOT NULL UNIQUE
);

select * from users;

CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    card_number VARCHAR(20) NOT NULL,
    expiry_date VARCHAR(7) NOT NULL,
    cvv VARCHAR(4) NOT NULL
);

select * from payments;