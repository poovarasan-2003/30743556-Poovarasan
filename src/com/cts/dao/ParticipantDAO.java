package com.cts.dao;

import com.cts.model.Participant;
import com.cts.exception.DAOException;

import java.util.List;

public interface ParticipantDAO {
    void addParticipant(Participant participant) throws DAOException;
    Participant getParticipant(int participantId) throws DAOException;
    List<Participant> getAllParticipants() throws DAOException;
    void updateParticipant(Participant participant) throws DAOException;
    void deleteParticipant(int participantId) throws DAOException;
}
