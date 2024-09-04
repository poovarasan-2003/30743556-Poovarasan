package com.cts.service;

import com.cts.dao.TrialDAO;
import com.cts.model.Trial;
import com.cts.exception.ServiceException;

import java.util.List;

public class TrialService {
    private TrialDAO trialDAO;

    public TrialService(TrialDAO trialDAO) {
        this.trialDAO = trialDAO;
    }

    public void addTrial(Trial trial) throws ServiceException {
        try {
            trialDAO.addTrial(trial);
        } catch (Exception e) {
            throw new ServiceException("Unable to add trial", e);
        }
    }

    public Trial getTrial(int trialId) throws ServiceException {
        try {
            return trialDAO.getTrial(trialId);
        } catch (Exception e) {
            throw new ServiceException("Unable to retrieve trial", e);
        }
    }

    public List<Trial> getAllTrials() throws ServiceException {
        try {
            return trialDAO.getAllTrials();
        } catch (Exception e) {
            throw new ServiceException("Unable to retrieve trials", e);
        }
    }

    public void updateTrial(Trial trial) throws ServiceException {
        try {
            trialDAO.updateTrial(trial);
        } catch (Exception e) {
            throw new ServiceException("Unable to update trial", e);
        }
    }

    public void deleteTrial(int trialId) throws ServiceException {
        try {
            trialDAO.deleteTrial(trialId);
        } catch (Exception e) {
            throw new ServiceException("Unable to delete trial", e);
        }
    }
}
