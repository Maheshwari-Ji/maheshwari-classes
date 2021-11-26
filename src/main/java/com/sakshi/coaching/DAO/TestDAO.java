package com.sakshi.coaching.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sakshi.coaching.Model.Answers;
import com.sakshi.coaching.Model.Test;
import com.sakshi.coaching.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchDAO batchDAO;
    @Autowired
    private UserDAO userDAO;

    public TestDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTest(Test test) {
        String s = "insert into tests(id,courseId,testName,question,testDate,startTime,endTime,duration,maximumMarks) values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(s, test.getId(), test.getCourseId(), test.getTestName(), test.getQuestion(),
                test.getTestDate(), test.getStartTime(), test.getEndTime(), test.getDuration(), test.getMaximumMarks());
    }

    public Test getTestByTestId(int testId) {
        String s = "select * from tests where id=?";
        return jdbcTemplate.queryForObject(s, testRowMapper, testId);
    }

    public List<Test> upcomingTest() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Date date = new Date(today.getTime().getTime());
        String s = "select * from tests where testDate >=?";
        return jdbcTemplate.query(s, testRowMapper, date);
    }

    public List<Test> previousTest() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Date date = new Date(today.getTime().getTime());
        String s = "select * from tests where testDate <?";
        return jdbcTemplate.query(s, testRowMapper, date);
    }

    public void submitAnswer(Answers answer) {
        String s = "insert into answers(id,testId,studentId,answer,date,marks) values(?,?,?,?,?,?)";
        jdbcTemplate.update(s, answer.getId(), answer.getTestId(), answer.getStudentId(), answer.getAnswer(),
                answer.getDate(), answer.getMarks());
    }

    public List<Answers> getAllResults() {
        String s = "select * from answers where marks!=0";
        return jdbcTemplate.query(s, answerRowMapper);
    }

    public List<Answers> getAllResultsByStudentId(int studentId) {
        String s = "select * from answers where marks!=0 and studentId=?";
        return jdbcTemplate.query(s, answerRowMapper, studentId);
    }

    public List<Answers> getAllResultsByTestId(int testId) {
        String s = "select * from answers where testId=?";
        return jdbcTemplate.query(s, answerRowMapper, testId);
    }

    public List<User> getStudentsByCourseId(int courseId) {
        String s = "select batchId from batch_course where courseId=?";
        List<Integer> batchIds = jdbcTemplate.query(s, (rs, i) -> {
            return rs.getInt("batchId");
        }, courseId);
        List<User> students = new ArrayList<>();
        for (Integer id : batchIds) {
            List<Integer> studentIds = batchDAO.getStudentIdByBatchId(id);
            for (Integer studentId : studentIds) {
                students.add(userDAO.getUserByUserId(studentId));
            }
        }
        return students;
    }

    public List<Integer> getGradedStudentIds(int testId) {
        String s = "select studentId from answers where testId=? and marks=0";
        return jdbcTemplate.query(s, (rs, i) -> {
            return rs.getInt("studentId");
        }, testId);
    }

    public List<Answers> getSubmittedAnswerByStudentId(int studentId) {
        String s = "select * from answers where studentId=?";
        return jdbcTemplate.query(s, answerRowMapper, studentId);
    }

    public Answers getResultByTestIdAndStudentId(int testId, int studentId) {
        String s = "select * from answers where testId=? and studentId=?";
        return jdbcTemplate.queryForObject(s, answerRowMapper, testId, studentId);
    }

    private RowMapper<Test> testRowMapper = new RowMapper<Test>() {
        @Override
        public Test mapRow(ResultSet rs, int i) throws SQLException {
            Test test = new Test();
            test.setId(rs.getInt("id"));
            test.setCourseId(rs.getInt("courseId"));
            test.setQuestion(rs.getString("question"));
            test.setTestName(rs.getString("testName"));
            test.setTestDate(rs.getDate("testDate"));
            test.setStartTime(rs.getString("startTime"));
            test.setEndTime(rs.getString("endTime"));
            test.setDuration(rs.getInt("duration"));
            test.setMaximumMarks(rs.getInt("maximumMarks"));
            test.setAverageMarks(rs.getInt("averageMarks"));
            return test;
        }
    };

    private RowMapper<Answers> answerRowMapper = new RowMapper<Answers>() {
        @Override
        public Answers mapRow(ResultSet rs, int i) throws SQLException {
            Answers result = new Answers();
            result.setId(rs.getInt("id"));
            result.setTestId(rs.getInt("testId"));
            result.setStudentId(rs.getInt("studentId"));
            result.setDate(rs.getDate("date"));
            result.setMarks(rs.getInt("marks"));
            result.setAnswer(rs.getString("answer"));
            return result;
        }
    };
}
