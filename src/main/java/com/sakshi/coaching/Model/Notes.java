package com.sakshi.coaching.Model;

import java.sql.Date;

public class Notes {
    private int id;
    private int courseId;
    private Date date;
    private String title;
    private String notes;

    public Notes() {
    }

    public Notes(int id, int courseId, Date date, String title, String notes) {
        this.id = id;
        this.courseId = courseId;
        this.date = date;
        this.title = title;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Notes [courseId=" + courseId + ", date=" + date + ", id=" + id + ", notes=" + notes + ", title=" + title
                + "]";
    }

}
