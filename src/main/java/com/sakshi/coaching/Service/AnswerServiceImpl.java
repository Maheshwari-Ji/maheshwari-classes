package com.sakshi.coaching.Service;

import com.sakshi.coaching.DAO.AnswerDAO;
import com.sakshi.coaching.Model.Answers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDAO answerDAO;

    @Override
    public void submitAnswer(Answers answer) {
        answerDAO.submitAnswer(answer);
    }

    @Override
    public Answers getAnswerByTestIdAndStudentId(int testId, int studentId) {
        return answerDAO.getAnswerByTestIdAndStudentId(testId, studentId);
    }

    @Override
    public void updateMarks(int studentId, int testId, int marks) {
        answerDAO.updateMarks(studentId, testId, marks);
    }

}
