package com.sakshi.coaching.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sakshi.coaching.Model.Batch;
import com.sakshi.coaching.Model.Course;
import com.sakshi.coaching.Model.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BatchDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CourseDAO courseDAO;

    public BatchDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addBatch(Batch batch) {
        String s = "insert into batch(id,batchName,fees,startDate,endDate,duration) values(?,?,?,?,?,?)";
        jdbcTemplate.update(s, batch.getId(), batch.getBatchName(), batch.getFees(), batch.getStartDate(),
                batch.getEndDate(), batch.getDuration());
    }

    public void removeBatch(int batchId) {
        String s = "delete from batch where id=?";
        jdbcTemplate.update(s, batchId);
    }

    public List<Batch> getAllBatches() {
        String s = "select * from batch";
        return jdbcTemplate.query(s, batchRowMapper);
    }

    public Batch getBatchByBatchId(int batchId) {
        String s = "select * from batch where id=?";
        return jdbcTemplate.queryForObject(s, batchRowMapper, batchId);
    }

    public void addCourseToBatch(int courseId, int batchId) {
        String s = "insert into batch_course(batchId,courseId) values(?,?)";
        jdbcTemplate.update(s, batchId, courseId);
    }

    public void removeCourseFromBatch(int courseId, int batchId) {
        String s = "delete from batch_course where courseId=? and batchId=?";
        jdbcTemplate.update(s, courseId, batchId);
    }

    public List<Integer> getStudentIdByBatchId(int batchId) {
        String s = "select studentId from batch_student where batchId=?";
        return jdbcTemplate.query(s, (rs, i) -> {
            return rs.getInt("studentId");
        }, batchId);
    }

    public List<Course> getAllCoursesOfBatch(int batchId) {
        String s = "select courseId from batch_course where batchId=?";
        List<Integer> courseIds = jdbcTemplate.query(s, (rs, i) -> {
            return rs.getInt("courseId");
        }, batchId);
        List<Course> courses = new ArrayList<>();
        for (Integer id : courseIds) {
            courses.add(courseDAO.getCourseByCourseId(id));
        }
        return courses;
    }

    // student
    public List<Batch> getAllBatchesOfStudent(int studentId) {
        String s = "select batchId from batch_student where studentId=?";
        List<Integer> batchIds = jdbcTemplate.query(s, (rs, i) -> {
            return rs.getInt("batchId");
        }, studentId);
        List<Batch> batches = new ArrayList<>();
        for (Integer id : batchIds) {
            batches.add(getBatchByBatchId(id));
        }
        return batches;
    }

    public void enrollToBatch(int batchId, int studentId) {
        String s = "insert into batch_student(batchId,studentId) values(?,?)";
        jdbcTemplate.update(s, batchId, studentId);
    }

    public void unenrollFromBatch(int batchId, int studentId) {
        String s = "delete from batch_student where batchId=? and studentId=?";
        jdbcTemplate.update(s, batchId, studentId);
    }

    public List<Notes> getNotesByBatchId(int batchId) {
        String s = "select courseId from batch_course where batchId=?";
        List<Integer> courseIds = jdbcTemplate.query(s, (rs, i) -> {
            return rs.getInt("courseId");
        }, batchId);
        List<Notes> notes = new ArrayList<>();
        for (Integer id : courseIds) {
            List<Notes> tempNotes = courseDAO.getAllNotesByCourseId(id);
            for (Notes note : tempNotes) {
                notes.add(note);
            }
        }
        return notes;
    }

    private RowMapper<Batch> batchRowMapper = new RowMapper<Batch>() {
        @Override
        public Batch mapRow(ResultSet rs, int i) throws SQLException {
            Batch batch = new Batch();
            batch.setId(rs.getInt("id"));
            batch.setBatchName(rs.getString("batchName"));
            batch.setFees(rs.getInt("fees"));
            batch.setStartDate(rs.getDate("startDate"));
            batch.setEndDate(rs.getDate("endDate"));
            batch.setDuration(rs.getInt("duration"));
            return batch;
        }
    };

}
