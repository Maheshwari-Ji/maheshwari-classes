package com.sakshi.coaching.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.sakshi.coaching.Model.Batch;
import com.sakshi.coaching.Model.Course;
import com.sakshi.coaching.Model.User;
import com.sakshi.coaching.Service.BatchService;
import com.sakshi.coaching.Service.CourseService;
import com.sakshi.coaching.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BatchController {
    @Autowired
    private UserService userService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/teacher/batches/")
    public String batches(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        return "teacher/batches";
    }

    @GetMapping("/teacher/batches/add/")
    public String addBatch(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        Batch batch = new Batch();
        model.addAttribute("batch", batch);
        return "teacher/addBatch";
    }

    @PostMapping("/teacher/batches/add/")
    public String addBatchPost(@ModelAttribute("batch") Batch batch, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        long duration = batch.getEndDate().getTime() - batch.getStartDate().getTime();
        int diffInDays = (int) TimeUnit.MILLISECONDS.toDays(duration);
        batch.setDuration(diffInDays);
        batchService.addBatch(batch);
        System.out.println(batch.toString());
        return "redirect:/teacher/batches/";
    }

    @GetMapping("/teacher/batches/remove/{batchId}/")
    public String removeBatch(@PathVariable("batchId") int batchId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";

        batchService.removeBatch(batchId);
        return "redirect:/teacher/batches/all/";
    }

    @GetMapping("/teacher/batches/all/")
    public String allBatches(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        List<Batch> batches = batchService.getAllBatches();
        model.addAttribute("allBatches", batches);
        return "teacher/allBatches";
    }

    @GetMapping("/teacher/batches/mybatches/")
    public String myBatches(Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());
        int teacherId = user.getId();
        List<Batch> batches = batchService.getAllBatchesUnderTeacher(teacherId);
        model.addAttribute("myBatches", batches);
        return "teacher/myBatches";
    }

    @GetMapping("/teacher/batches/{batchId}/")
    public String batchPage(@PathVariable("batchId") int batchId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";

        Batch batch = batchService.getBatchByBatchId(batchId);
        model.addAttribute("batch", batch);
        List<Course> linkedCourses = batchService.getAllCoursesOfBatch(batchId);
        model.addAttribute("linkedCourses", linkedCourses);
        return "teacher/batch";
    }

    @GetMapping("/teacher/batches/{batchId}/courses/link/")
    public String linkCourseShowList(@PathVariable("batchId") int batchId, Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        List<Course> allCourses = courseService.getAllCourses();
        List<Course> linkedCourses = batchService.getAllCoursesOfBatch(batchId);
        List<Integer> linkedCoursesIds = new ArrayList<>();
        List<Course> finalCourses = new ArrayList<>();
        for (Course course : linkedCourses) {
            linkedCoursesIds.add(course.getId());
        }
        for (Course course : allCourses) {
            if (linkedCoursesIds.contains(course.getId()) == false)
                finalCourses.add(course);
        }
        System.out.println(linkedCourses);
        System.out.println(finalCourses);

        model.addAttribute("batchId", batchId);
        model.addAttribute("allCourses", finalCourses);
        return "teacher/linkCourses";
    }

    @GetMapping("/teacher/batches/{batchId}/courses/link/{courseId}/")
    public String linkCourse(@PathVariable("batchId") int batchId, @PathVariable("courseId") int courseId,
            Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        batchService.addCourseToBatch(courseId, batchId);
        return "redirect:/teacher/batches/" + batchId + "/";
    }

    @GetMapping("/teacher/batches/{batchId}/courses/unlink/{courseId}/")
    public String unlinkCourse(@PathVariable("batchId") int batchId, @PathVariable("courseId") int courseId,
            Model model) {
        String username = userService.findLoggedInUsername();
        if (username == null)
            return "redirect:/login/";
        if (userService.getLoggedInRole().equals("Student"))
            return "unauthorized";
        batchService.removeCourseFromBatch(courseId, batchId);
        return "redirect:/teacher/batches/" + batchId + "/";
    }

}
