package com.cts.main;

import com.cts.model.Trial;
import com.cts.service.TrialService;
import com.cts.exception.ServiceException;
import com.cts.dao.TrialDAOImpl;
import com.cts.util.DatabaseConnectionUtil;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class TrialManagement {
    private final TrialService trialService;

    public TrialManagement(DatabaseConnectionUtil dbUtil) {
        Connection connection = dbUtil.getConnection();
        this.trialService = new TrialService(new TrialDAOImpl(connection));
    }

    public void manageTrials(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Trial");
            System.out.println("2. View Trial");
            System.out.println("3. View All Trials");
            System.out.println("4. Update Trial");
            System.out.println("5. Delete Trial");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            String trialName = null;
            java.sql.Date startDate = null;
            java.sql.Date endDate = null;
            String status = null;
            int trialId;

            switch (choice) {
                case 1:
                    System.out.print("Enter trial name: ");
                    trialName = scanner.next();
                    System.out.print("Enter start date (yyyy-mm-dd): ");
                    startDate = java.sql.Date.valueOf(scanner.next());
                    System.out.print("Enter end date (yyyy-mm-dd): ");
                    endDate = java.sql.Date.valueOf(scanner.next());
                    System.out.print("Enter status (Planned, In Progress, Completed): ");
                    status = scanner.next();

                    Trial trial = new Trial(0, trialName, startDate, endDate, status);

                    try {
                        trialService.addTrial(trial);
                        System.out.println("Trial added successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error adding trial: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter trial ID: ");
                    trialId = scanner.nextInt();

                    try {
                        Trial retrievedTrial = trialService.getTrial(trialId);
                        if (retrievedTrial != null) {
                            System.out.println(retrievedTrial);
                        } else {
                            System.out.println("Trial not found.");
                        }
                    } catch (ServiceException e) {
                        System.err.println("Error retrieving trial: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        List<Trial> trials = trialService.getAllTrials();
                        for (Trial t : trials) {
                            System.out.println(t);
                        }
                    } catch (ServiceException e) {
                        System.err.println("Error retrieving trials: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter trial ID: ");
                    trialId = scanner.nextInt();
                    System.out.print("Enter new trial name: ");
                    trialName = scanner.next();
                    System.out.print("Enter new start date (yyyy-mm-dd): ");
                    startDate = java.sql.Date.valueOf(scanner.next());
                    System.out.print("Enter new end date (yyyy-mm-dd): ");
                    endDate = java.sql.Date.valueOf(scanner.next());
                    System.out.print("Enter new status (Planned, In Progress, Completed): ");
                    status = scanner.next();

                    trial = new Trial(trialId, trialName, startDate, endDate, status);

                    try {
                        trialService.updateTrial(trial);
                        System.out.println("Trial updated successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error updating trial: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter trial ID to delete: ");
                    trialId = scanner.nextInt();

                    try {
                        trialService.deleteTrial(trialId);
                        System.out.println("Trial deleted successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error deleting trial: " + e.getMessage());
                    }
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
