package com.sakshi.coaching.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import com.sakshi.coaching.Model.Course;
import com.sakshi.coaching.Model.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCourse(Course course) {
        String s = "insert into course(id,name,teacherId) values(?,?,?)";
        jdbcTemplate.update(s, course.getId(), course.getName(), course.getTeacherId());
    }

    public void removeCourse(int courseId) {
        String s = "delete from course where id=?";
        jdbcTemplate.update(s, courseId);
    }

    public List<Course> getAllCourses() {
        String s = "select * from course";
        return jdbcTemplate.query(s, courseRowMapper);
    }

    public List<Course> getAllCoursesUnderTeacher(int teacherId) {
        String s = "select * from course where teacherId=?";
        return jdbcTemplate.query(s, courseRowMapper, teacherId);
    }

    public Course getCourseByCourseId(int courseId) {
        String s = "select * from course where id=?";
        return jdbcTemplate.queryForObject(s, courseRowMapper, courseId);
    }

    public void createNotes(Notes notes) {
        String s = "insert into notes(id,courseId,date,title,notes) values(?,?,?,?,?)";
        jdbcTemplate.update(s, notes.getId(), notes.getCourseId(), notes.getDate(), notes.getTitle(), notes.getNotes());
    }

    public List<Integer> getAllBatchIdsByCourseId(int courseId) {
        String s = "select batchId from batch_course where courseId=?";
        return jdbcTemplate.query(s, (rs, i) -> {
            return rs.getInt("batchId");
        }, courseId);
    }

    public void removeNotes(int notesId) {
        String s = "delete from notes where id=?";
        jdbcTemplate.update(s, notesId);
    }

    public void editNotes(Notes notes, int notesId) {
        String s = "update notes set title=?,notes=? where id=?";
        System.out.println(notes.toString());
        jdbcTemplate.update(s, notes.getTitle(), notes.getNotes(), notesId);
    }

    public List<Notes> getAllNotesByCourseId(int courseId) {
        String s = "select * from notes where courseId=?";
        return jdbcTemplate.query(s, notesRowMapper, courseId);
    }

    public Notes getNotesByNoteId(int notesId) {
        String s = "select * from notes where id=?";
        return jdbcTemplate.queryForObject(s, notesRowMapper, notesId);
    }

    private RowMapper<Course> courseRowMapper = new RowMapper<Course>() {
        @Override
        public Course mapRow(ResultSet rs, int i) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setTeacherId(rs.getInt("teacherId"));
            return course;
        }
    };

    private RowMapper<Notes> notesRowMapper = new RowMapper<Notes>() {
        @Override
        public Notes mapRow(ResultSet rs, int i) throws SQLException {
            Notes notes = new Notes();
            notes.setId(rs.getInt("id"));
            notes.setCourseId(rs.getInt("courseId"));
            notes.setDate(rs.getDate("date"));
            notes.setNotes(rs.getString("notes"));
            notes.setTitle(rs.getString("title"));
            return notes;
        }
    };
}
