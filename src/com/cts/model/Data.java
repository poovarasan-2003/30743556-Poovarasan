package com.cts.model;

import java.sql.Date;

public class Data {
    private int dataId;
    private int participantId;
    private Date dataDate;
    private String dataValue;
    private String dataType;

    public Data(int dataId, int participantId, Date dataDate, String dataValue, String dataType) {
        this.dataId = dataId;
        this.participantId = participantId;
        this.dataDate = dataDate;
        this.dataValue = dataValue;
        this.dataType = dataType;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Data [dataId=" + dataId + ", participantId=" + participantId + ", dataDate=" + dataDate + ", dataValue=" + dataValue + ", dataType=" + dataType + "]";
    }
}
