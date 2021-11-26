package com.sakshi.coaching.Controller;

import com.sakshi.coaching.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping({ "/", "", "/home/" })
    public String home(Model model) {
        System.out.println("Home");
        String name = userService.findLoggedInUsername();
        model.addAttribute("loggedInUser", name);
        System.out.println(name);
        if (name != null) {
            String userRole = userService.getLoggedInRole();
            System.out.println(userRole);

            if (userRole.equals("Student"))
                return "redirect:/student/home/";
            else if (userRole.equals("Teacher"))
                return "redirect:/teacher/home/";
        }
        return "home";
    }

    @GetMapping("/error/")
    public String error(Model model) {
        return "error";
    }

}
