package com.sakshi.coaching.Model;

import java.sql.Date;

public class Test {
    private int id;
    private int courseId;
    private String testName;
    private String question;
    private Date testDate;
    private String startTime;
    private String endTime;
    private int duration;
    private int maximumMarks;
    private int averageMarks;

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMaximumMarks() {
        return maximumMarks;
    }

    public void setMaximumMarks(int maximumMarks) {
        this.maximumMarks = maximumMarks;
    }

    public int getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(int averageMarks) {
        this.averageMarks = averageMarks;
    }

    public Test() {
    }

    public Test(int id, int courseId, String testName, String question, Date testDate, String startTime, String endTime,
            int duration, int maximumMarks, int averageMarks) {
        this.id = id;
        this.courseId = courseId;
        this.testName = testName;
        this.question = question;
        this.testDate = testDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.maximumMarks = maximumMarks;
        this.averageMarks = averageMarks;
    }

    @Override
    public String toString() {
        return "Test [averageMarks=" + averageMarks + ", courseId=" + courseId + ", duration=" + duration + ", endTime="
                + endTime + ", id=" + id + ", maximumMarks=" + maximumMarks + ", question=" + question + ", startTime="
                + startTime + ", testDate=" + testDate + ", testName=" + testName + "]";
    }

}