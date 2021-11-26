package com.sakshi.coaching.Controller;

import com.sakshi.coaching.Model.User;
import com.sakshi.coaching.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login/")
    public String loginGet(Model model) {
        String username = userService.findLoggedInUsername();
        if (username != null)
            return "redirect:/home/";
        model.addAttribute("user", new User());
        return "login";
    }

    // @PostMapping("/login/")
    // public String loginPost(@ModelAttribute("user") User user)
    // {
    // System.out.println("Logged in" + user.toString());
    // return "redirect:/";
    // }
}
