package com.cts.dao;

import com.cts.model.Participant;
import com.cts.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAOImpl implements ParticipantDAO {

    private Connection connection;

    public ParticipantDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addParticipant(Participant participant) throws DAOException {
        String query = "INSERT INTO Participant (participant_name, date_of_birth, contact_number, email, trial_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, participant.getParticipantName());
            pstmt.setDate(2, participant.getDateOfBirth());
            pstmt.setString(3, participant.getContactNumber());
            pstmt.setString(4, participant.getEmail());
            pstmt.setInt(5, participant.getTrialId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error adding participant", e);
        }
    }

    @Override
    public Participant getParticipant(int participantId) throws DAOException {
        String query = "SELECT * FROM Participant WHERE participant_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, participantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Participant(
                            rs.getInt("participant_id"),
                            rs.getString("participant_name"),
                            rs.getDate("date_of_birth"),
                            rs.getString("contact_number"),
                            rs.getString("email"),
                            rs.getInt("trial_id")
                    );
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving participant", e);
        }
        return null;
    }

    @Override
    public List<Participant> getAllParticipants() throws DAOException {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT * FROM Participant";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Participant participant = new Participant(
                        rs.getInt("participant_id"),
                        rs.getString("participant_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getInt("trial_id")
                );
                participants.add(participant);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving participants", e);
        }
        return participants;
    }

    @Override
    public void updateParticipant(Participant participant) throws DAOException {
        String query = "UPDATE Participant SET participant_name = ?, date_of_birth = ?, contact_number = ?, email = ?, trial_id = ? WHERE participant_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, participant.getParticipantName());
            pstmt.setDate(2, participant.getDateOfBirth());
            pstmt.setString(3, participant.getContactNumber());
            pstmt.setString(4, participant.getEmail());
            pstmt.setInt(5, participant.getTrialId());
            pstmt.setInt(6, participant.getParticipantId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating participant", e);
        }
    }

    @Override
    public void deleteParticipant(int participantId) throws DAOException {
        String query = "DELETE FROM Participant WHERE participant_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, participantId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting participant", e);
        }
    }
}
