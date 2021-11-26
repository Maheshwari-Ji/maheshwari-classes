package com.sakshi.coaching.Service;

import java.util.ArrayList;
import java.util.List;

import com.sakshi.coaching.DAO.BatchDAO;
import com.sakshi.coaching.DAO.CourseDAO;
import com.sakshi.coaching.Model.Batch;
import com.sakshi.coaching.Model.Course;
import com.sakshi.coaching.Model.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchDAO batchDAO;
    @Autowired
    private CourseDAO courseDAO;

    @Override
    public void addBatch(Batch batch) {
        batchDAO.addBatch(batch);
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchDAO.getAllBatches();
    }

    @Override
    public List<Batch> getAllBatchesUnderTeacher(int teacherId) {
        List<Course> allCoursesUnderTeacher = courseDAO.getAllCoursesUnderTeacher(teacherId);
        List<Integer> batchIds = new ArrayList<>();
        for (Course course : allCoursesUnderTeacher) {
            for (Integer id : courseDAO.getAllBatchIdsByCourseId(course.getId())) {
                if (batchIds.contains(id) == false)
                    batchIds.add(id);
            }
        }
        List<Batch> batches = new ArrayList<>();
        for (Integer id : batchIds) {
            batches.add(getBatchByBatchId(id));
        }
        return batches;
    }

    @Override
    public List<Course> getAllCoursesOfBatch(int batchId) {
        return batchDAO.getAllCoursesOfBatch(batchId);
    }

    @Override
    public Batch getBatchByBatchId(int batchId) {
        return batchDAO.getBatchByBatchId(batchId);
    }

    @Override
    public void addCourseToBatch(int courseId, int batchId) {
        batchDAO.addCourseToBatch(courseId, batchId);
    }

    @Override
    public void removeCourseFromBatch(int courseId, int batchId) {
        batchDAO.removeCourseFromBatch(courseId, batchId);
    }

    @Override
    public void removeBatch(int batchId) {
        batchDAO.removeBatch(batchId);
    }

    @Override
    public List<Batch> getAllBatchesOfStudent(int studentId) {
        return batchDAO.getAllBatchesOfStudent(studentId);
    }

    @Override
    public void enrollToBatch(int batchId, int studentId) {
        batchDAO.enrollToBatch(batchId, studentId);
    }

    @Override
    public void unenrollFromBatch(int batchId, int studentId) {
        batchDAO.unenrollFromBatch(batchId, studentId);
    }

    @Override
    public List<Notes> getNotesByBatchId(int batchId) {
        return batchDAO.getNotesByBatchId(batchId);
    }

    @Override
    public List<Integer> getStudentIdByBatchId(int batchId) {
        return batchDAO.getStudentIdByBatchId(batchId);
    }

}
