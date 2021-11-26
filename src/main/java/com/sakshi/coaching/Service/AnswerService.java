package com.sakshi.coaching.Service;

import com.sakshi.coaching.Model.Answers;

public interface AnswerService {

    public void submitAnswer(Answers answer);

    public Answers getAnswerByTestIdAndStudentId(int testId, int studentId);

    public void updateMarks(int studentId, int testId, int marks);
}
