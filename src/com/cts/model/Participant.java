package com.cts.model;

import java.sql.Date;

public class Participant {
    private int participantId;
    private String participantName;
    private Date dateOfBirth;
    private String contactNumber;
    private String email;
    private int trialId;

    public Participant(int participantId, String participantName, Date dateOfBirth, String contactNumber, String email, int trialId) {
        this.participantId = participantId;
        this.participantName = participantName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.email = email;
        this.trialId = trialId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTrialId() {
        return trialId;
    }

    public void setTrialId(int trialId) {
        this.trialId = trialId;
    }

    @Override
    public String toString() {
        return "Participant [participantId=" + participantId + ", participantName=" + participantName + ", dateOfBirth=" + dateOfBirth +
                ", contactNumber=" + contactNumber + ", email=" + email + ", trialId=" + trialId + "]";
    }
}
