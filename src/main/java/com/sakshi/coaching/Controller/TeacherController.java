package com.sakshi.coaching.Controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sakshi.coaching.Model.Course;
import com.sakshi.coaching.Model.Notes;
import com.sakshi.coaching.Model.Answers;
import com.sakshi.coaching.Model.Test;
import com.sakshi.coaching.Model.User;
import com.sakshi.coaching.Service.AnswerService;
import com.sakshi.coaching.Service.CourseService;
import com.sakshi.coaching.Service.TestService;
import com.sakshi.coaching.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TestService testService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/teacher/home/")
    public String teacherHome(Model model) {

        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        System.out.println(userService.getLoggedInRole());
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        System.out.println("In Teacher Controller " + username);
        model.addAttribute("loggedInUser", username);
        return "teacher/home";
    }

    @GetMapping("/teacher/courses/")
    public String courses(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        return "teacher/courses";
    }

    @GetMapping("/teacher/courses/add/")
    public String addCourse(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        Course course = new Course();
        model.addAttribute("course", course);
        return "teacher/addCourse";
    }

    @PostMapping("/teacher/courses/add/")
    public String addCoursePost(@ModelAttribute("course") Course course, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());
        int teacherId = user.getId();
        course.setTeacherId(teacherId);
        courseService.addCourse(course);
        System.out.println(course.toString());
        return "redirect:/teacher/courses/";
    }

    @GetMapping("/teacher/courses/all/")
    public String allCourses(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        List<Course> courses = courseService.getAllCourses();
        Map<Integer, String> teacherMap = new HashMap<>();
        for (Course course : courses) {
            teacherMap.put(course.getTeacherId(), userService.getUserByUserId(course.getTeacherId()).getUsername());
        }
        model.addAttribute("allCourses", courses);
        model.addAttribute("teacherMap", teacherMap);
        return "teacher/allCourses";
    }

    @GetMapping("/teacher/courses/mycourses/")
    public String myCourses(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());
        int teacherId = user.getId();
        List<Course> courses = courseService.getAllCoursesUnderTeacher(teacherId);
        model.addAttribute("myCourses", courses);
        return "teacher/myCourses";
    }

    @GetMapping("/teacher/courses/mycourses/remove/{courseId}/")
    public String removeCourse(@PathVariable("courseId") int courseId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        courseService.removeCourse(courseId);
        return "redirect:/teacher/courses/mycourses/";
    }

    @GetMapping("/teacher/courses/mycourses/{courseId}/notes/")
    public String notesList(@PathVariable("courseId") int courseId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        List<Notes> allNotes = courseService.getAllNotesByCourseId(courseId);
        model.addAttribute("allNotes", allNotes);
        Map<Integer, String> courseMap = new HashMap<>();
        for (Notes note : allNotes) {
            courseMap.put(note.getCourseId(), courseService.getCourseByCourseId(note.getCourseId()).getName());
        }
        model.addAttribute("courseMap", courseMap);
        return "teacher/notesList";
    }

    @GetMapping("/teacher/courses/mycourses/{courseId}/notes/add/")
    public String addNotes(@PathVariable("courseId") int courseId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        Notes notes = new Notes();
        model.addAttribute("notes", notes);
        model.addAttribute("courseId", courseId);
        return "teacher/addNotes";
    }

    @PostMapping("/teacher/courses/mycourses/{courseId}/notes/add/")
    public String addNotesPost(@ModelAttribute("notes") Notes notes, @PathVariable("courseId") int courseId,
            Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());
        notes.setCourseId(courseId);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Date date = new Date(today.getTime().getTime());
        notes.setDate(date);
        courseService.createNotes(notes);
        System.out.println(notes.toString());
        return "redirect:/teacher/courses/mycourses/" + courseId + "/notes/";
    }

    @GetMapping("/teacher/courses/mycourses/{courseId}/notes/remove/{notesId}/")
    public String removeNotes(@PathVariable("courseId") int courseId, @PathVariable("notesId") int id, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        courseService.removeNotes(id);
        return "redirect:/teacher/courses/mycourses/" + courseId + "/notes/";
    }

    @GetMapping("/teacher/courses/mycourses/{courseId}/notes/view/{notesId}/")
    public String viewNotes(@PathVariable("courseId") int courseId, @PathVariable("notesId") int id, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        Notes notes = courseService.getNotesByNoteId(id);
        model.addAttribute("notes", notes);
        return "teacher/notes";
    }

    @GetMapping("/teacher/courses/mycourses/{courseId}/notes/edit/{notesId}/")
    public String editNotes(@PathVariable("courseId") int courseId, @PathVariable("notesId") int id, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        Notes notes = courseService.getNotesByNoteId(id);
        model.addAttribute("notes", notes);
        model.addAttribute("courseId", courseId);
        model.addAttribute("notesId", id);
        model.addAttribute("edit", true);
        return "teacher/addNotes";
    }

    @PostMapping("/teacher/courses/mycourses/{courseId}/notes/edit/{notesId}/")
    public String editNotesPost(@ModelAttribute("notes") Notes notes, @PathVariable("courseId") int courseId,
            @PathVariable("notesId") int id, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());
        courseService.editNotes(notes, id);
        System.out.println(notes.toString());
        return "redirect:/teacher/courses/mycourses/" + courseId + "/notes/view/" + id + "/";
    }

    @GetMapping("/teacher/tests/all/")
    public String allTests(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        return "teacher/allTest";
    }

    @GetMapping("/teacher/tests/add/")
    public String addTest(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        int teacherId = userService.getUserByUsername(username).getId();
        List<Course> myCourses = courseService.getAllCoursesUnderTeacher(teacherId);
        Test test = new Test();
        model.addAttribute("test", test);
        model.addAttribute("myCourses", myCourses);
        return "teacher/addTest";
    }

    @PostMapping("/teacher/tests/add/")
    public String addTestPost(@ModelAttribute("test") Test test, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());
        // Calendar today = Calendar.getInstance();
        // today.add(Calendar.HOUR, test.getDuration());
        // Date date = new Date(today.getTime().getTime());
        Integer hour = Integer.parseInt(test.getStartTime().substring(0, 2)) + test.getDuration();
        System.out.println(hour);
        Integer min = Integer.parseInt(test.getStartTime().substring(3, 5));
        String endTime = hour.toString() + ":" + min.toString();
        System.out.println(endTime);
        test.setEndTime(endTime);
        testService.createTest(test);
        System.out.println(test.toString());
        return "redirect:/teacher/tests/all/";
    }

    @GetMapping("/teacher/tests/upcomimg/")
    public String upcomingTests(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        List<Test> tests = testService.upcomingTest();
        model.addAttribute("tests", tests);
        return "teacher/upcomingTest";
    }

    @GetMapping("/teacher/tests/previous/")
    public String previousTests(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        List<Test> tests = testService.previousTests();
        model.addAttribute("tests", tests);
        return "teacher/previousTests";
    }

    @GetMapping("/teacher/tests/{id}/results/add/")
    public String addResult(Model model, @PathVariable("id") int testId) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        Answers result = new Answers();
        Test test = testService.getTestByTestId(testId);
        int courseId = test.getCourseId();
        List<User> students = testService.getStudentsByCourseId(courseId);
        System.out.println(students);
        List<Integer> gradedStudentIds = testService.getGradedStudentIds(testId);
        model.addAttribute("gradedStudentIds", gradedStudentIds);
        model.addAttribute("result", result);
        model.addAttribute("students", students);
        model.addAttribute("testId", testId);
        return "teacher/addResult";
    }

    @GetMapping("/teacher/tests/{id}/results/add/{studentId}/")
    public String gradeStudent(Model model, @PathVariable("id") int testId, @PathVariable("studentId") int studentId) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        Answers result = answerService.getAnswerByTestIdAndStudentId(testId, studentId);
        Test test = testService.getTestByTestId(testId);
        model.addAttribute("result", result);
        model.addAttribute("test", test);
        return "teacher/gradeStudent";
    }

    @PostMapping("/teacher/tests/{id}/results/add/{studentId}/")
    public String gradeStudentPost(@ModelAttribute("result") Answers result, @PathVariable("studentId") int studentId,
            @PathVariable("id") int testId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());
        answerService.updateMarks(studentId, testId, result.getMarks());
        return "redirect:/teacher/tests/" + testId + "/results/add/";
    }

    @GetMapping("/teacher/results/all/")
    public String allResults(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        List<Answers> allResults = testService.getAllResults();
        Map<Integer, Test> testMap = new HashMap<>();
        for (Answers answer : allResults) {
            testMap.put(answer.getTestId(), testService.getTestByTestId(answer.getTestId()));
        }
        Map<Integer, String> studentMap = new HashMap<>();
        for (Answers answer : allResults) {
            studentMap.put(answer.getStudentId(), userService.getUserByUserId(answer.getStudentId()).getUsername());
        }
        model.addAttribute("studentMap", studentMap);
        model.addAttribute("testMap", testMap);
        model.addAttribute("allResults", allResults);
        return "teacher/allResults";
    }

    @GetMapping("/teacher/tests/{id}/results/view/")
    public String viewResult(Model model, @PathVariable("id") int testId) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        List<Answers> result = testService.getAllResultsByTestId(testId);
        Map<Integer, Test> testMap = new HashMap<>();
        for (Answers answer : result) {
            testMap.put(answer.getTestId(), testService.getTestByTestId(answer.getTestId()));
        }
        Map<Integer, String> studentMap = new HashMap<>();
        for (Answers answer : result) {
            studentMap.put(answer.getStudentId(), userService.getUserByUserId(answer.getStudentId()).getUsername());
        }
        model.addAttribute("studentMap", studentMap);
        model.addAttribute("testMap", testMap);
        model.addAttribute("result", result);
        return "teacher/viewResults";
    }

}
