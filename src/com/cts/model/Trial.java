package com.cts.model;

import java.sql.Date;

public class Trial {
    private int trialId;
    private String trialName;
    private Date startDate;
    private Date endDate;
    private String status;

    public Trial(int trialId, String trialName, Date startDate, Date endDate, String status) {
        this.trialId = trialId;
        this.trialName = trialName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getTrialId() {
        return trialId;
    }

    public void setTrialId(int trialId) {
        this.trialId = trialId;
    }

    public String getTrialName() {
        return trialName;
    }

    public void setTrialName(String trialName) {
        this.trialName = trialName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Trial [trialId=" + trialId + ", trialName=" + trialName + ", startDate=" + startDate + ", endDate="
                + endDate + ", status=" + status + "]";
    }
}
