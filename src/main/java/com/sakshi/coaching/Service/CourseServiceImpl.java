package com.sakshi.coaching.Service;

import java.util.List;

import com.sakshi.coaching.DAO.CourseDAO;
import com.sakshi.coaching.Model.Course;
import com.sakshi.coaching.Model.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDAO courseDAO;

    @Override
    public void addCourse(Course course) {
        courseDAO.addCourse(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    @Override
    public List<Course> getAllCoursesUnderTeacher(int teacherId) {
        return courseDAO.getAllCoursesUnderTeacher(teacherId);
    }

    @Override
    public void removeCourse(int courseId) {
        courseDAO.removeCourse(courseId);
    }

    @Override
    public void createNotes(Notes notes) {
        courseDAO.createNotes(notes);
    }

    @Override
    public void removeNotes(int notesId) {
        courseDAO.removeNotes(notesId);
    }

    @Override
    public List<Notes> getAllNotesByCourseId(int courseId) {
        return courseDAO.getAllNotesByCourseId(courseId);
    }

    @Override
    public Notes getNotesByNoteId(int notesId) {
        return courseDAO.getNotesByNoteId(notesId);
    }

    @Override
    public void editNotes(Notes notes, int notesId) {
        courseDAO.editNotes(notes, notesId);
    }

    @Override
    public Course getCourseByCourseId(int courseId) {
        return courseDAO.getCourseByCourseId(courseId);
    }
}
