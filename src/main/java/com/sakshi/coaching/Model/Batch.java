package com.sakshi.coaching.Model;

import java.sql.Date;

public class Batch {
    private int id;
    private String batchName;
    private int fees;
    private Date startDate;
    private Date endDate;
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Batch() {
    }

    public Batch(int id, String batchName, int fees, Date startDate, Date endDate, int duration) {
        this.id = id;
        this.batchName = batchName;
        this.fees = fees;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Batch [batchName=" + batchName + ", duration=" + duration + ", endDate=" + endDate + ", fees=" + fees
                + ", id=" + id + ", startDate=" + startDate + "]";
    }
}