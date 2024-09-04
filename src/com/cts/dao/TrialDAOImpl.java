package com.cts.dao;

import com.cts.model.Trial;
import com.cts.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrialDAOImpl implements TrialDAO {

    private Connection connection;

    public TrialDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTrial(Trial trial) throws DAOException {
        String query = "INSERT INTO Trial (trial_name, start_date, end_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, trial.getTrialName());
            pstmt.setDate(2, trial.getStartDate());
            pstmt.setDate(3, trial.getEndDate());
            pstmt.setString(4, trial.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error adding trial", e);
        }
    }

    @Override
    public Trial getTrial(int trialId) throws DAOException {
        String query = "SELECT * FROM Trial WHERE trial_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, trialId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Trial(
                            rs.getInt("trial_id"),
                            rs.getString("trial_name"),
                            rs.getDate("start_date"),
                            rs.getDate("end_date"),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving trial", e);
        }
        return null;
    }

    @Override
    public List<Trial> getAllTrials() throws DAOException {
        List<Trial> trials = new ArrayList<>();
        String query = "SELECT * FROM Trial";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Trial trial = new Trial(
                        rs.getInt("trial_id"),
                        rs.getString("trial_name"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status")
                );
                trials.add(trial);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving trials", e);
        }
        return trials;
    }

    @Override
    public void updateTrial(Trial trial) throws DAOException {
        String query = "UPDATE Trial SET trial_name = ?, start_date = ?, end_date = ?, status = ? WHERE trial_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, trial.getTrialName());
            pstmt.setDate(2, trial.getStartDate());
            pstmt.setDate(3, trial.getEndDate());
            pstmt.setString(4, trial.getStatus());
            pstmt.setInt(5, trial.getTrialId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating trial", e);
        }
    }

    @Override
    public void deleteTrial(int trialId) throws DAOException {
        String query = "DELETE FROM Trial WHERE trial_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, trialId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting trial", e);
        }
    }
}
