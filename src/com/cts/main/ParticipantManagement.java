package com.cts.main;

import com.cts.model.Participant;
import com.cts.service.ParticipantService;
import com.cts.exception.ServiceException;
import com.cts.dao.ParticipantDAOImpl;
import com.cts.util.DatabaseConnectionUtil;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class ParticipantManagement {
    private final ParticipantService participantService;

    public ParticipantManagement(DatabaseConnectionUtil dbUtil) {
        Connection connection = dbUtil.getConnection();
        this.participantService = new ParticipantService(new ParticipantDAOImpl(connection));
    }

    public void manageParticipants(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Participant");
            System.out.println("2. View Participant");
            System.out.println("3. View All Participants");
            System.out.println("4. Update Participant");
            System.out.println("5. Delete Participant");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            String participantName = null;
            java.sql.Date dateOfBirth = null;
            String contactNumber = null;
            String email = null;
            int trialId;
            int participantId;

            switch (choice) {
                case 1:
                    System.out.print("Enter participant name: ");
                    participantName = scanner.next();
                    System.out.print("Enter date of birth (yyyy-mm-dd): ");
                    dateOfBirth = java.sql.Date.valueOf(scanner.next());
                    System.out.print("Enter contact number: ");
                    contactNumber = scanner.next();
                    System.out.print("Enter email: ");
                    email = scanner.next();
                    System.out.print("Enter trial ID: ");
                    trialId = scanner.nextInt();

                    Participant participant = new Participant(0, participantName, dateOfBirth, contactNumber, email, trialId);

                    try {
                        participantService.addParticipant(participant);
                        System.out.println("Participant added successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error adding participant: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter participant ID: ");
                    participantId = scanner.nextInt();

                    try {
                        Participant retrievedParticipant = participantService.getParticipant(participantId);
                        if (retrievedParticipant != null) {
                            System.out.println(retrievedParticipant);
                        } else {
                            System.out.println("Participant not found.");
                        }
                    } catch (ServiceException e) {
                        System.err.println("Error retrieving participant: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        List<Participant> participants = participantService.getAllParticipants();
                        for (Participant p : participants) {
                            System.out.println(p);
                        }
                    } catch (ServiceException e) {
                        System.err.println("Error retrieving participants: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter participant ID: ");
                    participantId = scanner.nextInt();
                    System.out.print("Enter new participant name: ");
                    participantName = scanner.next();
                    System.out.print("Enter new date of birth (yyyy-mm-dd): ");
                    dateOfBirth = java.sql.Date.valueOf(scanner.next());
                    System.out.print("Enter new contact number: ");
                    contactNumber = scanner.next();
                    System.out.print("Enter new email: ");
                    email = scanner.next();
                    System.out.print("Enter new trial ID: ");
                    trialId = scanner.nextInt();

                    Participant updatedParticipant = new Participant(participantId, participantName, dateOfBirth, contactNumber, email, trialId);

                    try {
                        participantService.updateParticipant(updatedParticipant);
                        System.out.println("Participant updated successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error updating participant: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter participant ID to delete: ");
                    participantId = scanner.nextInt();

                    try {
                        participantService.deleteParticipant(participantId);
                        System.out.println("Participant deleted successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error deleting participant: " + e.getMessage());
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

