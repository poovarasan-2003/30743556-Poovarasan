CREATE DATABASE clinical_trials;
USE clinical_trials;
CREATE TABLE Trial (
    trial_id INT AUTO_INCREMENT PRIMARY KEY,
    trial_name VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    status ENUM('Planned', 'In Progress', 'Completed')
);

CREATE TABLE Participant (
    participant_id INT AUTO_INCREMENT PRIMARY KEY,
    participant_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    contact_number VARCHAR(15),
    email VARCHAR(100),
    trial_id INT,
    FOREIGN KEY (trial_id) REFERENCES Trial(trial_id) ON DELETE CASCADE
);

CREATE TABLE Data (
    data_id INT AUTO_INCREMENT PRIMARY KEY,
    participant_id INT,
    data_date DATE,
    data_value VARCHAR(255),
    data_type VARCHAR(50),
    FOREIGN KEY (participant_id) REFERENCES Participant(participant_id) ON DELETE CASCADE
);
