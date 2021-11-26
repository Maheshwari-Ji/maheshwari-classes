package com.sakshi.coaching.Controller;

import com.sakshi.coaching.Model.User;
import com.sakshi.coaching.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register/")
    public String registerGet(Model model) {
        String username = userService.findLoggedInUsername();
        if (username != null)
            return "redirect:/home/";
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/")
    public String registerPost(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        System.out.println("Registered" + user.toString());

        return "redirect:/";
    }
}
