package com.sakshi.coaching.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sakshi.coaching.Model.Answers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void submitAnswer(Answers answer) {
        String s = "insert into answers(id,testId,studentId,answer,date,marks) values(?,?,?,?,?,?)";
        jdbcTemplate.update(s, answer.getId(), answer.getTestId(), answer.getStudentId(), answer.getAnswer(),
                answer.getDate(), answer.getMarks());
    }

    public Answers getAnswerByTestIdAndStudentId(int testId, int studentId) {
        String s = "select * from answers where testId=? and studentId=?";
        return jdbcTemplate.queryForObject(s, answersRowMapper, testId, studentId);

    }

    public void updateMarks(int studentId, int testId, int marks) {
        String s = "update answers set marks=? where testId=? and studentId=?";
        jdbcTemplate.update(s, marks, testId, studentId);
    }

    private RowMapper<Answers> answersRowMapper = new RowMapper<Answers>() {
        @Override
        public Answers mapRow(ResultSet rs, int i) throws SQLException {
            Answers answers = new Answers();
            answers.setId(rs.getInt("id"));
            answers.setTestId(rs.getInt("testId"));
            answers.setStudentId(rs.getInt("studentId"));
            answers.setAnswer(rs.getString("answer"));
            answers.setDate(rs.getDate("date"));
            answers.setMarks(rs.getInt("marks"));
            return answers;
        }
    };
}
