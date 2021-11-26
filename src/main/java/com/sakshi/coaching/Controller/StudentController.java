package com.sakshi.coaching.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sakshi.coaching.Model.Answers;
import com.sakshi.coaching.Model.Batch;
import com.sakshi.coaching.Model.Notes;
import com.sakshi.coaching.Model.Test;
import com.sakshi.coaching.Service.AnswerService;
import com.sakshi.coaching.Service.BatchService;
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
public class StudentController {
    @Autowired
    private UserService userService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TestService testService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/student/home/")
    public String studentHome(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        System.out.println("In Student Controller " + username);
        model.addAttribute("loggedInUser", username);
        return "student/home";
    }

    @GetMapping("/student/batches/all/")
    public String allStudentBatches(Model model) {
        String username = userService.findLoggedInUsername();
        System.out.println(username);
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        List<Batch> allBatches = batchService.getAllBatches();
        int studentId = userService.getUserByUsername(username).getId();
        List<Batch> enrolledBatches = batchService.getAllBatchesOfStudent(studentId);

        List<Integer> enrolledBatchesIds = new ArrayList<>();
        for (Batch batch : enrolledBatches) {
            enrolledBatchesIds.add(batch.getId());
        }
        model.addAttribute("allBatches", allBatches);
        model.addAttribute("enrolledBatchIds", enrolledBatchesIds);
        return "student/allBatches";
    }

    @GetMapping("/student/batches/enrolled/")
    public String allEnrolledBatches(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        int studentId = userService.getUserByUsername(username).getId();
        List<Batch> enrolledBatches = batchService.getAllBatchesOfStudent(studentId);
        model.addAttribute("enrolledBatches", enrolledBatches);
        return "student/enrolledBatches";
    }

    @GetMapping("/student/batches/enroll/{batchId}/")
    public String enrollToBatch(@PathVariable("batchId") int batchId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        int studentId = userService.getUserByUsername(username).getId();
        batchService.enrollToBatch(batchId, studentId);
        return "redirect:/student/batches/all/";
    }

    @GetMapping("/student/batches/unenroll/{batchId}/")
    public String unenrollFromBatch(@PathVariable("batchId") int batchId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        int studentId = userService.getUserByUsername(username).getId();
        batchService.unenrollFromBatch(batchId, studentId);
        return "redirect:/student/batches/all/";
    }

    @GetMapping("/student/batches/{batchId}/notes/view/")
    public String notesList(@PathVariable("batchId") int batchId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        List<Notes> allNotes = batchService.getNotesByBatchId(batchId);
        Map<Integer, String> courseMap = new HashMap<>();
        for (Notes note : allNotes) {
            courseMap.put(note.getCourseId(), courseService.getCourseByCourseId(note.getCourseId()).getName());
        }
        model.addAttribute("courseMap", courseMap);
        model.addAttribute("allNotes", allNotes);
        return "student/notesList";
    }

    @GetMapping("/student/batches/{batchId}/notes/view/{notesId}/")
    public String viewNotes(@PathVariable("batchId") int batchId, @PathVariable("notesId") int notesId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        Notes notes = courseService.getNotesByNoteId(notesId);
        model.addAttribute("notes", notes);
        return "student/viewNotes";
    }

    @GetMapping("/student/tests/all/")
    public String allTests(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        return "student/allTest";
    }

    @GetMapping("/student/tests/{id}/")
    public String viewTest(@PathVariable("id") int testId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        Test test = testService.getTestByTestId(testId);
        int studentId = userService.getUserByUsername(username).getId();
        Answers pastAnswers = answerService.getAnswerByTestIdAndStudentId(testId, studentId);
        Answers answers = new Answers();
        if (pastAnswers != null)
            answers = pastAnswers;
        model.addAttribute("test", test);
        model.addAttribute("answers", answers);
        return "student/testPage";
    }

    @GetMapping("/student/tests/upcomimg/")
    public String upcomingTests(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        List<Test> tests = testService.upcomingTest();
        model.addAttribute("tests", tests);
        int studentId = userService.getUserByUsername(username).getId();
        List<Answers> submittedAnswers = testService.getSubmittedAnswerByStudentId(studentId);
        List<Integer> testIdSubmittedAnswers = new ArrayList<>();
        for (Answers answers : submittedAnswers) {
            testIdSubmittedAnswers.add(answers.getTestId());
        }
        model.addAttribute("testIdSubmittedAnswers", testIdSubmittedAnswers);
        return "student/upcomingTest";
    }

    @GetMapping("/student/tests/previous/")
    public String previousTests(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        List<Test> tests = testService.previousTests();
        int studentId = userService.getUserByUsername(username).getId();
        List<Answers> submittedAnswers = testService.getSubmittedAnswerByStudentId(studentId);
        List<Integer> testIdSubmittedAnswers = new ArrayList<>();
        for (Answers answers : submittedAnswers) {
            testIdSubmittedAnswers.add(answers.getTestId());
        }
        model.addAttribute("testIdSubmittedAnswers", testIdSubmittedAnswers);
        model.addAttribute("tests", tests);
        return "student/previousTests";
    }

    @GetMapping("/student/tests/submit/{testId}/")
    public String submitTest(@PathVariable("testId") int testId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        Answers answers = new Answers();
        String question = testService.getTestByTestId(testId).getQuestion();

        model.addAttribute("answers", answers);
        model.addAttribute("question", question);
        model.addAttribute("testId", testId);
        return "student/submitTest";
    }

    @PostMapping("/student/tests/submit/{testId}/")
    public String submitTestPost(@ModelAttribute("answers") Answers answers, @PathVariable("testId") int testId,
            Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        int studentId = userService.getUserByUsername(username).getId();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Date date = new Date(today.getTime().getTime());
        answers.setTestId(testId);
        answers.setDate(date);
        answers.setStudentId(studentId);
        System.out.println(answers.toString());
        testService.submitAnswer(answers);
        return "redirect:/student/tests/all/";
    }

    @GetMapping("/student/tests/viewresult/{testId}/")
    public String viewResult(Model model, @PathVariable("testId") int testId) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        int studentId = userService.getUserByUsername(username).getId();
        Test test = testService.getTestByTestId(testId);
        Answers answers = testService.getResultByTestIdAndStudentId(testId, studentId);
        model.addAttribute("answers", answers);
        model.addAttribute("test", test);
        return "student/viewResult";
    }

    @GetMapping("/student/results/all/")
    public String allResults(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Teacher"))
            return "unauthorized";
        int studentId = userService.getUserByUsername(username).getId();
        List<Answers> allResults = testService.getAllResultsByStudentId(studentId);
        Map<Integer, Test> testMap = new HashMap<>();
        for (Answers answer : allResults) {
            testMap.put(answer.getTestId(), testService.getTestByTestId(answer.getTestId()));
        }
        model.addAttribute("testMap", testMap);
        model.addAttribute("allResults", allResults);
        return "student/allResults";
    }
}
