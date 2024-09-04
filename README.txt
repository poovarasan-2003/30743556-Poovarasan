
Clinical Trial Management System

Overview

This Clinical Trial Management System is a menu-based console application built with Core Java, MySQL, and JDBC. It allows users to manage clinical trials, participants, and related data efficiently.

Features

1. Trial Management: Add, view, update, and delete clinical trials.
2. Participant Management: Register, view, update, and delete participants.
3. Data Management: Record, view, update, and delete trial data for participants.

Setup

Prerequisites

- Java 8+ and MySQL.
- JDBC driver jar file.

Database Setup

1. Create Database and Tables:
   CREATE DATABASE clinical_trial_db;
   USE clinical_trial_db;

   CREATE TABLE Trial (
       trial_id INT PRIMARY KEY AUTO_INCREMENT,
       trial_name VARCHAR(255) NOT NULL,
       start_date DATE NOT NULL,
       end_date DATE NOT NULL,
       status VARCHAR(50) NOT NULL
   );

   CREATE TABLE Participant (
       participant_id INT PRIMARY KEY AUTO_INCREMENT,
       participant_name VARCHAR(255) NOT NULL,
       date_of_birth DATE NOT NULL,
       contact_number VARCHAR(15) NOT NULL,
       email VARCHAR(255) NOT NULL,
       trial_id INT,
       FOREIGN KEY (trial_id) REFERENCES Trial(trial_id) ON DELETE CASCADE
   );

   CREATE TABLE Data (
       data_id INT PRIMARY KEY AUTO_INCREMENT,
       participant_id INT,
       data_date DATE NOT NULL,
       data_value VARCHAR(255) NOT NULL,
       data_type VARCHAR(100) NOT NULL,
       FOREIGN KEY (participant_id) REFERENCES Participant(participant_id) ON DELETE CASCADE
   );

2. Configure Database: Update db.properties with your MySQL credentials.

Build and Run

1. Clone the Repository:
   git clone https://github.com/yourusername/clinical-trial-management-system.git
   cd clinical-trial-management-system

2. Build with Maven:
   mvn clean install

3. Run the Application:
   java -jar target/clinical-trial-management-system.jar

Usage

- Main Menu: Navigate through options to manage trials, participants, and data.
- Example Operations: Add trials, register participants, record data.

Exception Handling

- DAOException: Thrown for database errors.
- ServiceException: Thrown for business logic violations.

Conclusion

This system demonstrates a simple, modular console application for managing clinical trials using Java, JDBC, and MySQL.
