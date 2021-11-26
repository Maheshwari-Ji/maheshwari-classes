package com.sakshi.coaching.Service;

import java.util.List;

import com.sakshi.coaching.Model.Answers;
import com.sakshi.coaching.Model.Test;
import com.sakshi.coaching.Model.User;

public interface TestService {
    public List<Test> upcomingTest();

    public List<Test> previousTests();

    public void createTest(Test test);

    public List<Answers> getSubmittedAnswerByStudentId(int studentId);

    public List<User> getStudentsByCourseId(int courseId);

    public void submitAnswer(Answers answer);

    public List<Answers> getAllResults();

    public List<Answers> getAllResultsByStudentId(int studentId);

    public List<Answers> getAllResultsByTestId(int testId);

    public Test getTestByTestId(int testId);

    public Answers getResultByTestIdAndStudentId(int testId, int studentId);

    public List<Integer> getGradedStudentIds(int testId);
}
