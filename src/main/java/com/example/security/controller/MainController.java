package com.example.security.controller;

import java.security.Principal;

import com.example.security.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        User logUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(logUser);
        model.addAttribute("userInfo", userInfo);
        return "adminPage";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, Principal principal) {
        String userName = principal.getName();
        System.out.println("User Name: " + userName);
        User logUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(logUser);
        model.addAttribute("userInfo", userInfo);
        return "userInfoPage";
    }

    @GetMapping("/403")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User logUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(logUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }

}