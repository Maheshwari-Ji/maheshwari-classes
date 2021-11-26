package com.sakshi.coaching.Service;

import java.util.List;

import com.sakshi.coaching.Model.Batch;
import com.sakshi.coaching.Model.Course;
import com.sakshi.coaching.Model.Notes;

public interface BatchService {
    public void addBatch(Batch batch);

    public void removeBatch(int batchId);

    public List<Batch> getAllBatches();

    public List<Batch> getAllBatchesUnderTeacher(int teacherId);

    public List<Course> getAllCoursesOfBatch(int batchId);

    public Batch getBatchByBatchId(int batchId);

    public List<Integer> getStudentIdByBatchId(int batchId);

    public void addCourseToBatch(int courseId, int batchId);

    public void removeCourseFromBatch(int courseId, int batchId);

    public List<Batch> getAllBatchesOfStudent(int studentId);

    public void enrollToBatch(int batchId, int studentId);

    public void unenrollFromBatch(int batchId, int studentId);

    public List<Notes> getNotesByBatchId(int batchId);
}
