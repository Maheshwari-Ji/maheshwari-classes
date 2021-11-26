package com.sakshi.coaching.Model;

import java.sql.Date;

public class Answers {
    private int id;
    private int testId;
    private int studentId;
    private String answer;
    private Date date;
    private int marks;

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Answers() {
    }

    public Answers(int id, int testId, int studentId, String answer, Date date, int marks) {
        this.id = id;
        this.testId = testId;
        this.studentId = studentId;
        this.answer = answer;
        this.date = date;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Answers [answer=" + answer + ", date=" + date + ", id=" + id + ", marks=" + marks + ", studentId="
                + studentId + ", testId=" + testId + "]";
    }

}
