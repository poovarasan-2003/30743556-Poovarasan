package com.cts.dao;

import com.cts.model.Trial;
import com.cts.exception.DAOException;

import java.util.List;

public interface TrialDAO {
    void addTrial(Trial trial) throws DAOException;
    Trial getTrial(int trialId) throws DAOException;
    List<Trial> getAllTrials() throws DAOException;
    void updateTrial(Trial trial) throws DAOException;
    void deleteTrial(int trialId) throws DAOException;
}
