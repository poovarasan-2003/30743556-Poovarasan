package com.cts.service;

import com.cts.dao.ParticipantDAO;
import com.cts.model.Participant;
import com.cts.exception.ServiceException;

import java.util.List;

public class ParticipantService {
    private ParticipantDAO participantDAO;

    public ParticipantService(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

    public void addParticipant(Participant participant) throws ServiceException {
        try {
            participantDAO.addParticipant(participant);
        } catch (Exception e) {
            throw new ServiceException("Unable to add participant", e);
        }
    }

    public Participant getParticipant(int participantId) throws ServiceException {
        try {
            return participantDAO.getParticipant(participantId);
        } catch (Exception e) {
            throw new ServiceException("Unable to retrieve participant", e);
        }
    }

    public List<Participant> getAllParticipants() throws ServiceException {
        try {
            return participantDAO.getAllParticipants();
        } catch (Exception e) {
            throw new ServiceException("Unable to retrieve participants", e);
        }
    }

    public void updateParticipant(Participant participant) throws ServiceException {
        try {
            participantDAO.updateParticipant(participant);
        } catch (Exception e) {
            throw new ServiceException("Unable to update participant", e);
        }
    }

    public void deleteParticipant(int participantId) throws ServiceException {
        try {
            participantDAO.deleteParticipant(participantId);
        } catch (Exception e) {
            throw new ServiceException("Unable to delete participant", e);
        }
    }
}
