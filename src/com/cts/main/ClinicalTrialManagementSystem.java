package com.cts.main;

import com.cts.util.DatabaseConnectionUtil;
import java.sql.SQLException;
import java.util.Scanner;

public class ClinicalTrialManagementSystem {
    public static void main(String[] args) {
        try {
            DatabaseConnectionUtil dbUtil = new DatabaseConnectionUtil();
            TrialManagement trialManagement = new TrialManagement(dbUtil);
            ParticipantManagement participantManagement = new ParticipantManagement(dbUtil);
            DataManagement dataManagement = new DataManagement(dbUtil);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Manage Trials");
                System.out.println("2. Manage Participants");
                System.out.println("3. Manage Data");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        trialManagement.manageTrials(scanner);
                        break;
                    case 2:
                        participantManagement.manageParticipants(scanner);
                        break;
                    case 3:
                        dataManagement.manageData(scanner);
                        break;
                    case 4:
                        scanner.close();
                        dbUtil.closeConnection();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }
}
