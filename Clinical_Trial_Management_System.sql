create database ClinicalTrialDB;
USE ClinicalTrialDB;

CREATE TABLE Trial (
    trial_id INT PRIMARY KEY AUTO_INCREMENT,
    trial_name VARCHAR(100),
    start_date DATE,
    end_date DATE,
    status ENUM('Planned', 'In Progress', 'Completed')
);

CREATE TABLE Participant (
    participant_id INT PRIMARY KEY AUTO_INCREMENT,
    participant_name VARCHAR(100),
    date_of_birth DATE,
    contact_number VARCHAR(15),
    email VARCHAR(100),
    trial_id INT,
    FOREIGN KEY (trial_id) REFERENCES Trial(trial_id)
);

CREATE TABLE Data (
    data_id INT PRIMARY KEY AUTO_INCREMENT,
    participant_id INT,
    data_date DATE,
    data_value VARCHAR(255),
    data_type VARCHAR(100),
    FOREIGN KEY (participant_id) REFERENCES Participant(participant_id)
);
