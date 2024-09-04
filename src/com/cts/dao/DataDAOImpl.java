package com.cts.dao;

import com.cts.model.Data;
import com.cts.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDAOImpl implements DataDAO {

    private Connection connection;

    public DataDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addData(Data data) throws DAOException {
        String query = "INSERT INTO Data (participant_id, data_date, data_value, data_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, data.getParticipantId());
            pstmt.setDate(2, data.getDataDate());
            pstmt.setString(3, data.getDataValue());
            pstmt.setString(4, data.getDataType());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error adding data", e);
        }
    }

    @Override
    public Data getData(int dataId) throws DAOException {
        String query = "SELECT * FROM Data WHERE data_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, dataId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Data(
                            rs.getInt("data_id"),
                            rs.getInt("participant_id"),
                            rs.getDate("data_date"),
                            rs.getString("data_value"),
                            rs.getString("data_type")
                    );
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving data", e);
        }
        return null;
    }

    @Override
    public List<Data> getAllData() throws DAOException {
        List<Data> dataList = new ArrayList<>();
        String query = "SELECT * FROM Data";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Data data = new Data(
                        rs.getInt("data_id"),
                        rs.getInt("participant_id"),
                        rs.getDate("data_date"),
                        rs.getString("data_value"),
                        rs.getString("data_type")
                );
                dataList.add(data);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving data", e);
        }
        return dataList;
    }

    @Override
    public void updateData(Data data) throws DAOException {
        String query = "UPDATE Data SET participant_id = ?, data_date = ?, data_value = ?, data_type = ? WHERE data_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, data.getParticipantId());
            pstmt.setDate(2, data.getDataDate());
            pstmt.setString(3, data.getDataValue());
            pstmt.setString(4, data.getDataType());
            pstmt.setInt(5, data.getDataId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating data", e);
        }
    }

    @Override
    public void deleteData(int dataId) throws DAOException {
        String query = "DELETE FROM Data WHERE data_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, dataId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting data", e);
        }
    }
}
