package com.sakshi.coaching.Service;

import java.util.List;

import com.sakshi.coaching.Model.Course;
import com.sakshi.coaching.Model.Notes;

public interface CourseService {
    public void addCourse(Course course);

    public void removeCourse(int courseId);

    public List<Course> getAllCourses();

    public List<Course> getAllCoursesUnderTeacher(int teacherId);

    public void createNotes(Notes notes);

    public void removeNotes(int notesId);

    public Course getCourseByCourseId(int courseId);

    public void editNotes(Notes notes, int notesId);

    public List<Notes> getAllNotesByCourseId(int courseId);

    public Notes getNotesByNoteId(int notesId);

}
