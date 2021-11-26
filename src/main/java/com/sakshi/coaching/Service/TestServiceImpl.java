package com.sakshi.coaching.Service;

import java.util.List;

import com.sakshi.coaching.DAO.TestDAO;
import com.sakshi.coaching.Model.Answers;
import com.sakshi.coaching.Model.Test;
import com.sakshi.coaching.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDAO testDAO;

    @Override
    public List<Test> upcomingTest() {
        List<Test> tests = testDAO.upcomingTest();
        System.out.println(tests);
        return tests;
    }

    @Override
    public List<Test> previousTests() {
        return testDAO.previousTest();
    }

    @Override
    public void createTest(Test test) {
        testDAO.createTest(test);
    }

    @Override
    public List<Answers> getAllResults() {
        return testDAO.getAllResults();
    }

    @Override
    public List<Answers> getAllResultsByTestId(int testId) {
        return testDAO.getAllResultsByTestId(testId);
    }

    @Override
    public Test getTestByTestId(int testId) {
        return testDAO.getTestByTestId(testId);
    }

    @Override
    public void submitAnswer(Answers answer) {
        testDAO.submitAnswer(answer);
    }

    @Override
    public List<User> getStudentsByCourseId(int courseId) {
        return testDAO.getStudentsByCourseId(courseId);
    }

    @Override
    public List<Answers> getSubmittedAnswerByStudentId(int studentId) {
        return testDAO.getSubmittedAnswerByStudentId(studentId);
    }

    @Override
    public Answers getResultByTestIdAndStudentId(int testId, int studentId) {
        return testDAO.getResultByTestIdAndStudentId(testId, studentId);
    }

    @Override
    public List<Integer> getGradedStudentIds(int testId) {
        return testDAO.getGradedStudentIds(testId);
    }

    @Override
    public List<Answers> getAllResultsByStudentId(int studentId) {
        return testDAO.getAllResultsByStudentId(studentId);
    }

}
